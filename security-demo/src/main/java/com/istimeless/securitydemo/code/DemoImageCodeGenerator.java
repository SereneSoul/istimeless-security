package com.istimeless.securitydemo.code;

import com.istimeless.securitycore.validate.code.ImageCode;
import com.istimeless.securitycore.validate.code.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lijiayin
 */
@Slf4j
@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(HttpServletRequest request) {
        log.info("自定义图形验证码");
        return null;
    }
}
