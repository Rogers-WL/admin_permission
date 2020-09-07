package com.rog.authority.configuration.securityconfiguration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description: //WebSecurityConfigurerAdapter是Spring Security Config内置提供的一个WebSecurityConfigurer抽象实现类。
 * WebSecurityConfigurerAdapter存在的目的是提供一个方便开发人员配置WebSecurity的基类。它提供了一组全方位配置WebSecurity的缺省方法实现。
 * 开发人员只要继承WebSecurityConfigurerAdapter提供自己的实现类，哪怕不覆盖WebSecurityConfigurerAdapter的任何一个方法，
 * 都得到了一个配置WebSecurity的安全配置器WebSecurityConfigurer实例。但通常情况下，开发人员都有自己特定的安全配置和要求，
 * 这时候就可以在自己提供的WebSecurityConfigurerAdapter子实现类中提供自己的方法覆盖WebSecurityConfigurerAdapter相应的方法从
 * 而对WebSecurity实施定制
 * @Author:   Rogers_WL
 * @Date:     2020/5/21 17:09
 */
@EnableWebSecurity
@SpringBootConfiguration
public class WebSecurityConfiguration  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//开启登录配置
                .antMatchers("/", "/home","/swagger","swagger-ui.html").permitAll()
                .anyRequest().authenticated()//表示剩余的其他接口，登录之后就能访问
                .and()
                .formLogin()
                .loginPage("/login").permitAll().loginProcessingUrl("/doLogin")
                .and()
                .logout().permitAll();

    }
}
