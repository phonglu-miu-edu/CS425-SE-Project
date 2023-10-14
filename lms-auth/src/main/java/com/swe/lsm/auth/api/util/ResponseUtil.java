package com.swe.lsm.auth.api.util;

import com.swe.lsm.auth.api.constant.HTTPConst;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    public static ResponseEntity<?> createUnauthorize(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put(HTTPConst.MESSAGE, message);
        map.put(HTTPConst.STATUS_CODE, HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    }

    public static ResponseEntity<?> createOK(Object objData, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put(HTTPConst.STATUS_CODE, HttpStatus.OK.value());
        map.put(HTTPConst.MESSAGE, message);
        map.put(HTTPConst.DATA, objData);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public static ResponseEntity<?> createOK(Object objData) {
        Map<String, Object> map = new HashMap<>();
        map.put(HTTPConst.STATUS_CODE, HttpStatus.OK.value());
        map.put(HTTPConst.DATA, objData);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public static ResponseEntity<?> createBadRequest(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put(HTTPConst.MESSAGE, message);
        map.put(HTTPConst.STATUS_CODE, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
