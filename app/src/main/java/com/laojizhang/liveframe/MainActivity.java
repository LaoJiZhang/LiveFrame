/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.laojizhang.liveframe;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.laojizhang.lifelibrary.ui.activity.BaseLifeActivity;
import com.laojizhang.liveframe.databinding.ActivityMainBinding;

public class MainActivity extends BaseLifeActivity<ActivityMainBinding, CommonModel> {

    @Override
    protected ActivityUiDelegate createActivityUiDelegate() {
        return new ActivityUiDelegate() {
            @Override
            public void initActivity(Bundle savedInstanceState, ActivityMainBinding binding) {
                NewFragment fragment = new NewFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, NewFragment.TAG).commit();
            }

            @Override
            public int getContentId() {
                return R.layout.activity_main;
            }

            @Override
            public Class<CommonModel> getClazz() {
                return CommonModel.class;
            }
        };
    }

    @Override
    protected Observer<?> createDefaultObserver() {
        return new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        };
    }
}
