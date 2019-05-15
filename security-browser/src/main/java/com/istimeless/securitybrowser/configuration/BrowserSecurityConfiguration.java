package com.istimeless.securitybrowser.configuration;

import com.istimeless.securitybrowser.authentication.MyAuthenticationFailureHandler;
import com.istimeless.securitybrowser.authentication.MyAuthenticationSuccessHandler;
import com.istimeless.securitycore.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;


/**
 * @author lijiayin
 */
@Configuration
@EnableWebSecurity
public class BrowserSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Resource
    private SecurityProperties securityProperties;
    
    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    
    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/error", securityProperties.getBrowser().getLoginPage())
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
                
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailsService);
//    }
    
}
