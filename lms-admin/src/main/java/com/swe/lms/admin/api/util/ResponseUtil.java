package com.swe.lms.admin.api.util;

import com.swe.lms.admin.api.constant.HTTPConst;
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

    public static ResponseEntity<?> createNotFound(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put(HTTPConst.STATUS_CODE, HttpStatus.NOT_FOUND.value());
        map.put(HTTPConst.MESSAGE, message);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<?> createBadRequest(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put(HTTPConst.MESSAGE, message);
        map.put(HTTPConst.STATUS_CODE, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> createInternalServerError(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put(HTTPConst.MESSAGE, message);
        map.put(HTTPConst.STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
