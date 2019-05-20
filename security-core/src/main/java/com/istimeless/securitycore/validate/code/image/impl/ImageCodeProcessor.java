package com.istimeless.securitycore.validate.code.image.impl;

import com.istimeless.securitycore.common.Constant;
import com.istimeless.securitycore.validate.code.image.ImageCode;
import com.istimeless.securitycore.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author lijiayin
 */
@Component
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
        ImageIO.write(validateCode.getImage(), Constant.ImageType.JPEG, request.getResponse().getOutputStream());
    }
}
