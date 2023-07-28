package com.lilystu.furns.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 韩顺平
 * @version 1.0
 * 基于druid数据库连接池的工具类
 */
public class JDBCUtilsByDruid {

    private static DataSource ds;
    //事务管理要点：同一个connection，因为事务和connection关联
    //笔记：一个线程可以有多个ThreadLocal对象，但是一个ThreadLocal对象只能保存一个对象
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

//测试是否可以正常连接数据库
//    public static void main(String[] args) throws SQLException {
//        Connection connection = JDBCUtilsByDruid.getConnection();
//        System.out.println(connection);
//    }

    //在静态代码块完成 ds初始化
    static {
        Properties properties = new Properties();
        try {
            //在javaweb中获取src中的文件，需要使用类加载器
            properties.load(JDBCUtilsByDruid.class.getClassLoader()//拿到类加载器
                    .getResourceAsStream("druid.properties"));
//            properties.load(new FileInputStream("src\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //编写getConnection方法
//    public static Connection getConnection() throws SQLException {
//        return ds.getConnection();
//    }

    /**
     * 从TreadLocal获取connection对象，保证在同一个线程中使用的是同一个connection对象
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() {
        Connection connection = threadLocal.get();
        if (connection == null){
            try {
                connection =ds.getConnection();
                connection.setAutoCommit(false);//将连接设置为手动提交
            }catch (SQLException e){
                e.printStackTrace();
            }
            threadLocal.set(connection);
        }
        return connection;
    }

    /**
     * 提交事务:commit某个connection的所有事务
     * mysql事务+线程+ThreadLocal+过滤机制
     */
    public static void commit(){
        Connection connection = threadLocal.get();
        if (connection != null){
            try {
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //提交后要将connection从treadLocal中移除
        //不然会造成treadLocal长期持有该连接，影响效率
        //因为Tomcat底层使用的是连接池技术
        threadLocal.remove();
    }

    /**
     * 回滚事务
     * 回滚（撤销）某个connection的所有事务
     */
    public static void rollback(){
        Connection connection = threadLocal.get();
        if (connection != null){
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        threadLocal.remove();
    }

    //关闭连接, 老师再次强调： 在数据库连接池技术中，close 不是真的断掉连接
    //而是把使用的Connection对象放回连接池
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
