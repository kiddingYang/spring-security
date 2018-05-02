package com.gs.start.srcurity.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Administrator on 2018/5/2.
 * 自定义决策器
 *
 */
public class CustomAccessDecision implements AccessDecisionManager {

    private Logger logger = LoggerFactory.getLogger(CustomAccessDecision.class);

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        logger.debug("access:");

    }

    @Override
    public boolean supports(ConfigAttribute attribute) {

        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return false;
    }
}
