package com.lilystu.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

//@Configuration
public class DruidDataSourceConfig {

    //注入Druid组件
    @Bean
    //通过配置文件配置数据源设置
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        //加入sql语句监控功能，加入sql防火墙监控功能
        dataSource.setFilters("stat,wall");
        return dataSource;
    }

    //配置druid页面监控功能
    @Bean
    public ServletRegistrationBean statViewServlet(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean registrationBean =
                new ServletRegistrationBean(statViewServlet,"/druid/*");
        //配置init-parameter
        registrationBean.addInitParameter("loginUsername","lily");
        registrationBean.addInitParameter("loginPassword","666666");
        return registrationBean;
    }

    //配置druid web监控
    @Bean
    public FilterRegistrationBean webStatFilter(){
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        //默认对所有url进行监控
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        //排除指定url
        registrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return registrationBean;
    }
}
