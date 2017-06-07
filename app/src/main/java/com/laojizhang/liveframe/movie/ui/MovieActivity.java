package com.laojizhang.liveframe.movie.ui;

import android.arch.lifecycle.Observer;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.laojizhang.lifelibrary.adapter.DatabindingRecyclerDelegate;
import com.laojizhang.lifelibrary.adapter.MultiTypeRecyclerViewAdapter;
import com.laojizhang.lifelibrary.adapter.OnRecyclerViewAttachItemClickListener;
import com.laojizhang.lifelibrary.utils.LogUtils;
import com.laojizhang.liveframe.R;
import com.laojizhang.liveframe.base.BaseActivity;
import com.laojizhang.liveframe.databinding.ActivityMovieItemLayoutBinding;
import com.laojizhang.liveframe.databinding.ActivityMovieLayoutBinding;
import com.laojizhang.liveframe.databinding.ActivityMovieListHeaderBinding;
import com.laojizhang.liveframe.movie.bean.MovieEntity;
import com.laojizhang.liveframe.movie.model.MovieModel;

import java.util.List;

/**
 * 文件名称： MovieActivity
 * 作   者： guomaojian
 * 创建日期： 2017/06/06-21:49
 * 文件描述：
 * <p>
 */

public class MovieActivity extends BaseActivity<ActivityMovieLayoutBinding, MovieModel> {

    private MultiTypeRecyclerViewAdapter<MovieEntity> mAdapter;

    @Override
    protected Observer<List<MovieEntity>> createDefaultObserver() {
        return new Observer<List<MovieEntity>>() {
            @Override
            public void onChanged(@Nullable List<MovieEntity> movieEntities) {
                if (movieEntities != null) {
                    if (movieEntities.size() > 0) {
                        getContentBinding().movieRecyclerview.setAdapter(initAdapter(movieEntities));
                        showContentView();
                    } else {
                        showEmptyPage();
                    }
                } else {
                    showErrorPage();
                }
            }
        };
    }

    private MultiTypeRecyclerViewAdapter<MovieEntity> initAdapter(List<MovieEntity> movieEntities) {
        if (mAdapter == null) {
            mAdapter = new MultiTypeRecyclerViewAdapter<MovieEntity>(getContentBinding().movieRecyclerview) {
                @Override
                public DatabindingRecyclerDelegate<MovieEntity> createProxy() {
                    return new DatabindingRecyclerDelegate<MovieEntity>() {
                        @Override
                        public int getItemLayoutResId() {
                            return R.layout.activity_movie_item_layout;
                        }

                        @Override
                        public void onBindItem(ViewDataBinding viewDataBinding, MovieEntity item, int position) {
                            ActivityMovieItemLayoutBinding binding = (ActivityMovieItemLayoutBinding) viewDataBinding;
                            binding.setMovie(item);

                            if (item.getImages() != null && !TextUtils.isEmpty(item.getImages().getLarge())) {
                                binding.movieItemPicIv.setImageURI(item.getImages().getLarge());
                            }

                            if (item.getGenres() != null) {
                                binding.movieItemType1Tv.setVisibility(View.GONE);
                                binding.movieItemType2Tv.setVisibility(View.GONE);
                                binding.movieItemType3Tv.setVisibility(View.GONE);
                                for (int i = 0, len = item.getGenres().size(); i < len; i++) {
                                    TextView textView = i == 0 ? binding.movieItemType1Tv : (i == 1 ? binding.movieItemType2Tv : binding.movieItemType3Tv);
                                    if (!TextUtils.isEmpty(item.getGenres().get(i))) {
                                        textView.setVisibility(View.VISIBLE);
                                        textView.setText(item.getGenres().get(i));
                                    }
                                }
                            }
                        }
                    };
                }
            };
            mAdapter.addHeaderView("我是header Title", new DatabindingRecyclerDelegate<String>() {
                @Override
                public int getItemLayoutResId() {
                    return R.layout.activity_movie_list_header;
                }

                @Override
                public void onBindItem(final ViewDataBinding viewDataBinding, final String item, int position) {
                    LogUtils.i("ccc", "onBindItem : " + item.toString());
                    getModel().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ((ActivityMovieListHeaderBinding) viewDataBinding).movieHeaderTv.setText(item);
                        }
                    }, 3000);
                }
            });
        }
        mAdapter.setBindingDatas(movieEntities);
        return mAdapter;
    }

    @Override
    protected ActivityUiDelegate createActivityUiDelegate() {
        return new ActivityUiDelegate() {
            @Override
            public void initActivity(Bundle savedInstanceState, ActivityMovieLayoutBinding binding) {
                binding.movieRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                binding.movieRecyclerview.setItemAnimator(new DefaultItemAnimator());
                binding.movieRecyclerview.addOnChildAttachStateChangeListener(new OnRecyclerViewAttachItemClickListener() {
                    @Override
                    public void onItemClickEvent(Object data) {
                        LogUtils.i("ccc", data.toString());
                        Toast.makeText(getActivity(), "onItemClickEvent", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClickEvent(Object data) {
                        Toast.makeText(getActivity(), "onItemLongClickEvent", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public int getContentId() {
                return R.layout.activity_movie_layout;
            }

            @Override
            public Class<MovieModel> getClazz() {
                return MovieModel.class;
            }
        };
    }

    @Override
    public View.OnClickListener createNetErrBtnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MovieActivity.this, "重试一次", Toast.LENGTH_SHORT).show();
                showEmptyPage();
            }
        };
    }
}
