package com.istimeless.securitycore.validate.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageCode {
    
    private BufferedImage image;
    
    private String code;
    
    private LocalDateTime expireTime;
    
    public ImageCode(BufferedImage image, String code, int expireIn){
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
