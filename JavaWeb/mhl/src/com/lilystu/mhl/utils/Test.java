package com.lilystu.mhl.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author lily
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        //jdbc
        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println(connection);
    }
}
