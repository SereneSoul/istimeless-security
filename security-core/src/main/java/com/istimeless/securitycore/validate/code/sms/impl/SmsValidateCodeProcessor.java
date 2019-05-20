package com.istimeless.securitycore.validate.code.sms.impl;

import com.istimeless.securitycore.validate.code.ValidateCode;
import com.istimeless.securitycore.validate.code.impl.AbstractValidateCodeProcessor;
import com.istimeless.securitycore.validate.code.sms.SmsCodeSender;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;

/**
 * @author lijiayin
 */
@Component
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Resource
    private SmsCodeSender smsCodeSender;
    
    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        String code = validateCode.getCode();
        smsCodeSender.send(mobile, code);
    }
}
