package com.rog.adminpermission.configuration.webconfiguration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.rog.adminpermission.configuration.webconfiguration.interceptor.TestInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @Description: 拦截器配置，一个项目只能有一个WebMvcConfigurationSupport生效,会禁用SpringBoot对mvc的自动配置
 * @Author Rogers
 * @Date 2020/5/24 15:30
 **/
@SpringBootConfiguration
public class WebConfiguration  extends WebMvcConfigurationSupport {
    /**
     * @Description //设置拦截器的拦截路径
     * @author wangl
     * @Date  12/18/2019 10:27 AM
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new TestInterceptor())
                .addPathPatterns("/**")// 其中/**表示当前目录以及所有子目录（递归），/*表示当前目录，不包括子目录。
                .excludePathPatterns("/swagger-resources/**","/webjars/**","/v2/**","/swagger-ui.html/**")
                .excludePathPatterns("/error")//请求不匹配会默认返回error页面，当前项目没有error的路径
        ;

    }

    /**
     * @Description //静态资源访问设置
     * @author wangl
     * @Date  12/18/2019 10:27 AM
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * @Description //fastjson配置
     * @author wangl
     * @Date  12/19/2019 10:58 AM
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //4、将convert添加到converters中
        converters.add(fastConverter);
        //5、追加默认转换器
        super.addDefaultHttpMessageConverters(converters);
    }

}
