package com.fengniao.myandroidapplication.net;

import com.fengniao.myandroidapplication.BuildConfig;

/**
 * Created by a1 on 2017/2/14.
 */

public interface FNHttpClient {

    class Server {
        static final String SERVER_DOMIN;
        //是否开启支付一分
        static final boolean PAY_ONE_PERCENT;
        static final String URL_IMAGE_TOP;
        static final String URL_UPLOAD_IMAGE;
        static final String URL_ALIPAY_NOTIFY;
        static final String URL_WECHATPAY_NOTIFY;

        static {
//            if ("release".equals(BuildConfig.BUILD_TYPE)){
            //正式服务器版本
            SERVER_DOMIN = "http://xdzn.365os.com/index.php?c=api&m=";
            PAY_ONE_PERCENT = BuildConfig.DEBUG;
            URL_IMAGE_TOP = "http://kyk.api.yunfengapp.com:6634/";
            URL_UPLOAD_IMAGE = "http://kyk.api.yunfengapp.com:6634/File/UploadFile/";
            URL_ALIPAY_NOTIFY = "http://api.ljhn.yfapp.net:8412/controllers/payAli.aspx";
            URL_WECHATPAY_NOTIFY = "http://api.ljhn.yfapp.net:8412/Controllers/payWechat.aspx";

//            } else {
            //测试服务器版本
//            }
        }
    }

    String SERVER_DOMIN = Server.SERVER_DOMIN;
    boolean PAY_ONE_PERCENT = Server.PAY_ONE_PERCENT;

    //接口地址

}
