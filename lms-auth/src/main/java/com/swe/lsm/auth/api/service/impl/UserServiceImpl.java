package com.swe.lsm.auth.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.swe.lsm.auth.api.config.AppConfig;
import com.swe.lsm.auth.api.config.TokenConfig;
import com.swe.lsm.auth.api.constant.DTOConst;
import com.swe.lsm.auth.api.constant.LmsConst;
import com.swe.lsm.auth.api.dto.CommonTokenDTO;
import com.swe.lsm.auth.api.service.IUserService;
import com.swe.lsm.auth.api.util.ExceptionUtil;
import com.swe.lsm.auth.api.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service("UserService")
public class UserServiceImpl implements IUserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private JWTVerifier tokenVerifier = null;

    private Algorithm tokenAlgo = null;

    private TokenConfig tokenConfig;

    private AppConfig appConfig;

    @Autowired
    public UserServiceImpl(final AppConfig appConfig, final TokenConfig tokenConfig) {
        this.appConfig = appConfig;
        this.tokenConfig = tokenConfig;
        log.info(tokenConfig.getKey());
        tokenAlgo = Algorithm.HMAC256(tokenConfig.getKey());
        tokenVerifier = JWT.require(tokenAlgo).withIssuer(tokenConfig.getIssuer()).build();
    }

    private String getDomainName(String domain) {
        String[] str = domain.split("\\.");
        if (str.length == 3) {
            StringBuilder sbDomain = new StringBuilder();
            sbDomain.append(str[1]);
            sbDomain.append(".");
            sbDomain.append(str[2]);
            return sbDomain.toString();
        }
        return domain;
    }

    private void setCookies(String domain, String userId, String roleCd, String token, int expiry) {

        HttpServletResponse httpResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        domain = getDomainName(domain);

        Cookie userCookie = new Cookie(appConfig.getCookie_user(), userId);
        userCookie.setMaxAge(expiry);
        userCookie.setPath(LmsConst.COOKIE_PATH);
        userCookie.setDomain(domain);
        httpResponse.addCookie(userCookie);

        Cookie roleCookie = new Cookie(appConfig.getCookie_role(), roleCd);
        roleCookie.setMaxAge(expiry);
        roleCookie.setPath(LmsConst.COOKIE_PATH);
        roleCookie.setDomain(domain);
        httpResponse.addCookie(roleCookie);

        Cookie tokenCookie = new Cookie(appConfig.getCookie_token(), token);
        tokenCookie.setMaxAge(expiry);
        tokenCookie.setPath(LmsConst.COOKIE_PATH);
        tokenCookie.setDomain(domain);
        httpResponse.addCookie(tokenCookie);
    }

    @Override
    public ResponseEntity<?> verify(String domain, String token) {
        try {
            if (StringUtils.isEmpty(token)) {
                return ResponseUtil.createBadRequest("Verify token failed. Token can't be NULL or EMPTY.");
            }
            CommonTokenDTO tokenDTO = decodeToken(token);
            setCookies(domain, tokenDTO.getUser_id(), tokenDTO.getRole_cd(), token, 600);
            return ResponseUtil.createOK(tokenDTO, "TOKEN IS VERIFIED SUCCESSFULLY");
        } catch (Exception ex) {
            String errMessage = ExceptionUtil.getStackTrace(ex);
            log.error(errMessage);
            log.error("verify - error token value [{}]", token);
            return ResponseUtil.createUnauthorize("DECODE THE TOKEN FAILED");
        }
    }
    private CommonTokenDTO decodeToken(String token) throws Exception {
        DecodedJWT jwt = null;
        try {
            jwt = this.tokenVerifier.verify(token);
        } catch (Exception ex) {
            log.error("TOKEN [{}] GOT ISSUE.", token);
            throw ex;
        }

        CommonTokenDTO tokenDTO = new CommonTokenDTO();

        tokenDTO.setUser_id(jwt.getClaim(DTOConst.ID).asString());
        tokenDTO.setRole_cd(jwt.getClaim(DTOConst.ROLE_CD).asString());
        tokenDTO.setCre_dt(jwt.getClaim(DTOConst.CRE_DT).asLong());

        return tokenDTO;
    }
}
