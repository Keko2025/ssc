package com.soho.ssc.ui.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.soho.ssc.R;
import com.soho.ssc.model.DataBean;
import com.soho.ssc.model.SscBean;
import com.soho.ssc.utils.L;
import com.soho.ssc.utils.OkHttpUtil;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author dell
 * @data 2017/12/29.
 */

public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.rl_splash)
    RelativeLayout rlSplash;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        startAnim();
        initData();
    }

    private void initData() {
        new OkHttpUtil().get("http://vipapp.01appddd.com/Lottery_server/get_init_data.php?"+"type=android&&appid=20180101",
                new OkHttpUtil.HttpCallback() {
                    @Override
                    public void onSuccess(String data) {
                        L.e("data:"+data);
                        SscBean bean = new Gson().fromJson(data,SscBean.class);
                        String result = new String(Base64.decode(bean.getData().getBytes(),Base64.DEFAULT));
                        L.e("entoStr:" + result);
                        if(!result.isEmpty()){
                            DataBean dataBean = new Gson().fromJson(result,DataBean.class);
                            if(dataBean.getShow_url().endsWith("1")){
                                startActivity(new Intent(SplashActivity.this,WebViewActivity.class).putExtra("url",dataBean.getUrl()));
                            }
                        }
                    }
                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        L.e("data:"+msg);
                    }
                });
    }

    @Override
    protected void onStart() {
        //调用配置
        init();
        super.onStart();
    }

    private void init() {
        int flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        //判断当前版本在4.0以上并且存在虚拟按键，否则不做操作
        if (Build.VERSION.SDK_INT < 19 || !checkDeviceHasNavigationBar()) {
            //一定要判断是否存在按键，否则在没有按键的手机调用会影响别的功能。如之前没有考虑到，导致图传全屏变成小屏显示。
            return;
        } else {
            // 获取属性
            rlSplash.setSystemUiVisibility(flag);
        }
    }
    /**
     * 判断是否存在虚拟按键
     *
     * @return
     */
    public boolean checkDeviceHasNavigationBar() {
        boolean hasNavigationBar = false;
        Resources rs = getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }

    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
    private void startAnim() {
        AlphaAnimation alpha = new AlphaAnimation(0, 1);// 渐变动画,从完全透明到完全不透明
        alpha.setDuration(200); // 持续时间 2 秒
        alpha.setFillAfter(true);// 动画结束后，保持动画状态

        // 设置动画监听器
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
            // 动画结束时回调此方法
            @Override
            public void onAnimationEnd(Animation animation) {
                // 跳转到下一个页面
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        });
        // 启动动画
        rlSplash.startAnimation(alpha);
    }
}
