package com.istimeless.securitycore.validate.code;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lijiayin
 */
public interface ValidateCodeGenerator {

    ImageCode generate(HttpServletRequest request);
}
