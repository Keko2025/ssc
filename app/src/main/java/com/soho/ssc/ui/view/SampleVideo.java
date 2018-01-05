package com.soho.ssc.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.soho.ssc.R;
import com.soho.ssc.utils.SwitchVideoModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/4/12.
 */

public class SampleVideo extends StandardGSYVideoPlayer {
    private ImageView photo;
    private ImageView collect;
    private ImageView share;

    private TextView mMoreScale;

    private TextView mSwitchSize;

    private List<SwitchVideoModel> mUrlList = new ArrayList<>();
    //记住切换数据源类型
    private int mType = 0;
    private int mTransformSize = 0;
    //数据源
    private int mSourcePosition = 0;
    /**
     * 1.5.0开始加入，如果需要不同布局区分功能，需要重载
     */
    public SampleVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public SampleVideo(Context context) {
        super(context);
    }

    public SampleVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        initView();
    }

    private void initView() {
        mMoreScale = (TextView) findViewById(R.id.moreScale);
        mSwitchSize = (TextView) findViewById(R.id.switchSize);

        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT);
        //切换清晰度
        mMoreScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mType == 0) {
                    mType = 1;
                    mMoreScale.setText("16:9");
                    GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_16_9);
                    if (mTextureView != null) {
                        mTextureView.requestLayout();
                    }
                } else if (mType == 1) {
                    mType = 2;
                    mMoreScale.setText("4:3");
                    GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_4_3);
                    if (mTextureView != null) {
                        mTextureView.requestLayout();
                    }
                } else if (mType == 2) {
                    mType = 0;
                    mMoreScale.setText("默认比例");
                    GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT);
                    if (mTextureView != null) {
                        mTextureView.requestLayout();
                    }
                }
            }
        });

        //切换视频清晰度
        mSwitchSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSwitchDialog();
            }
        });
    }

    /**
     * 设置播放URL
     * @param url           播放url
     * @param cacheWithPlay 是否边播边缓存
     * @param title         title
     * @return
     */
    public boolean setUp(List<SwitchVideoModel> url, boolean cacheWithPlay, String title) {
        mUrlList = url;
        return setUp(url.get(mSourcePosition).getUrl(), cacheWithPlay, title);
    }
    /**
     * 设置播放URL
     *
     * @param url           播放url
     * @param cacheWithPlay 是否边播边缓存
     * @param cachePath     缓存路径，如果是M3U8或者HLS，请设置为false
     * @param title         title
     * @return
     */
    public boolean setUp(List<SwitchVideoModel> url, boolean cacheWithPlay, File cachePath, String title) {
        mUrlList = url;
        return setUp(url.get(mSourcePosition).getUrl(), cacheWithPlay, cachePath, title);
    }

    @Override
    public int getLayoutId() {
        return R.layout.sample_video_normal;
    }

    /**
     * 全屏时将对应处理参数逻辑赋给全屏播放器
     *
     * @param context
     * @param actionBar
     * @param statusBar
     * @return
     */
    @Override
    public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
        SampleVideo sampleVideo = (SampleVideo) super.startWindowFullscreen(context, actionBar, statusBar);
        sampleVideo.mSourcePosition = mSourcePosition;
        sampleVideo.mType = mType;
        sampleVideo.mTransformSize = mTransformSize;
        sampleVideo.mUrlList = mUrlList;
        //sampleVideo.resolveTransform();
//        sampleVideo.resolveTypeUI();
        //sampleVideo.resolveRotateUI();
        //这个播放器的demo配置切换到全屏播放器
        //这只是单纯的作为全屏播放显示，如果需要做大小屏幕切换，请记得在这里耶设置上视频全屏的需要的自定义配置
        //比如已旋转角度之类的等等
        //可参考super中的实现
        return sampleVideo;
    }

    /**
     * 弹出切换清晰度
     */
    private void showSwitchDialog() {
        Toast.makeText(mContext, "切换清晰度稍后开放", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void updateStartImage() {
        ImageView imageView = (ImageView) mStartButton;
        if (mCurrentState == CURRENT_STATE_PLAYING) {
            imageView.setImageResource(R.drawable.video_click_pause_selector);
        } else if (mCurrentState == CURRENT_STATE_ERROR) {
            imageView.setImageResource(R.drawable.video_click_play_selector);
        } else {
            imageView.setImageResource(R.drawable.video_click_play_selector);
        }
    }
}
