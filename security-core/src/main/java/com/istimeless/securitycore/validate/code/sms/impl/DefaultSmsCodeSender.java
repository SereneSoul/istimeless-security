package com.istimeless.securitycore.validate.code.sms.impl;

import com.istimeless.securitycore.validate.code.sms.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lijiayin
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String phone, String code) {
        log.info("向手机：{}， 发送验证码：{}", phone, code);
    }
}
