package com.bcs.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    @Autowired
    private Environment env ;

    @Autowired
    private DataSource dataSource;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
/*
        http
                .exceptionHandling().authenticationEntryPoint(new AuthException())
                .and()
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/tokens/anular").permitAll()
                .antMatchers("/api/backend/**").hasAnyAuthority("Super_Administrador")
                //.anyRequest().authenticated()
                .and().cors()
                .configurationSource(configurationSource());*/


        /*http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/account/listar","/api/account/ver/{id}"
                        ,"/api/historical/listar", "/api/historical/transaction/{accountId}").hasAnyRole("ADMIN","USER")
                .antMatchers("/api/deposit/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/withdrawal/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated().and().cors()
                .configurationSource(configurationSource());*/

        http
                .exceptionHandling().authenticationEntryPoint(new AuthException())
                .and()
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/tokens/anular/**" ).permitAll();



    }




}


