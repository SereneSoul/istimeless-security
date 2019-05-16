package com.istimeless.securitycore.validate.code;

import com.istimeless.securitycore.properties.SecurityProperties;
import com.istimeless.securitycore.validate.code.impl.ImageCodeGenerator;
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
}
