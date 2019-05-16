package com.istimeless.securitycore.properties;

import lombok.Data;

/**
 * @author lijiayin
 */
@Data
public class ImageCodeProperties {
    
    private int width = 67;
    private int height = 23;
    private int length = 4;
    private int expireIn = 60;
    private String url;
}
