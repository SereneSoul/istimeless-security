package com.istimeless.securitycore.controller;

import com.istimeless.securitycore.common.Constant;
import com.istimeless.securitycore.validate.code.ImageCode;
import com.istimeless.securitycore.validate.code.ValidateCodeGenerator;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lijiayin
 */
@RestController
public class ValidateCodeController {
    
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    
    @Resource(name = "imageCodeGenerator")
    private ValidateCodeGenerator validateCodeGenerator;
    
    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = validateCodeGenerator.generate(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), Constant.Session.SESSION_KEY_IMAGE_CODE, imageCode);
        ImageIO.write(imageCode.getImage(), Constant.ImageType.JPEG, response.getOutputStream());
    }
}
