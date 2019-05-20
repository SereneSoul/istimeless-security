package com.istimeless.securitycore.properties;

import lombok.Data;

/**
 * @author lijiayin
 */
@Data
public class SmsCodeProperties {
    private int length = 6;
    private int expireIn = 60;
    private String url;
}
