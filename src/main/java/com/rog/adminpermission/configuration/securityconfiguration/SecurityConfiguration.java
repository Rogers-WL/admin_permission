package com.rog.adminpermission.configuration.securityconfiguration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/6/21 15:49
 **/
@SpringBootConfiguration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//开启登录配置
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()//表示剩余的其他接口，登录之后就能访问
                .and()
                .formLogin()
                .loginPage("/login").permitAll().loginProcessingUrl("/doLogin")
                .and()
                .logout().permitAll();

    }
}
