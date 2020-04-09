package com.code.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hb-03-one-to-many?useSSL=false";
        String name = "root";
        String password = "1234";

        try(Connection connection = DriverManager.getConnection(url, name, password);) {
            System.out.println(url);
            System.out.println(connection);
        }catch (Exception e){}
    }
}
