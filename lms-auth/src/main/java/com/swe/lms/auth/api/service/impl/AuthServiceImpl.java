package com.swe.lms.auth.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.swe.lms.auth.api.config.AppConfig;
import com.swe.lms.auth.api.config.TokenConfig;
import com.swe.lms.auth.api.constant.DTOConst;
import com.swe.lms.auth.api.constant.HTTPConst;
import com.swe.lms.auth.api.constant.LmsConst;
import com.swe.lms.auth.api.feign.IUserFeignClient;
import com.swe.lms.auth.api.service.IAuthService;
import com.swe.lms.auth.api.util.ResponseUtil;
import com.swe.lms.auth.api.dto.CommonTokenDTO;
import com.swe.lms.auth.api.dto.UserDTO;
import com.swe.lms.auth.api.util.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service("AuthService")
public class AuthServiceImpl implements IAuthService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private JWTVerifier tokenVerifier;

    private Algorithm tokenAlgo;

    private final TokenConfig tokenConfig;

    private final AppConfig appConfig;

    @Autowired
    private IUserFeignClient userFeignClient;

    @Autowired
    public AuthServiceImpl(final AppConfig appConfig, final TokenConfig tokenConfig) {
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
            return ResponseUtil.createBadRequest("DECODE THE TOKEN FAILED");
        }
    }

    private String getToken(String userId, String roleCd) {
        JWTCreator.Builder tkBuilder = JWT.create().withIssuer(tokenConfig.getIssuer());
        tkBuilder = tkBuilder.withClaim(DTOConst.ID, userId);
        tkBuilder = tkBuilder.withClaim(DTOConst.ROLE_CD, roleCd);
        tkBuilder = tkBuilder.withClaim(DTOConst.CRE_DT, System.currentTimeMillis());
        return tkBuilder.sign(this.tokenAlgo);
    }
    @Override
    public ResponseEntity<?> login(String domain, UserDTO userDTO) {
        String userName = userDTO.getUserName();
        String password = userDTO.getPassword();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return ResponseUtil.createBadRequest("The user must enter both user name and password.");
        }
        ResponseEntity<Map<String, Object>> loginUserResponse = userFeignClient.getLoginUser(userDTO);
        if (loginUserResponse.getStatusCodeValue() != HttpStatus.OK.value()) {
            return ResponseUtil.createUnauthorize("Either user name or password is incorrect. Please try again.");
        }
        Map<String, Object> mapValue = (Map<String, Object>) loginUserResponse.getBody().get(HTTPConst.DATA);
        return ResponseUtil.createOK(loginUserResponse.getBody().get(HTTPConst.DATA));
//        Optional<User> optUser = userRepository.findByUserNameAndPassword(userName, password);
//        if (optUser.isPresent()) {
//            User user = optUser.get();
//            String token = getToken(user.getUserName(), user.getRoleCd());
//            setCookies(domain, user.getUserName(), user.getRoleCd(), token, 600);
//            return ResponseUtil.createOK(UserAdapter.convertToUserDTO(user));
//        }

    }

    @Override
    public ResponseEntity<?> logout(String domain, UserDTO userDTO) {
        setCookies(domain, userDTO.getUserName(), userDTO.getRoleCd(), "", 0);
        return ResponseUtil.createOK("The user has logged out successfully.");
    }

    private CommonTokenDTO decodeToken(String token) {
        DecodedJWT jwt;
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
