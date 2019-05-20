package com.istimeless.securitycore.validate.code;

import com.istimeless.securitycore.common.ValidateCodeType;
import com.istimeless.securitycore.validate.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lijiayin
 */
@Component
public class ValidateCodeProcessorHolder {
    
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;
    
    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type){
        return findValidateCodeProcessor(type.toString());
    }
    
    public ValidateCodeProcessor findValidateCodeProcessor(String type){
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessors.get(name);
        if(validateCodeProcessor == null){
            throw new ValidateCodeException("验证码处理器" + name + "不存在！");
        }
        return validateCodeProcessor;
    }
}
