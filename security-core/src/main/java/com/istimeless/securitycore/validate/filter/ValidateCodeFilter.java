package com.istimeless.securitycore.validate.filter;

import com.istimeless.securitycore.common.SecurityConstants;
import com.istimeless.securitycore.common.ValidateCodeType;
import com.istimeless.securitycore.properties.SecurityProperties;
import com.istimeless.securitycore.validate.code.ValidateCodeProcessorHolder;
import com.istimeless.securitycore.validate.exception.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lijiayin
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
    
    /**
     * 存放所有需要检验码的url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    /**
     * 路径匹配
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 系统配置信息
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 系统中的校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 自定义失败处理
     */
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);
    }

    /**
     * 向urlMap中添加url
     * @param urlString
     * @param type
     */
    private void addUrlToMap(String urlString, ValidateCodeType type) {
        if(StringUtils.isNotBlank(urlString)){
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            Arrays.stream(urls).forEach(url -> urlMap.put(url, type));
        }
    }
    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        ValidateCodeType type = getValidateCodeType(request);
        if(type != null){
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                .validate(new ServletWebRequest(request, response));
            }catch (ValidateCodeException e){
                myAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if(StringUtils.equalsIgnoreCase(request.getMethod(), "post")){
            Set<String> urls = urlMap.keySet();
            for(String url : urls){
                if(pathMatcher.match(url, request.getRequestURI())){
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }
}
