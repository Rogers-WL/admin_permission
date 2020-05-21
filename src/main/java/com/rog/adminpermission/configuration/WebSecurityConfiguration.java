package com.rog.adminpermission.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description: //
 * @Author:   Rogers_WL
 * @Date:     2020/5/21 17:09
 */
@EnableWebSecurity
@SpringBootConfiguration
public class WebSecurityConfiguration  implements WebSecurityConfigurer {
    @Override
    public void init(SecurityBuilder builder) throws Exception {

    }

    @Override
    public void configure(SecurityBuilder builder) throws Exception {

    }
}
