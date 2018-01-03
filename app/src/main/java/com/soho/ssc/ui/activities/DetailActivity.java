package com.soho.ssc.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.soho.ssc.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author dell
 * @data 2018/1/3.
 */
public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.tv_content)
    TextView tvContent;
    private Intent intent;
    private String desc;
    private String titleContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        intent = getIntent();
        if(intent != null){
            titleContent = intent.getStringExtra("title");
            desc = intent.getStringExtra("detail");
        }

        titleTv.setText(titleContent);
        tvContent.setText(desc);
    }

    @OnClick(R.id.back)
    public void onClick() {
    }
}
