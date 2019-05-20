package com.istimeless.securitycore.validate.code;

import com.istimeless.securitycore.properties.SecurityProperties;
import com.istimeless.securitycore.validate.code.image.impl.ImageCodeGenerator;
import com.istimeless.securitycore.validate.code.sms.SmsCodeSender;
import com.istimeless.securitycore.validate.code.sms.impl.DefaultSmsCodeSender;
import com.istimeless.securitycore.validate.code.sms.impl.SmsCodeGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author lijiayin
 */
@Configuration
public class ValidateCodeBeanConfig {
    
    @Resource
    private SecurityProperties securityProperties;
    
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeGenerator")
    public SmsCodeGenerator smsCodeGenerator(){
        SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
        smsCodeGenerator.setSecurityProperties(securityProperties);
        return smsCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }
}
