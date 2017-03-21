package com.fengniao.myandroidapplication;

import android.os.Bundle;
import android.webkit.WebView;

import com.fengniao.mlibs.activity.DevBaseWebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends DevBaseWebViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    @Override
    public WebView getWebView() {
        return (WebView) findViewById(R.id.web_view);
    }

    @Override
    public String getUrl() {
        return "https://www.baidu.com";
    }
}
