package com.swe.lms.admin.api.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {
    public static Map<String, Object> parseQueryParam(String query, String paramDelim)
    {
        if (query == null) {
            return new HashMap<String, Object>();
        }

        Map<String, Object> map = new HashMap<>();

        String[] queryList = query.split(paramDelim);
        for (String queryItem : queryList)
        {
            if (queryItem.length() > 0) {
                String[] kv = queryItem.split("=");
                map.put(kv[0], kv[1]);
            }
        }
        return map;
    }
}
