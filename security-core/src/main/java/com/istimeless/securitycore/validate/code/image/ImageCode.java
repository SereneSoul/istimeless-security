package com.istimeless.securitycore.validate.code.image;

import com.istimeless.securitycore.validate.code.ValidateCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

/**
 * @author lijiayin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageCode extends ValidateCode {
    
    private BufferedImage image;
    
    public ImageCode(BufferedImage image, String code, int expireIn){
        super(code, expireIn);
        this.image = image;
    }
}
