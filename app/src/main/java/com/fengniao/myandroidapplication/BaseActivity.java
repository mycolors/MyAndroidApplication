package com.fengniao.myandroidapplication;

import com.fengniao.mlibs.activity.DevBaseActivity;

import butterknife.ButterKnife;


public class BaseActivity extends DevBaseActivity {

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }
}
