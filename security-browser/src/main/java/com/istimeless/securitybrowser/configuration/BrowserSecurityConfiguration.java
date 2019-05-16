package com.istimeless.securitybrowser.configuration;

import com.istimeless.securitybrowser.authentication.MyAuthenticationFailureHandler;
import com.istimeless.securitybrowser.authentication.MyAuthenticationSuccessHandler;
import com.istimeless.securitycore.common.Constant;
import com.istimeless.securitycore.properties.SecurityProperties;
import com.istimeless.securitycore.validate.filter.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();
        
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl(Constant.Login.LOGIN_PROCESSING_URL)
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/error", "/code/image", securityProperties.getBrowser().getLoginPage())
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
                
    }
}
