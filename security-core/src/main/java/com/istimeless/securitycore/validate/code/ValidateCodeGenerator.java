package com.istimeless.securitycore.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author lijiayin
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}
