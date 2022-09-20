package com.alibaba.cloud.youxia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class ShareingConfig {
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        String sql = "SELECT * FROM t_order WHERE user_id=? AND order_id=?";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, 15);
            ps.setLong(2,3000);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }catch (SQLException e1){
            System.out.println(e1.getMessage());
        }
    }
}
