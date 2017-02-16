package com.fengniao.myandroidapplication.net;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.InputFilter;
import android.text.TextUtils;

import org.xutils.http.RequestParams;

/**
 * Created by a1 on 2017/2/14.
 */

public class FNHttpClientImpl implements FNHttpClient {
    private static final String TAG = "FNHttpClient";
    private static FNHttpClientImpl mFNHttpClientImpl;
    String token;
    private Context context;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private FNHttpClientImpl() {
    }

    public static FNHttpClientImpl getInstance() {
        if (mFNHttpClientImpl == null) {
            mFNHttpClientImpl = new FNHttpClientImpl();
        }
        return mFNHttpClientImpl;
    }

    /**
     * 获取渠道名
     *
     * @param context 此处习惯性设为activity，实际上context也可以
     * @return 如果没有获取成功，那么返回值为空
     */
    public static String getChannelName(Context context) {
        if (context == null) return null;
        String channelName = null;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            //注意此处是ApplicationInfo而不是ActivityInfo，因为友盟设置的meta-data是在application标签中，
            //而不是某activity 标签中，所以用ApplicationInfo
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(),
                        PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelName = applicationInfo.metaData.getString("UMENG_CHANNEL");
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return channelName;
    }


    private RequestParams getBaseParams(Context context, String function) {
        RequestParams params = new RequestParams(function);
        if (!TextUtils.isEmpty(token)) {
            params.setHeader("token", token);
        }
        return params;
    }
}
