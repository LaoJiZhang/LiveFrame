package com.laojizhang.liveframe;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.laojizhang.lifelibrary.ui.fragment.BaseLifeFragment;
import com.laojizhang.lifelibrary.utils.LogUtils;
import com.laojizhang.liveframe.databinding.FragmentNewLayoutBinding;

import java.util.List;

/**
 * 文件名称： NewFragment
 * 作   者： guomaojian
 * 创建日期： 2017/05/27-14:36
 * 文件描述：
 * <p>
 */

public class NewFragment extends BaseLifeFragment<FragmentNewLayoutBinding, NewModel> {

    public static final String TAG = NewFragment.class.getSimpleName();

    @Override
    protected FragmentUiDelegate createUiDelegate() {
        return new FragmentUiDelegate() {
            @Override
            public int getContentId() {
                return R.layout.fragment_new_layout;
            }

            @Override
            public void initFragment(FragmentNewLayoutBinding binding) {
                binding.btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "aaaa", Toast.LENGTH_SHORT).show();
                    }
                });
                subscribeUi(getModel().bindListData(), new Observer<List<Integer>>() {
                    @Override
                    public void onChanged(@Nullable List<Integer> integers) {
                        LogUtils.i("AAAA", integers.toString());
                    }
                });
            }

            @Override
            public Class<NewModel> getClazz() {
                return NewModel.class;
            }
        };
    }

    @Override
    protected Observer<String> createDefaultObserver() {
        return new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Integer var = Integer.valueOf(s);
                if (var == 5) {
                    showLoadingPage();
                } else if (var == 10) {
                    showContentView();
                } else if (var == 15) {
                    showErrorPage();
                } else if (var == 20) {
                    showEmptyPage();
                }
                getContentBinding().btn.setText(s);
            }
        };
    }

    @Override
    public View createOwnLoadingView() {
        TextView textView = new TextView(getActivity());
        textView.setText("Newloading");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public View createOwnEmptyView() {
        TextView textView = new TextView(getActivity());
        textView.setText("Newemtpy");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public View createOwnErrorView() {
        TextView textView = new TextView(getActivity());
        textView.setText("NewError");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
