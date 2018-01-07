package com.soho.ssc.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.soho.ssc.R;
import com.soho.ssc.utils.DataCleanManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author dell
 * @data 2018/1/3.
 */
public class SetActivity extends AppCompatActivity {
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.back)
    ImageView titleBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        setCacheData();
        titleTv.setText("设置");
        titleBack.setVisibility(View.VISIBLE);
    }
    /**
     * 计算图片缓存大小给控件赋值
     */
    private void setCacheData() {
        try {
            tvCache.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.back, R.id.clear_cache, R.id.current_vision})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.clear_cache:
                DataCleanManager.clearAllCache(this);
                tvCache.setText("0K");

                break;
            case R.id.current_vision:
                Toast.makeText(this, "当前已是最新版本", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
