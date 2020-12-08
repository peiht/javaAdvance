package com.javaAdvance.jdbc;

import java.sql.*;

public class ComputeOrderId {

    private static final String username = "root";
    private static final String password = "";
    private static final String jdbcUrl = "jdbc:mysql://localhost:3308/order_1?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();
            String sql = "select order_id from goods_order_0";

            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                long orderId = set.getLong(1);
                System.out.println( orderId+ "取模结果= " + orderId%2);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
