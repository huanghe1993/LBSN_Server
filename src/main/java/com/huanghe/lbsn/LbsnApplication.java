package com.huanghe.lbsn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.huanghe.lbsn.Mapper")
public class LbsnApplication {

    public static void main(String[] args) {
        SpringApplication.run(LbsnApplication.class, args);
    }

}

