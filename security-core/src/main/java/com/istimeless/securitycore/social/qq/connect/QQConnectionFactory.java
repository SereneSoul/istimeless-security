package com.istimeless.securitycore.social.qq.connect;

import com.istimeless.securitycore.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author lijiayin
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
