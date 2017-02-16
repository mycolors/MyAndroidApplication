package com.fengniao.myandroidapplication.list;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.fengniao.mlibs.activity.DevBaseRecyclerViewActivity;
import com.fengniao.myandroidapplication.net.FNHttpClientImpl;

/**
 * Created by a1 on 2017/2/16.
 */

public abstract class BaseRecyclerViewActivity extends DevBaseRecyclerViewActivity {
    FNHttpClientImpl mFNHttpClientImpl;
    int sKip = 1, mMax = 10;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mFNHttpClientImpl = FNHttpClientImpl.getInstance();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected boolean showHomeAsUp() {
        return true;
    }

    protected ProgressDialog mProgressDialog;

    public void showProgressDialog(String msg) {
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    public void cancelProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
        }
    }

    public void showToast(int resId) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), resId, Toast.LENGTH_SHORT).show();
        }
    }

    public void showToast(CharSequence text) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }
    }

}
