package com.istimeless.securitycore.validate.code.sms;

/**
 * @author lijiayin
 */
public interface SmsCodeSender {
    
    void send(String phone, String code);
}
