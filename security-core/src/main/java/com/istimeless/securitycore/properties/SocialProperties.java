package com.istimeless.securitycore.properties;

import lombok.Data;

/**
 * @author lijiayin
 */
@Data
public class SocialProperties {
    
    String filterProcessesUrl = "/auth";
    
    private QQProperties qq = new QQProperties();
}
