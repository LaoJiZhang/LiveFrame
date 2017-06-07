package com.laojizhang.liveframe.base;


import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 文件名称： LiveApplication
 * 作   者： guomaojian
 * 创建日期： 2017/06/07-11:59
 * 文件描述：
 * <p>
 */

public class LiveApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
