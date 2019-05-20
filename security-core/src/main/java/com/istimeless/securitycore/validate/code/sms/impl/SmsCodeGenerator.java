package com.istimeless.securitycore.validate.code.sms.impl;

import com.istimeless.securitycore.properties.SecurityProperties;
import com.istimeless.securitycore.validate.code.ValidateCode;
import com.istimeless.securitycore.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lijiayin
 */
public class SmsCodeGenerator implements ValidateCodeGenerator {
    
    private SecurityProperties securityProperties;
    
    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }
}
