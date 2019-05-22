package com.istimeless.securityapp.config;

import com.istimeless.securitycore.authentication.AbstractChannelSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author lijiayin
 */
@Configuration
public class AppSecurityConfig extends AbstractChannelSecurityConfig {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}
