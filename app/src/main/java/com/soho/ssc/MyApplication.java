package com.soho.ssc;

import android.app.Application;
import android.graphics.Bitmap;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.tencent.bugly.Bugly;

import cn.jpush.android.api.JPushInterface;


/**
 * @author dell
 * @data 2017/12/31.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initFresco();

        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);

        Bugly.init(this, "5acae9829e", false);  //TODO,发布时改 false
    }
    private void initFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .setResizeAndRotateEnabledForNetwork(true)
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .build();
        Fresco.initialize(this, config);
    }

}
