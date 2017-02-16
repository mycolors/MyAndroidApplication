package com.fengniao.myandroidapplication.net;

/**
 * Created by a1 on 2017/2/14.
 */

public interface FNAjaxCallback {
    void onReceiveData(String function, String data, String msg);

    void onReceiveError(String funcation, int errorCode, String msg);
}
