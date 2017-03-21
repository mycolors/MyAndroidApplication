package com.fengniao.mlibs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public abstract class DevBaseWebViewActivity extends DevBaseActivity {
    WebView mWebView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mWebView = getWebView();
        url = getUrl();
        if (mWebView != null) {
            //防止跳转到外部浏览器
            mWebView.setWebViewClient(new WebViewClient());
            mWebView.setWebChromeClient(new WebChromeClient());
            WebSettings webSettings = mWebView.getSettings();
            //设置是否支持js，仅在需要的时候打开
            webSettings.setJavaScriptEnabled(true);
            // 可任意比例放大缩小
            webSettings.setUseWideViewPort(true);
            //和setUseWideViewPort一起解决网页自适应问题
            webSettings.setLoadWithOverviewMode(true);
            //是否使用缓存
            webSettings.setAppCacheEnabled(true);
            //设置启用dom存储api
            webSettings.setDomStorageEnabled(true);
            if (!TextUtils.isEmpty(url)) {
                mWebView.loadUrl(url);
            }
        }
    }

    public abstract WebView getWebView();

    public abstract String getUrl();

    @Override
        public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
    }
}
