package com.laojizhang.liveframe.network;

import java.io.Serializable;

/**
 * 文件名称： DouBanResponse
 * 作   者： guomaojian
 * 创建日期： 2017/05/27-11:54
 * 文件描述：
 * <p>
 */

public class DouBanResponse<D extends Object> implements Serializable {
    private Integer count;
    private Integer start;
    private Integer total;
    private D subjects;
    private String title;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public D getSubjects() {
        return subjects;
    }

    public void setSubjects(D subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "DouBanResponse{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", subjects=" + subjects +
                ", title='" + title + '\'' +
                '}';
    }
}
