package com.soho.ssc.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.soho.ssc.R;
import com.soho.ssc.db.PlayHisDao;
import com.soho.ssc.domain.HistoryVideo;
import com.soho.ssc.ui.listener.SampleListener;
import com.soho.ssc.ui.view.LandLayoutVideo;
import com.soho.ssc.utils.L;
import com.soho.ssc.utils.NetWorkHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author dell
 * @data 2018/1/9.
 */

public class VideoDetailActivity extends AppCompatActivity {
    @BindView(R.id.detail_player)
    LandLayoutVideo detailPlayer;
    @BindView(R.id.id_framelayout)
    FrameLayout idFramelayout;
    @BindView(R.id.toolbar_tab)
    TabLayout toolbarTab;
    private Intent intent;
    private int videoId;
    private String videoTitle;
    private String videoUrl;
    private String videoPoster;
    private SimpleDraweeView imageView;
    private OrientationUtils orientationUtils;
    private boolean isPlay;
    private boolean isPause;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            setContentView(R.layout.activity_video_detail);
            ButterKnife.bind(this);

            intent = getIntent();
            if(intent != null){
                videoId = intent.getIntExtra("videoId",0);
                videoTitle = intent.getStringExtra("name");
                videoUrl = intent.getStringExtra("url");
                videoPoster = intent.getStringExtra("photo_url");
            }

            initVideo();

        }
    }

    private void initVideo() {
        detailPlayer.setEnlargeImageRes(R.drawable.video_fullscreen);
        detailPlayer.setShrinkImageRes(R.drawable.exit_video_fullscreen);
        //添加封面图
        imageView = new SimpleDraweeView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageURI(Uri.parse(videoPoster != null ? videoPoster : ""));

        resolveNormalVideoUI();

        orientationUtils = new OrientationUtils(this, detailPlayer);//外部辅助的旋转，帮助全屏
        orientationUtils.setEnable(false);//初始化不打开外部的旋转
        detailPlayer.startWindowFullscreen(VideoDetailActivity.this, true, true);

        GSYVideoOptionBuilder gsyVideoOption = new GSYVideoOptionBuilder();
        gsyVideoOption
                .setThumbImageView(imageView)
                .setIsTouchWiget(true)
                .setNeedShowWifiTip(true)
                .setRotateViewAuto(false)
                .setShowFullAnimation(false)
                .setLockLand(false)
                .setNeedLockFull(true)
                .setSeekRatio(10)
                .setUrl(videoUrl)
                .setCacheWithPlay(false)
                .setVideoTitle(videoTitle)
                .setThumbPlay(true)
                .setStandardVideoAllCallBack(new SampleListener() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        Debuger.printfError("***** onPrepared **** " + objects[0]);
                        Debuger.printfError("***** onPrepared **** " + objects[1]);
                        super.onPrepared(url, objects);
                        //开始播放了才能旋转和全屏
                        orientationUtils.setEnable(true);
                        isPlay = true;
                            addHisData();
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                        Debuger.printfError("***** onEnterFullscreen **** " + objects[0]);//title
                        Debuger.printfError("***** onEnterFullscreen **** " + objects[1]);//当前全屏player
                    }

                    @Override
                    public void onAutoComplete(String url, Object... objects) {
                        super.onAutoComplete(url, objects);
                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo();
                        }
                        detailPlayer.getBottomView().setVisibility(View.GONE);
                        detailPlayer.getStartImg().setVisibility(View.GONE);
                    }

                    @Override
                    public void onClickStartError(String url, Object... objects) {
                        super.onClickStartError(url, objects);
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        Debuger.printfError("***** onQuitFullscreen **** " + objects[0]);//title
                        Debuger.printfError("***** onQuitFullscreen **** " + objects[1]);//当前非全屏player
//                        if (orientationUtils != null) {
//                            orientationUtils.backToProtVideo();
//                        }
                        finish();
                    }
                })
                .setLockClickListener(new LockClickListener() {
                    @Override
                    public void onClick(View view, boolean lock) {
                        if (orientationUtils != null) {
                            //配合下方的onConfigurationChanged
                            orientationUtils.setEnable(!lock);

                            detailPlayer.getCurrentPlayer().setRotateViewAuto(!lock);
                        }
                    }
                }).build(detailPlayer);
        if (NetWorkHelper.isWifi(getApplicationContext())) {
            detailPlayer.startPlayLogic();
            L.e("wifi:" + "播放");
        }
        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(VideoDetailActivity.this, true, true);
            }
        });
    }

    private void addHisData() {
        HistoryVideo his = new HistoryVideo();
        his.setId(videoId);
        his.setName(videoTitle);
        his.setPoster(videoPoster);
        his.setCurrent(detailPlayer.getCurrentPositionWhenPlaying());
        his.setUpdated_time(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(System.currentTimeMillis())));
        PlayHisDao.insert(his); //插入数据
    }

    private void resolveNormalVideoUI() {
        detailPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        detailPlayer.getTitleTextView().setText(videoTitle != null ? videoTitle : "");
        detailPlayer.getBackButton().setVisibility(View.VISIBLE);
        detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private GSYVideoPlayer getCurPlay() {
        if (detailPlayer.getFullWindowPlayer() != null) {
            return detailPlayer.getFullWindowPlayer();
        }
        return detailPlayer;
    }
    @Override
    protected void onResume() {
        getCurPlay().onVideoResume();
        super.onResume();
        isPause = false;
        //记录页面启动时间点
    }

    @Override
    protected void onPause() {
        getCurPlay().onVideoPause();
        super.onPause();
        isPause = true;
        //记录页面退出时间点
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isPlay){
            getCurPlay().release();
        }
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
    }

}
