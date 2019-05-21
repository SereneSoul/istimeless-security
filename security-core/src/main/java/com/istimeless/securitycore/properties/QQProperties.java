package com.istimeless.securitycore.properties;

import lombok.Data;

/**
 * @author lijiayin
 */
@Data
public class QQProperties {
    
    private String appId;
    
    private String appSecret;
    
    private String providerId = "qq";
}
