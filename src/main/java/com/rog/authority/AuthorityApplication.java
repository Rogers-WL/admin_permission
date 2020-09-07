package com.rog.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Rogers_WL
 */
//引入tk.mybatis下的注解
@SpringBootApplication
//可以被以下几个注解代替：SpringBootConfiguration 表示 Spring Boot 的配置注解，
// EnableAutoConfiguration 表示自动配置，ComponentScan 表示 Spring Boot 扫描 Bean 的规则，比如扫描哪些包。
@MapperScan({"com.rog.authority.dao"})
@EnableTransactionManagement
public class AuthorityApplication {

    @Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager){
        System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        return new Object();
    }
    public static void main(String[] args) {
        SpringApplication.run(AuthorityApplication.class, args);
    }

}
