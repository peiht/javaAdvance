package com.javaAdvance.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用hikari连接池
 *
 * @author hitopei
 */
public class HikariOperate {

    private static final String username = "root";
    private static final String password = "root";
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull";

    public static void main(String[] args) {
        hikari();
    }

    public static void hikari() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        HikariDataSource hikariDataSource = new HikariDataSource(config);
        Connection connection = null;
        Statement statement = null;
        try {
            connection = hikariDataSource.getConnection();
            statement = connection.createStatement();
//            String sql = "select * from user_acct_hht";
//            statement.executeQuery(sql);
//            ResultSet set = statement.getResultSet();
//            System.out.println("查询结果：");
//            while (set.next()) {
//                int id = set.getInt(1);
//                System.out.println(id);
//            }
//
//            //改
//            String updateSql = "update user_acct_hht t set t.platformCustomerNo = 4321 where t.userId = 1111";
//            statement.executeUpdate(updateSql);
//
//            //删
//            String deleteSql = "delete from user_acct_hht where userId = 1111";
//            statement.execute(deleteSql);

            //增
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000_000; i++) {
                String insertSql = "INSERT INTO `test`.`goods_order`(`order_name`, `u_id`, `u_name`, `trade_amount`, `trade_type`, `order_status`, `u_phone`, `trade_num`, `source`, `goods_id`, `goods_name`, `coupon_id`, `c_time`, `u_time`, `remark`, `is_hidden`) VALUES ('1', 11, 'test', '111', 1, 2, '1883823823', 12, 'h5', 1, 'asdadsad', 1, '2020-11-29 19:02:07', '2020-11-29 19:02:07', '1', 1);";
                long start1 = System.currentTimeMillis();
                statement.execute(insertSql);
                long end1 = System.currentTimeMillis();
                System.out.println("1条数据执行时间" + (end1 - start1));
            }
            long end = System.currentTimeMillis();
            System.out.println("执行成功, 毫秒数: " + (end - start));
            System.out.println("秒数 ： " + (end - start) / 1000);
            System.out.println("执行成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
