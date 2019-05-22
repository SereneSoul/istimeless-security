package com.istimeless.securitybrowser.controller;

import com.istimeless.securitycore.dto.SimpleResponse;
import com.istimeless.securitycore.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lijiayin
 */
@Slf4j
@RestController
@RequestMapping("/authentication")
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Resource
    private SecurityProperties securityProperties;
    /**
     * 当需要身份认证时跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null){
            String target = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是：{}", target);
            if(StringUtils.endsWithIgnoreCase(target, ".html")){
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("访问的服务需要身份验证，请引导用户到登录页");
    }
}
