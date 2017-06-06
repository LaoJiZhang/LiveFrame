package com.laojizhang.liveframe.network;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guomaojian on 16/11/16.
 */

public class RequestParamsFactory {

    public static Map<String, String> getDouBanTop250Params() {
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("start", "0");
        queryMap.put("count", "50");
        return queryMap;
    }
}
