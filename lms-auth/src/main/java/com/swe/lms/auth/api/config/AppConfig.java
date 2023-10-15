package com.swe.lms.auth.api.config;

import com.swe.lms.auth.api.constant.HTTPConst;
import com.swe.lms.auth.api.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@RefreshScope
@EnableAutoConfiguration
public class AppConfig implements WebMvcConfigurer, Filter {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${swe.lms.auth.api.cookie_token}")
    private String cookie_token;

    @Value("${swe.lms.auth.api.cookie_user}")
    private String cookie_user;

    @Value("${swe.lms.auth.api.cookie_role}")
    private String cookie_role;

    public AppConfig() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String originHeader = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", originHeader);
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Content-disposition, origin, token,cat-id, unzip-yn, Access-Control-Allow-Headers, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "Authorization, responseType");

        if (request.getMethod().equalsIgnoreCase(HTTPConst.METHOD_OPTIONS)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        try {
            chain.doFilter(req, res);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
    }

    public String getCookie_token() {
        return cookie_token;
    }

    public void setCookie_token(String cookie_token) {
        this.cookie_token = cookie_token;
    }

    public String getCookie_user() {
        return cookie_user;
    }

    public void setCookie_user(String cookie_user) {
        this.cookie_user = cookie_user;
    }

    public String getCookie_role() {
        return cookie_role;
    }

    public void setCookie_role(String cookie_role) {
        this.cookie_role = cookie_role;
    }
}

