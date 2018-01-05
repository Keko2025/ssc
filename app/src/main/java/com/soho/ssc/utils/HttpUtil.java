
package com.soho.ssc.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.soho.ssc.MyApplication;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mummyding on 15-11-22.<br>
 * Utils for Post Data via network and get network state of cell phone.
 * @author MummyDing
 * @version Leisure 1.0
 */
public class HttpUtil {
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    public static boolean isWIFI = true;
    static{
//        mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
//        mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
//        mOkHttpClient.setWriteTimeout(30, TimeUnit.SECONDS);
    }
    /**
     * 该不会开启异步线程。
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException {
        return mOkHttpClient.newCall(request).execute();
    }
    /**
     * 开启异步线程访问网络
     * @param request
     * @param responseCallback
     */
    public static void enqueue(Request request, Callback responseCallback){
        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }
    /**
     * 开启异步线程访问网络, 且不在意返回结果（实现空callback）
     * @param request
     */
    public static void enqueue(Request request){
        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {}

            @Override
            public void onResponse(Call call, Response response) throws IOException {}
        });
    }

    public static boolean readNetworkState() {

        ConnectivityManager cm = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
            isWIFI = (cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI);
            return true;
        } else {

            return false;
        }
    }
}
