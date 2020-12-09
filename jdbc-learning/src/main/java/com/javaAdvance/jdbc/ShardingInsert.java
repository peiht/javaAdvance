package com.javaAdvance.jdbc;

import java.sql.*;
import java.util.Random;

/**
 * @author ty
 *
 */
public class ShardingInsert {

    private static final String username = "root";
    private static final String password = "123456";
    private static final String jdbcUrl = "jdbc:mysql://127.0.0.1:3309/test_sharding?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true";

    public static void main(String[] args) {
        deleteById();
    }

    private static void insertMillion(){
        Random random = new Random();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);
            long start = System.currentTimeMillis();
            //for (int i = 0; i < 1000; i++) {
            String insertSql = "insert into `goods_order` (`order_name`, `user_id`, `user_name`, `trade_amount`, `trade_type`, `order_status`, " +
                    "`trade_status`, `user_phone`, `user_mail`, "  +
                    "`trade_num`, `source`, `goods_id`, `goods_name`, `goods_detail_id`, `coupon_id`, `remark`) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(insertSql);
            for (int i = 0; i< 1000_000; i++) {
                statement.setString(1, "order_name" + random.nextInt(10));
                statement.setInt(2, random.nextInt(10000));
                statement.setString(3, "user_name" + random.nextInt(100));
                statement.setString(4, String.valueOf(random.nextInt(100)));
                statement.setInt(5, random.nextInt(3));
                statement.setInt(6, random.nextInt(3));
                statement.setInt(7, random.nextInt(2));
                statement.setString(8, "18810905210");
                statement.setString(9, "com");
                statement.setInt(10, random.nextInt(10));
                statement.setString(11, "h5");
                statement.setInt(12, random.nextInt(100));
                statement.setString(13, "goods_name" + random.nextInt(100));
                statement.setString(14, String.valueOf(random.nextInt(100)));
                statement.setInt(15, random.nextInt(1000));
                statement.setString(16, "remark");
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
            // }
            System.out.println("执行时间：" + (System.currentTimeMillis() - start));

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

    private static void insert(){

    }

    private static void update(){
        long orderId = 543390569842848056l;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "update goods_order set update_time = now() where order_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, orderId);
            int res = statement.executeUpdate();
            System.out.println(res);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    private static void selectByUserId(){
        long orderId = 543390569842848056l;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "select * from goods_order where order_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("订单名称：" + resultSet.getString(2) + "用户id:" + resultSet.getInt(3));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    private static void deleteById(){
        long orderId = 543390569842848056l;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "delete from goods_order where order_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, orderId);
            boolean is = statement.execute();
            System.out.println(is);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
}
