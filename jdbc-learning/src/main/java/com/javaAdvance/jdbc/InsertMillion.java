package com.javaAdvance.jdbc;

import java.sql.*;

/**
 * @author hitopei
 * <p>
 * 插入100w的方式
 */
public class InsertMillion {


    private static final String username = "root";
    private static final String password = "root";
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull";

    public static void main(String[] args) {

        Connection connection = null;
        //Statement statement = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            //statement = connection.createStatement();


            long start = System.currentTimeMillis();
            for (int i = 0; i < 100_000; i++) {
                //增
                long start1 = System.currentTimeMillis();
                String insertSql = "INSERT INTO `goods_order`(`order_name`, `u_id`, `u_name`, `trade_amount`, `trade_type`, `order_status`, `u_phone`, " +
                        "`trade_num`, `source`, `goods_id`, `goods_name`, `coupon_id`, `c_time`, `u_time`, `remark`, `is_hidden`) " +
                        "VALUES ";
                insertSql += "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                statement = connection.prepareStatement(insertSql);
                for (int j = 0; j < 10; j++) {
                    statement.setString(1, "1");
                    statement.setInt(2, 11);
                    statement.setString(3, "test");
                    statement.setString(4, "111");
                    statement.setInt(5, 1);
                    statement.setInt(6, 2);
                    statement.setString(7, "1883823823");
                    statement.setInt(8, 12);
                    statement.setString(9, "h5");
                    statement.setInt(10, 1);
                    statement.setString(11, "asdadsad");
                    statement.setInt(12, 1);
                    statement.setString(13, "2020-11-29 19:02:07");
                    statement.setString(14, "2020-11-29 19:02:07");
                    statement.setString(15, "1");
                    statement.setInt(16, 1);
                    statement.addBatch();
                }
                statement.executeBatch();
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
