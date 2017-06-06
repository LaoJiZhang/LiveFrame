package com.laojizhang.liveframe;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.laojizhang.lifelibrary.model.BaseLifeModel;
import com.laojizhang.lifelibrary.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名称： NewModel
 * 作   者： guomaojian
 * 创建日期： 2017/05/27-14:42
 * 文件描述：
 * <p>
 */

public class NewModel extends BaseLifeModel<String> {

    private int start = 0;
    private List<Integer> mIntegers = new ArrayList<>();
    private Runnable mCountDownRunnable = new Runnable() {
        @Override
        public void run() {
            start++;
            LogUtils.i("AAA", String.valueOf(start));
            LogUtils.i("AAA", mIntegers.toString());
            getLiveData().setValue(String.valueOf(start));
            getHandler().postDelayed(this, 1000);
        }
    };
    private MutableLiveData<List<Integer>> mListLiveData;

    public NewModel(Application application) {
        super(application);
        mIntegers.add(1);
        mIntegers.add(2);
        mIntegers.add(3);
        mIntegers.add(4);
        mIntegers.add(5);
        getHandler().postDelayed(mCountDownRunnable, 5000);
    }

    @Override
    public void onStop() {
        super.onStop();
        for (int i = 0, size = mIntegers.size(); i < size; i++) {
            mIntegers.set(i, mIntegers.get(i) + 1);
        }
        LogUtils.i("AAA", "STOP : " + mIntegers.toString());
    }

    public MutableLiveData<ArrayList<Integer>> bindListData() {
        return bindLiveData(new ArrayList<Integer>());
    }
}
