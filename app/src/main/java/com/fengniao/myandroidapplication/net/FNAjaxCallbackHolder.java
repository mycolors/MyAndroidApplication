package com.fengniao.myandroidapplication.net;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;



public class FNAjaxCallbackHolder implements org.xutils.common.Callback.CommonCallback<String> {
    public static final String TAG = "FNAjaxCallback";
    private WeakReference<Context> mContext;
    private String mFunction;

    public FNAjaxCallbackHolder(Context context, String mFunction, FNAjaxCallback mFNAjaxCallback) {
        this.mContext = new WeakReference<Context>(context);
        this.mFunction = mFunction;
        this.mFNAjaxCallback = mFNAjaxCallback;
    }

    private FNAjaxCallback mFNAjaxCallback;

    @Override
    public void onSuccess(String result) {
        Log.i(TAG, result);
        //解析具有共同数据结构的数据，提高效率
        try {
            JSONObject jsonObject = new JSONObject(result);
            int code = jsonObject.getInt("code");
            if (code == 100) {
                String data = jsonObject.get("data").toString();
                onReceiveData(data, jsonObject.getString("msg"));
            } else {
                onReceiveError(code, jsonObject.getString("msg"));
            }
        } catch (JSONException e) {
            onReceiveError(-1, "服务器数据解析失败");
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        Log.i(TAG, "onError: " + ex.toString());
        if (ex instanceof SocketTimeoutException) {
            onReceiveError(0, "连接服务器超时，请检查您的网络连接后重试");
        } else {
            onReceiveError(0, ex.toString());
        }
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }


    public void onReceiveData(String data, String msg) {
        mFNAjaxCallback.onReceiveData(mFunction, data, msg);
    }

    public void onReceiveError(int code, String msg) {
        mFNAjaxCallback.onReceiveError(mFunction, code, msg);
    }
}
