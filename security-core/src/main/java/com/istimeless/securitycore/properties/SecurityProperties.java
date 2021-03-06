package com.istimeless.securitycore.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lijiayin
 */
@Data
@Component
@ConfigurationProperties(prefix = "istimeless.security")
public class SecurityProperties {
    
    private BrowserProperties browser = new BrowserProperties();
    
    private ValidateCodeProperties code = new ValidateCodeProperties();
    
    private SocialProperties social = new SocialProperties();
    
}
