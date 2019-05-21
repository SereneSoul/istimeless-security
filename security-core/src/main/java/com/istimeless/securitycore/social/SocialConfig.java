package com.istimeless.securitycore.social;

import com.istimeless.securitycore.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author lijiayin
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private SecurityProperties securityProperties;
    
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        return repository;
    }
    
    @Bean
    public SpringSocialConfigurer isTimelessSocialSecurityConfig(){
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        IsTimelessSpringSocialConfigurer configurer = new IsTimelessSpringSocialConfigurer(filterProcessesUrl);
        return configurer;
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }
}
