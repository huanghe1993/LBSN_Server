package com.huanghe.lbsn.durid;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DruidTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass()); //使用的是DruidDataSource的数据源
        Connection connection = dataSource.getConnection();
        System.out.println(connection); //JDBC4Connection@68dcfd52,JDBC的连接
        connection.close();
    }
}
