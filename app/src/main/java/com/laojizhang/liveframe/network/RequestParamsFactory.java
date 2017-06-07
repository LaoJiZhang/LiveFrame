package com.laojizhang.liveframe.network;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件名称： RequestParamsFactory
 * 作   者： guomaojian
 * 创建日期： 2017/05/27-11:53
 * 文件描述：
 * <p>
 */

public class RequestParamsFactory {

    public static Map<String, String> getDouBanTop250Params() {
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("start", "0");
        queryMap.put("count", "50");
        return queryMap;
    }
}
