package com.istimeless.securitycore.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author lijiayin
 */
public class IsTimelessSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;
    
    public IsTimelessSpringSocialConfigurer(String filterProcessesUrl){
        this.filterProcessesUrl = filterProcessesUrl;
    }
    
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
