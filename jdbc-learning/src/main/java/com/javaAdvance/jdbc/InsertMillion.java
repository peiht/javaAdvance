package com.javaAdvance.jdbc;

import java.sql.*;
import java.util.Random;

/**
 * @author hitopei
 * <p>
 * 插入100w的方式
 */
public class InsertMillion {


    private static final String username = "root";
    private static final String password = "123456";
    private static final String jdbcUrl = "jdbc:mysql://localhost:3309/test_sharding?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull";

    public static void main(String[] args) {

        Connection connection = null;
        //Statement statement = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            //statement = connection.createStatement();


            long start = System.currentTimeMillis();
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                //增
                long start1 = System.currentTimeMillis();
                String insertSql = "INSERT INTO `goods_order`(`order_name`, `user_id`, `user_name`, `trade_amount`, `trade_type`, `order_status`, " +
                        "`trade_status`,`user_phone`, `user_mail`, " +
                        "`trade_num`, `source`, `goods_id`, `goods_name`, `goods_detail_id`, `coupon_id`, `remark`, `is_hidden`) " +
                        "VALUES ";
                insertSql += "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                statement = connection.prepareStatement(insertSql);
                //for (int j = 0; j < 100; j++) {
                    statement.setString(1, "order_name" + i);
                    statement.setInt(2, random.nextInt(1000));
                    statement.setString(3, "test" + random.nextInt(1000));
                    statement.setString(4, String.valueOf(random.nextInt(10000)));
                    statement.setInt(5, random.nextInt(5));
                    statement.setInt(6, random.nextInt(3));
                    statement.setInt(7, random.nextInt(2));
                    statement.setString(8, "1883823823");
                    statement.setString(9, "1071532941@qq.com");
                    statement.setInt(10, random.nextInt(20));
                    statement.setString(11, "h5");
                    statement.setInt(12, random.nextInt(1000));
                    statement.setString(13, "goods_name" + random.nextInt(100));
                    statement.setString(14, String.valueOf(random.nextInt(1000)));
                    statement.setInt(15, random.nextInt(1000));
                    statement.setString(16, "1");
                    statement.setInt(17, 1);
                //}
                statement.execute();
                long end1 = System.currentTimeMillis();
                System.out.println("10条数据执行时间" + (end1 - start1));
            }
            long end = System.currentTimeMillis();
            System.out.println("执行成功, 毫秒数: " + (end - start));
            System.out.println("秒数 ： " + (end - start) / 1000);
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
