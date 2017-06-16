package com.laojizhang.liveframe.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.laojizhang.liveframe.db.dao.UserDao;
import com.laojizhang.liveframe.db.table.User;

/**
 * 文件名称： LiveDatabase
 * 作   者： guomaojian
 * 创建日期： 2017/06/10-01:03
 * 文件描述：
 * <p>
 */

@Database(entities = {User.class}, version = 1)
public abstract class LiveDatabase extends RoomDatabase {

    static final String DATABASE_NAME = "live_db";

    public abstract UserDao userDao();
}
