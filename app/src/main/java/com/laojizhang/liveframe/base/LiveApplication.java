package com.laojizhang.liveframe.base;


import android.arch.persistence.room.Room;
import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.laojizhang.lifelibrary.utils.LogUtils;
import com.laojizhang.liveframe.db.LiveDatabase;

/**
 * 文件名称： LiveApplication
 * 作   者： guomaojian
 * 创建日期： 2017/06/07-11:59
 * 文件描述：
 * <p>
 */

public class LiveApplication extends MultiDexApplication {

    private static final java.lang.String TAG = LiveApplication.class.getSimpleName();
    public static LiveDatabase sDatabase;

    public static LiveDatabase getDatabase() {
        return sDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        sDatabase = Room.databaseBuilder(getApplicationContext(), LiveDatabase.class, "liveDatabaseTest").build();
        LogUtils.i("LiveApplication", sDatabase.toString());

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
