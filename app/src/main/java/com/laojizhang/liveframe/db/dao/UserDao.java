package com.laojizhang.liveframe.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.laojizhang.liveframe.db.table.User;

import java.util.List;

/**
 * 文件名称： UserDao
 * 作   者： guomaojian
 * 创建日期： 2017/06/10-01:10
 * 文件描述：
 * <p>
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> listUsers();

    @Query("SELECT * FROM user WHERE nickName = :nickName")
    User getUser(String nickName);

    @Insert
    void addUser(User user);
}
