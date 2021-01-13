package com.ID0420FF19OWidya.dao;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class DBConnection
{
    public static Connection getConnection() {
        Connection conn = null;
        final String url = "jdbc:mysql://localhost:3306/<database>";
        final String user = "";
        final String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("connection ok!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        return conn;
    }
    
    public static void close(final Statement myStat, final Connection conn) {
        try {
            if (myStat != null) {
                myStat.close();
                System.out.println("statement closed");
            }
            conn.close();
            System.out.println("connection closed");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
