package com.fengniao.mlibs.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;


public class DevBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }


    public void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) return;
        if (getActivity() != null)
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public Activity getActivity() {
        return this;
    }
}
