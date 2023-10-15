package com.swe.lms.auth.api.aop;

import com.swe.lms.auth.api.config.AppConfig;
import com.swe.lms.auth.api.constant.LmsConst;
import com.swe.lms.auth.api.service.IAuthService;
import com.swe.lms.auth.api.util.ResponseUtil;
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

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component("LoginAspect")
public class LoginAspect {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AppConfig appConfig;

    @Resource(name = "AuthService")
    private IAuthService authService;

    @Autowired
    public LoginAspect(final AppConfig appConfig)
    {
        this.appConfig = appConfig;
        if (null == appConfig.getCookie_token()) {
            log.error("MISSING CONFIGURATION FOR COOKIE TOKEN");
        }
        if (null == appConfig.getCookie_user()) {
            log.error("MISSING CONFIGURATION FOR COOKIE USER");
        }
        if (null == appConfig.getCookie_role()) {
            log.error("MISSING CONFIGURATION FOR COOKIE ROLE");
        }
    }

    @Around("execution(* com.swe.lms.auth.api.controller.*.*(..))" + "&& !@annotation(com.swe.lms.auth.api.aop.NoToken)")
    public Object validate(ProceedingJoinPoint pjp) throws Throwable
    {
        HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String methodName = ((MethodSignature) pjp.getSignature()).getMethod().getName();
        Object[] paramValues = pjp.getArgs();

        String token = "";

        if (!LmsConst.AUTH_METHOD.equals(methodName) && !LmsConst.AUTH_VERIFY_METHOD.equals(methodName)) {
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
                log.error("Error. Unauthorize access - method name: {}", methodName);
                return ResponseUtil.createUnauthorize("Error. Unauthorize access.");
            } else {
                String serverName = httpRequest.getServerName();
                ResponseEntity<?> valResult = authService.verify(serverName, token);
                if (valResult.getStatusCodeValue() != HttpStatus.OK.value()) {
                    return ResponseUtil.createUnauthorize("Error. Unauthorize access.");
                }
            }
        }
        return pjp.proceed(paramValues);
    }
}
