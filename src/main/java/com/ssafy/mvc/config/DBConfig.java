package com.ssafy.mvc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

// dao와 mapper를 연결시켜줌
@Configuration
@MapperScan(basePackages = "com.ssafy.mvc.model.dao")
public class DBConfig {
}
