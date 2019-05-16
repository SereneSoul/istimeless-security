package com.istimeless.securitycore.validate.filter;

import com.istimeless.securitycore.common.Constant;
import com.istimeless.securitycore.properties.SecurityProperties;
import com.istimeless.securitycore.validate.code.ImageCode;
import com.istimeless.securitycore.validate.exception.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Sets;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

/**
 * @author lijiayin
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    
    private Set<String> urls = Sets.newHashSet();
    
    private AntPathMatcher pathMatcher = new AntPathMatcher();
    
    private SecurityProperties securityProperties;

    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        Collections.addAll(urls, StringUtils.splitByWholeSeparatorPreserveAllTokens(
                securityProperties.getCode().getImage().getUrl(), ","));
        urls.add(Constant.Login.LOGIN_PROCESSING_URL);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        boolean action = false;
        for(String url : urls){
            if(pathMatcher.match(url, request.getRequestURI())){
                action = true;
            }
        }
        if(action){
            try {
                validate(new ServletWebRequest(request));
            }catch (ValidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, 
                Constant.Session.SESSION_KEY_IMAGE_CODE);
        
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        
        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码不能为空");
        }
        
        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }
        
        if(codeInSession.isExpired()){
            sessionStrategy.removeAttribute(request, Constant.Session.SESSION_KEY_IMAGE_CODE);
            throw new ValidateCodeException("验证码已过期");
        }
        
        if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)){
            throw new ValidateCodeException("验证码错误");
        }
        
        sessionStrategy.removeAttribute(request, Constant.Session.SESSION_KEY_IMAGE_CODE);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
