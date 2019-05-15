package com.istimeless.securitybrowser.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lijiayin
 * 不加密
 */
public class NoPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return StringUtils.equals(charSequence.toString(), s);
    }
}
