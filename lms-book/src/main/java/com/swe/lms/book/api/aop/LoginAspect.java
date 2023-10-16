package com.swe.lms.book.api.aop;

import com.swe.lms.book.api.config.AppConfig;
import com.swe.lms.book.api.feign.IAuthFeignClient;
import com.swe.lms.book.api.util.ResponseUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Component("LoginAspect")
public class LoginAspect {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final AppConfig appConfig;

    @Autowired
    private IAuthFeignClient authFeignClient;

    @Autowired
    public LoginAspect(final AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Around("execution(* com.swe.lms.book.api.controller.*.*(..))" + "&& !@annotation(com.swe.lms.book.api.aop.NoToken)")
    public Object validate(ProceedingJoinPoint pjp) throws Throwable
    {
        HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String methodName = ((MethodSignature) pjp.getSignature()).getMethod().getName();
        Object[] paramValues = pjp.getArgs();

        String token = "";

        Cookie[] cookies = httpRequest.getCookies();
        if ((null != cookies) && (cookies.length > 0)) {
            for (Cookie cookie: cookies) {
                String cookieName = cookie.getName();
                if (appConfig.getCookie_token().equals(cookieName)) {
                    token = cookie.getValue();
                }
            }
        }
        if (StringUtils.isBlank(token)) {
            log.error("ERROR. UNAUTHORIZE ACCESS - METHOD NAME [{}]", methodName);
            return ResponseUtil.createUnauthorize("Error - Unauthorize access.");
        } else {
            ResponseEntity<Map<String, Object>> authVerifyResponse = authFeignClient.verify(token);
            if (authVerifyResponse.getStatusCodeValue() != HttpStatus.OK.value()) {
                return ResponseUtil.createUnauthorize("Error - Unauthorize access.");
            }
        }
        return pjp.proceed(paramValues);
    }
}
