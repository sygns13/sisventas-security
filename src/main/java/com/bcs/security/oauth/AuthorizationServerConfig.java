package com.bcs.security.oauth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import java.util.List;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private CustomTokenEnhancer customTokenEnhancer;

    @Autowired
    private DataSource dataSource;

    @Value("${security.jwt.client-id}")
    private String clientId;

    @Value("${security.jwt.client-secret}")
    private String clientSecret;

    @Value("${security.jwt.grant-type1}")
    private String grantType1;

    @Value("${security.jwt.grant-type2}")
    private String grantType2;

    @Value("${security.jwt.scope-read}")
    private String scopeRead;

    @Value("${security.jwt.scope-write}")
    private String scopeWrite;

    @Value("${security.jwt.scope-delete}")
    private String delete;

    @Value("${security.signing-key}")
    private String signingKey;

    @Value("${security.jwt.resource-ids}")
    private String resourceIds;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // TODO Auto-generated method stub
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // TODO Auto-generated method stub

        clients.inMemory().withClient(clientId)
                .secret(passwordEncoder.encode(clientSecret)).scopes(scopeRead,scopeWrite, delete)	//Clave del cliente	angular
                .authorizedGrantTypes(grantType1,grantType2)
                .refreshTokenValiditySeconds(18000)
                .accessTokenValiditySeconds(3600);

/*
        clients.jdbc(this.dataSource).withClient(clientId)
                .secret(passwordEncoder.encode(clientSecret)).scopes(scopeRead,scopeWrite, delete)	//Clave del cliente	angular
                .authorizedGrantTypes(grantType1,grantType2)
                .refreshTokenValiditySeconds(10)
                .accessTokenValiditySeconds(1800);*/
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // TODO Auto-generated method stub
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(List.of(customTokenEnhancer, accessTokenConverter()));
        endpoints.authenticationManager(authenticationManager)
                .reuseRefreshTokens(false)
                .tokenEnhancer(tokenEnhancerChain)
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter());
        super.configure(endpoints);
    }

    @Bean
    public JdbcTokenStore tokenStore() {

        //return new JwtTokenStore(accessTokenConverter()); //EN MEMORIA
        return new JdbcTokenStore(this.dataSource); //EN BASE DE DATOS
    }
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(signingKey); //firma del microservicio para el api gateway
        return tokenConverter ;
    }



}

