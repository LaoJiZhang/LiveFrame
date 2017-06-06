package com.laojizhang.liveframe.movie.ui;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.laojizhang.liveframe.R;
import com.laojizhang.liveframe.base.BaseActivity;
import com.laojizhang.liveframe.databinding.ActivityMovieLayoutBinding;
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

    @Override
    protected Observer<List<MovieEntity>> createDefaultObserver() {
        return new Observer<List<MovieEntity>>() {
            @Override
            public void onChanged(@Nullable List<MovieEntity> entities) {
                entities = null;
                if (entities != null) {
                    if (entities.size() > 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (MovieEntity entity : entities) {
                            stringBuilder.append(entity.toString());
                            stringBuilder.append("\n");
                        }
                        getContentBinding().movieContent.setText(stringBuilder.toString());
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

    @Override
    protected ActivityUiDelegate createActivityUiDelegate() {
        return new ActivityUiDelegate() {
            @Override
            public void initActivity(Bundle savedInstanceState, ActivityMovieLayoutBinding binding) {
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
