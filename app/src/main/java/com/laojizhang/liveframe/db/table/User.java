package com.laojizhang.liveframe.db.table;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * 文件名称： User
 * 作   者： guomaojian
 * 创建日期： 2017/06/10-01:04
 * 文件描述：
 * <p>
 */

@Entity(tableName = "user", indices = {@Index("lastName")})
public class User {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "nickName")
    private String nick_name;
    @ColumnInfo(name = "lastName")
    private String last_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
