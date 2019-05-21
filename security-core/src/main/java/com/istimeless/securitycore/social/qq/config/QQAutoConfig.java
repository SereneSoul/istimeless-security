package com.istimeless.securitycore.social.qq.config;

import com.istimeless.securitycore.properties.QQProperties;
import com.istimeless.securitycore.properties.SecurityProperties;
import com.istimeless.securitycore.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;

/**
 * @author lijiayin
 */
@Configuration
@ConditionalOnProperty(prefix = "istimeless.security.social.qq",name = "app-id")
public class QQAutoConfig extends SocialConfigurerAdapter {
    
    @Autowired
    private SecurityProperties securityProperties;
    
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        super.addConnectionFactories(connectionFactoryConfigurer, environment);
        QQProperties qqProperties = securityProperties.getSocial().getQq();
        connectionFactoryConfigurer.addConnectionFactory(new QQConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(), qqProperties.getAppSecret()));
    }
}
