package com.fly.javasql.test1;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class HikariDataSourceExample {
    public static void main(String[] args) {
        // 使用HikariCP连接池（需第三方库）
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://10.19.200.150:3306/dev");
        config.setUsername("root");
        config.setPassword("Root@123456");
        config.addDataSourceProperty("cachePrepStmts", "true");

        try (HikariDataSource ds = new HikariDataSource(config);
             Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO USERS(name,email) VALUES(?,?)")) {

            conn.setAutoCommit(false); // 开启事务
            for (int i = 0; i < 10; i++) {
                stmt.setString(1, "John-"+i);
                stmt.setString(2, "john-"+i+"@example.com");
                stmt.addBatch();
            }

            int[] counts = stmt.executeBatch();
            conn.commit(); // 提交事务

            System.out.println("插入记录数: " + Arrays.stream(counts).sum());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
