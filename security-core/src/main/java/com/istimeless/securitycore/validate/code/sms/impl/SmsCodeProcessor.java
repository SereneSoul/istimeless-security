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
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Resource
    private SmsCodeSender smsCodeSender;
    
    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String phone = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "phone");
        String code = validateCode.getCode();
        smsCodeSender.send(phone, code);
    }
}
