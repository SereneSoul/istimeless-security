package com.istimeless.securitycore.properties;

import com.istimeless.securitycore.common.LoginType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lijiayin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrowserProperties {
    
    private String loginPage = "/formLogin.html";
    
    private LoginType loginType = LoginType.JSON;
    
    private Integer rememberMeSeconds = 3600;
}
