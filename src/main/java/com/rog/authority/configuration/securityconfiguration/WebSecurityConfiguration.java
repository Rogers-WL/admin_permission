package com.rog.authority.configuration.securityconfiguration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description: WebSecurityConfigurerAdapter是Spring Security Config内置提供的一个WebSecurityConfigurer抽象实现类。
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


    /**
     * @Description 提供可覆盖实现的空方法void configure(HttpSecurity http)，允许开发人员配置目标HttpSecurity;这里该方法缺省的实现对HttpSecurity
     * 的安全配置如下:
     * 对任何请求要求用户已认证(通俗地讲，用户必须先登录才能访问任何资源);
     * 启用用户名密码表单登录认证机制;
     * 启用Http Basic认证机制;
     * 自定义实现，每个模块配置使用and结尾。
     * authorizeRequests()配置路径拦截，表明路径访问所对应的权限，角色，认证信息。
     * formLogin()对应表单认证相关的配置
     * logout()对应了注销相关的配置
     * httpBasic()可以配置basic登录
     * @author Rogers
     * @Date  2020/9/8 9:38
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**", "/signup", "/about").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .failureForwardUrl("/login?error")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .permitAll()
                .and()
                .httpBasic()
                .disable();


    }


    /**
     * @Description 提供可覆盖实现的方法void configure(AuthenticationManagerBuilder auth) ,允许开发人员配置目标WebSecurity
     * 所使用的AuthenticationManager的双亲AuthenticationManager；该方法缺省实现的效果是该双亲AuthenticationManager来自
     * AuthenticationConfiguration定义的AuthenticationManager(其实是来自IoC容器的类型为AuthenticationManager的一个bean);
     * 通过覆盖实现该方法，开发人员可以定制认证机制，比如设置成基于内存的认证，基于数据库的认证，基于LDAP的认证，甚至这些认证机制的一个组合，
     * 设置AuthenticationManager的双亲关系，所使用的PasswordEncoder等等；
     * @author Rogers
     * @Date  2020/9/8 9:42
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

    }


    /**
     * @Description 提供可覆盖实现的空方法void configure(WebSecurity web)，允许开发人员覆盖实现配置WebSecurity,比如设置哪些URL要忽略安全等等；
     * 通过覆盖实现该方法，开发人员可以定制WebSecurity,主要是除了HttpSecurity之外的安全控制，比如忽略某些静态公开资源或者动态公开资源的安全 ,
     * 设置需要使用的防火墙实例，设置权限评估器，安全表达式处理器等;
     * @author Rogers
     * @Date  2020/9/8 9:45
     */
    @Override
    public void configure(WebSecurity web) {

    }
}
