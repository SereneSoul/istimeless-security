package com.istimeless.securitycore.properties;

import lombok.Data;

/**
 * @author lijiayin
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties{
    
    public ImageCodeProperties(){
        setLength(4);
    }
    
    private int width = 67;
    private int height = 23;
}
