package com.istimeless.securitybrowser.configuration;

import com.istimeless.securitycore.authentication.AbstractChannelSecurityConfig;
import com.istimeless.securitycore.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.istimeless.securitycore.common.SecurityConstants;
import com.istimeless.securitycore.properties.SecurityProperties;
import com.istimeless.securitycore.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * @author lijiayin
 */
@Configuration
@EnableWebSecurity
public class BrowserSecurityConfiguration extends AbstractChannelSecurityConfig {
    
    @Resource
    private SecurityProperties securityProperties;
    
    @Autowired
    private UserDetailsService myUserDetailsService;
    
    @Resource
    private DataSource dataSource;
    
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    
    @Autowired
    private SpringSocialConfigurer istimelessSocialSecurityConfig;
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        applyPasswordAuthenticationConfig(http);
        
        http.apply(validateCodeSecurityConfig)
                .and()
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            .apply(istimelessSocialSecurityConfig)
                .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(myUserDetailsService)
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .and()
            .authorizeRequests()
                .antMatchers(
                        "/error",
                        SecurityConstants.DEFAULT_REQUIRE_AUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .csrf()
                .disable();
    }
}
