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
 *
 * WebSecurityConfigurerAdapter自身是一个WebSecurityConfigurer,它在自己的初始化方法init()中创建了HttpSecurity http安全构建器对象，
 * 并在缺省情况下(disableDefaults为false)应用了如下HttpSecurity初始配置:
 * @Author:   Rogers_WL
 * @Date:     2020/5/21 17:09
 */
@EnableWebSecurity
@SpringBootConfiguration
public class WebSecurityConfiguration  extends WebSecurityConfigurerAdapter {




    /**
     * 安全过滤器链配置方法  用来配置 HttpSecurity 。 HttpSecurity 用于构建一个安全过滤器链 SecurityFilterChain 。SecurityFilterChain
     * 最终被注入核心过滤器。HttpSecurity 有许多我们需要的配置。我们可以通过它来进行自定义安全访问策略
     * @Description 提供可覆盖实现的空方法void configure(HttpSecurity http)，允许开发人员配置目标HttpSecurity;这里该方法缺省的实现对HttpSecurity
     * 的安全配置如下:
     * 对任何请求要求用户已认证(通俗地讲，用户必须先登录才能访问任何资源);
     * 启用用户名密码表单登录认证机制;
     * 启用Http Basic认证机制;
     *
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
     *
     * AuthenticationManagerBuilder 类是AuthenticationManager的建造者, 我们只需要向这个类中, 配置用户信息, 就能生成对应的AuthenticationManager,
     * 这个类也提过,是用户身份的管理者, 是认证的入口, 因此,我们需要通过这个配置,想security提供真实的用户身份。
     * 如果我们是使用UserDetailsService来配置用户身份的话, 这段配置改为如下；
     * dbUserDetailsService就是你自己写的类, 这个类的作用就是去获取用户信息,比如从数据库中获取。 这样的话,AuthenticationManager在认证用户身份信息的时候，
     * 就回从中获取用户身份,和从http中拿的用户身份做对比。
     *
     * UserDetails 相关的它都管，包含 PasswordEncoder 密码机
     * @author Rogers
     * @Date  2020/9/8 9:42
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        //auth.userDetailsService(dbUserDetailsService);
    }


    /**
     *  核心过滤器配置方法
     * @Description 提供可覆盖实现的空方法void configure(WebSecurity web)，允许开发人员覆盖实现配置WebSecurity,比如设置哪些URL要忽略安全等等；
     * 通过覆盖实现该方法，开发人员可以定制WebSecurity,主要是除了HttpSecurity之外的安全控制，比如忽略某些静态公开资源或者动态公开资源的安全 ,
     * 设置需要使用的防火墙实例，设置权限评估器，安全表达式处理器等;
     *
     * WebSecurity 是基于 Servlet Filter 用来配置 springSecurityFilterChain 。而 springSecurityFilterChain 又被委托给了 Spring Security
     * 核心过滤器 Bean DelegatingFilterProxy 。 相关逻辑你可以在 WebSecurityConfiguration 中找到。我们一般不会过多来自定义 WebSecurity ,
     * 使用较多的使其ignoring() 方法用来忽略 Spring Security 对静态资源的控制。
     * @author Rogers
     * @Date  2020/9/8 9:45
     */
    @Override
    public void configure(WebSecurity web) {

    }
}
