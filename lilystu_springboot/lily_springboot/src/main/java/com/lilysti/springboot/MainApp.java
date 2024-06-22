package com.lilysti.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ioc =
                SpringApplication.run(MainApp.class, args);

        /**
         * 寻找tomcat的启动方法
         * 1.SpringApplication.java
         *     public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
         *         return run(new Class[]{primarySource}, args);
         *     }
         *
         * 2.
         *    public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
         *          return (new SpringApplication(primarySources)).run(args);
         *     }
         *
         * 3.
         *     public ConfigurableApplicationContext run(String... args) {
         *         StopWatch stopWatch = new StopWatch();
         *         stopWatch.start();
         *         DefaultBootstrapContext bootstrapContext = this.createBootstrapContext();
         *         ConfigurableApplicationContext context = null;
         *         this.configureHeadlessProperty();
         *         SpringApplicationRunListeners listeners = this.getRunListeners(args);
         *         listeners.starting(bootstrapContext, this.mainApplicationClass);
         *
         *         try {
         *             ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
         *             ConfigurableEnvironment environment = this.prepareEnvironment(listeners, bootstrapContext, applicationArguments);
         *             this.configureIgnoreBeanInfo(environment);
         *             Banner printedBanner = this.printBanner(environment);
         *             context = this.createApplicationContext();  //======>重点分析此方法，会创建容器
         *             context.setApplicationStartup(this.applicationStartup);
         *             this.prepareContext(bootstrapContext, context, environment, listeners, applicationArguments, printedBanner);
         *             this.refreshContext(context);   //=====>刷新应用程序上下文，比如启动tomcat
         *             this.afterRefresh(context, applicationArguments);
         *             stopWatch.stop();
         *             if (this.logStartupInfo) {
         *                 (new StartupInfoLogger(this.mainApplicationClass)).logStarted(this.getApplicationLog(), stopWatch);
         *             }
         *
         *             listeners.started(context);
         *             this.callRunners(context, applicationArguments);
         *         } catch (Throwable var10) {
         *             this.handleRunFailure(context, var10, listeners);
         *             throw new IllegalStateException(var10);
         *         }
         *
         *         try {
         *             listeners.running(context);
         *             return context;
         *         } catch (Throwable var9) {
         *             this.handleRunFailure(context, var9, (SpringApplicationRunListeners)null);
         *             throw new IllegalStateException(var9);
         *         }
         *     }
         *
         * 4.context = this.createApplicationContext();
         *      protected ConfigurableApplicationContext createApplicationContext() {
         *         return this.applicationContextFactory.create(this.webApplicationType); //====>this.webApplicationType决定创建容器的类型，默认servlet容器，也就是web容器
         *     }
         *
         * 5.
         *  ApplicationContextFactory DEFAULT = (webApplicationType) -> {
         *         try {
         *             switch(webApplicationType) {
         *             case SERVLET:
         *                 return new AnnotationConfigServletWebServerApplicationContext(); //====>创建容器，但是里面还没有注入关键组件
         *             case REACTIVE:
         *                 return new AnnotationConfigReactiveWebServerApplicationContext();
         *             default:
         *                 return new AnnotationConfigApplicationContext();
         *             }
         *         } catch (Exception var2) {
         *             throw new IllegalStateException("Unable create a default ApplicationContext instance, you may need a custom ApplicationContextFactory", var2);
         *         }
         *     };
         * 6.this.refreshContext(context);
         *     private void refreshContext(ConfigurableApplicationContext context) {
         *         if (this.registerShutdownHook) {
         *             shutdownHook.registerApplicationContext(context);
         *         }
         *
         *         this.refresh(context); //===>重点分析，真正执行刷新工作
         *     }
         *
         * 7.this.refresh(context);
         *      protected void refresh(ConfigurableApplicationContext applicationContext) {
         *         applicationContext.refresh();
         *     }
         *
         * 8. ServletWebServerApplicationContext.java
         *     public final void refresh() throws BeansException, IllegalStateException {
         *         try {
         *             super.refresh();
         *         } catch (RuntimeException var3) {
         *             WebServer webServer = this.webServer;
         *             if (webServer != null) {
         *                 webServer.stop();
         *             }
         *
         *             throw var3;
         *         }
         *     }
         *
         * 9.AbstractApplicationContext.java
         *     public void refresh() throws BeansException, IllegalStateException {
         *         synchronized(this.startupShutdownMonitor) {
         *             StartupStep contextRefresh = this.applicationStartup.start("spring.context.refresh");
         *             this.prepareRefresh();
         *             ConfigurableListableBeanFactory beanFactory = this.obtainFreshBeanFactory();
         *             this.prepareBeanFactory(beanFactory);
         *
         *             try {
         *                 this.postProcessBeanFactory(beanFactory);
         *                 StartupStep beanPostProcess = this.applicationStartup.start("spring.context.beans.post-process");
         *                 this.invokeBeanFactoryPostProcessors(beanFactory);
         *                 this.registerBeanPostProcessors(beanFactory);
         *                 beanPostProcess.end();
         *                 this.initMessageSource();
         *                 this.initApplicationEventMulticaster();
         *                 this.onRefresh();  //====>模板模式，父类执行完通用的初始化工作，再动态绑定到子类方法，执行具体的刷新任务
         *                 this.registerListeners();
         *                 this.finishBeanFactoryInitialization(beanFactory);
         *                 this.finishRefresh();
         *             } catch (BeansException var10) {
         *                 if (this.logger.isWarnEnabled()) {
         *                     this.logger.warn("Exception encountered during context initialization - cancelling refresh attempt: " + var10);
         *                 }
         *
         *                 this.destroyBeans();
         *                 this.cancelRefresh(var10);
         *                 throw var10;
         *             } finally {
         *                 this.resetCommonCaches();
         *                 contextRefresh.end();
         *             }
         *
         *         }
         *     }
         *
         * 10.ServletWebServerApplicationContext.java
         *     protected void onRefresh() {
         *         super.onRefresh();
         *
         *         try {
         *             this.createWebServer(); //====>这里发生了什么？创建web server
         *         } catch (Throwable var2) {
         *             throw new ApplicationContextException("Unable to start web server", var2);
         *         }
         *     }
         *
         * 11.
         *     private void createWebServer() {
         *         WebServer webServer = this.webServer;
         *         ServletContext servletContext = this.getServletContext();//获取上下文
         *         if (webServer == null && servletContext == null) {
         *             StartupStep createWebServer = this.getApplicationStartup().start("spring.boot.webserver.create");
         *             ServletWebServerFactory factory = this.getWebServerFactory();
         *             createWebServer.tag("factory", factory.getClass().toString());
         *             this.webServer = factory.getWebServer(new ServletContextInitializer[]{this.getSelfInitializer()}); //=====>创建一个tomcat的web Server
         *             createWebServer.end();
         *             this.getBeanFactory().registerSingleton("webServerGracefulShutdown", new WebServerGracefulShutdownLifecycle(this.webServer));
         *             this.getBeanFactory().registerSingleton("webServerStartStop", new WebServerStartStopLifecycle(this, this.webServer));
         *         } else if (servletContext != null) {
         *             try {
         *                 this.getSelfInitializer().onStartup(servletContext);
         *             } catch (ServletException var5) {
         *                 throw new ApplicationContextException("Cannot initialize servlet context", var5);
         *             }
         *         }
         *
         *         this.initPropertySources();
         *     }
         *
         * 12.TomcatServletWebServerFactory.java
         *     public WebServer getWebServer(ServletContextInitializer... initializers) {
         *         if (this.disableMBeanRegistry) {
         *             Registry.disableRegistry();
         *         }
         *
         *         Tomcat tomcat = new Tomcat();  //创建tomcat对象
         *
         *         //一些初始化配置
         *         File baseDir = this.baseDirectory != null ? this.baseDirectory : this.createTempDir("tomcat");
         *         tomcat.setBaseDir(baseDir.getAbsolutePath());
         *         Connector connector = new Connector(this.protocol);
         *         connector.setThrowOnFailure(true);
         *         tomcat.getService().addConnector(connector);
         *         this.customizeConnector(connector);
         *         tomcat.setConnector(connector);
         *         tomcat.getHost().setAutoDeploy(false);
         *         this.configureEngine(tomcat.getEngine());
         *         Iterator var5 = this.additionalTomcatConnectors.iterator();
         *
         *         while(var5.hasNext()) {
         *             Connector additionalConnector = (Connector)var5.next();
         *             tomcat.getService().addConnector(additionalConnector);
         *         }
         *
         *         this.prepareContext(tomcat.getHost(), initializers);
         *         return this.getTomcatWebServer(tomcat); //重点分析，启动tomcat
         *     }
         * 13.this.getTomcatWebServer(tomcat)  //做了一个校验，创建了一个TomcatWebServer
         *     protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
         *         return new TomcatWebServer(tomcat, this.getPort() >= 0, this.getShutdown());
         *     }
         *
         * 14.TomcatWebServer.java
         *     public TomcatWebServer(Tomcat tomcat, boolean autoStart, Shutdown shutdown) {
         *         this.monitor = new Object();
         *         this.serviceConnectors = new HashMap();
         *         Assert.notNull(tomcat, "Tomcat Server must not be null");
         *         this.tomcat = tomcat;
         *         this.autoStart = autoStart;
         *         this.gracefulShutdown = shutdown == Shutdown.GRACEFUL ? new GracefulShutdown(tomcat) : null;
         *         this.initialize();
         *     }
         *
         * 15.this.initialize();
         *     private void initialize() throws WebServerException {
         *         logger.info("Tomcat initialized with port(s): " + this.getPortsDescription(false));
         *         synchronized(this.monitor) {
         *             try {
         *                 this.addInstanceIdToEngineName();
         *                 Context context = this.findContext();
         *                 context.addLifecycleListener((event) -> {
         *                     if (context.equals(event.getSource()) && "start".equals(event.getType())) {
         *                         this.removeServiceConnectors();
         *                     }
         *
         *                 });
         *                 this.tomcat.start();  //启动tomcat
         *                 this.rethrowDeferredStartupExceptions();
         *
         *                 try {
         *                     ContextBindings.bindClassLoader(context, context.getNamingToken(), this.getClass().getClassLoader());
         *                 } catch (NamingException var5) {
         *                 }
         *
         *                 this.startDaemonAwaitThread();
         *             } catch (Exception var6) {
         *                 this.stopSilently();
         *                 this.destroySilently();
         *                 throw new WebServerException("Unable to start embedded Tomcat", var6);
         *             }
         *
         *         }
         *     }
         *
         * 16.this.tomcat.start();
         *
         */
        System.out.println("hello ioc");
    }
}
