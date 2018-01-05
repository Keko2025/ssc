package com.soho.ssc.ui.activities.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soho.ssc.R;
import com.soho.ssc.model.ResultBean;
import com.soho.ssc.ui.adapter.common.HeaderViewRecyclerAdapter;
import com.soho.ssc.ui.adapter.common.RecyclerCommonAdapter;
import com.soho.ssc.ui.adapter.common.RecyclerViewHolder;
import com.soho.ssc.utils.L;
import com.soho.ssc.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author dell
 * @data 2018/1/3.
 */
public class FcActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.title_tv)
    TextView titleTv;
    ImageView imgFlag;
    TextView textName;
    TextView tvTime;
    TextView tvDate;
    RecyclerView recyclerNum;
    @BindView(R.id.recycle)
    RecyclerView recycleView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.back)
    ImageView back;
    private Intent intent;
    private String url;
    private LinearLayoutManager layoutManager;
    private RecyclerCommonAdapter<Integer> adapter;
    private List<ResultBean.DataBean> lotteryList = new ArrayList<>();
    private List<Integer> codeList = new ArrayList<>();
    private RecyclerCommonAdapter<ResultBean.DataBean> lotteryAdapter;
    private LinearLayoutManager layoutManager1;
    private HeaderViewRecyclerAdapter mAdapter;
    private View headView;
    private int flag;
    private Object object;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fucai);
        ButterKnife.bind(this);

        intent = getIntent();
        if (intent != null) {
            flag = intent.getIntExtra("flag",0);
            initview();
            initData();
            back.setVisibility(View.VISIBLE);
        }
    }

    private void initview() {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);
        refreshLayout.setRefreshing(true);

        recycleView.setHasFixedSize(true);
        layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(layoutManager1);
        lotteryAdapter = new RecyclerCommonAdapter<ResultBean.DataBean>(this, lotteryList, R.layout.item_home) {
            public List<Integer> subList = new ArrayList<>();
            public RecyclerCommonAdapter codeAdapter;
            public RecyclerView codeRecyclerView;

            @Override
            public void convert(RecyclerViewHolder holder, ResultBean.DataBean item, int position) {
                holder.getView(R.id.img_flag).setVisibility(View.GONE);
                if(flag == 0){
                    holder.setText(R.id.text_name, "福彩3D");
                }else if(flag == 1){
                    holder.setText(R.id.text_name, "7星彩");
                }else if(flag == 2){
                    holder.setText(R.id.text_name, "排列5");
                }
                holder.setText(R.id.tv_time, "第" + item.getExpect() + "期开奖结果");
                holder.setText(R.id.tv_date, "开奖时间：" + item.getOpentime());

                String data = lotteryList.get(position).getOpencode();
                String[] arr = data.split(",");
                subList.clear();
                for (int i = 0; i < arr.length; i++) {
                    subList.add(Integer.parseInt(arr[i]));
                }
                codeRecyclerView = (RecyclerView) holder.getView(R.id.recycler_num);
                LinearLayoutManager subLayoutManager = new LinearLayoutManager(mContext);
                subLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                codeRecyclerView.setLayoutManager(subLayoutManager);
                codeAdapter = new RecyclerCommonAdapter<Integer>(mContext, subList, R.layout.item_code) {
                    @Override
                    public void convert(RecyclerViewHolder holder, Integer item, int position) {
                        holder.setText(R.id.tv_code, String.valueOf(item));
                    }
                };
                codeRecyclerView.setAdapter(codeAdapter);
            }
        };
        mAdapter = new HeaderViewRecyclerAdapter(lotteryAdapter);
        recycleView.setAdapter(mAdapter);
        createHeadView();
    }

    private void createHeadView() {
        headView = LayoutInflater.from(this).inflate(R.layout.item_home, recycleView, false);
        recyclerNum = (RecyclerView) headView.findViewById(R.id.recycler_num);

        imgFlag = (ImageView) headView.findViewById(R.id.img_flag);
        textName = (TextView)headView.findViewById(R.id.text_name);
        tvTime = (TextView) headView.findViewById(R.id.tv_time);
        tvDate = (TextView) headView.findViewById(R.id.tv_date);
        mAdapter.addHeaderView(headView);

        titleTv.setText(intent.getStringExtra("title"));
        textName.setText(intent.getStringExtra("title"));
        tvTime.setText("第" + intent.getStringExtra("time") + "期开奖结果");
        tvDate.setText("开奖时间：" + intent.getStringExtra("date"));

        if(flag == 0){
            imgFlag.setImageResource(R.drawable.lottery_fucai3d);
        }else if(flag == 1){
            imgFlag.setImageResource(R.drawable.lottery_qixing);
        }else if(flag == 2){
            imgFlag.setImageResource(R.drawable.lottery_pailie5);
        }

        recyclerNum.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerNum.setLayoutManager(layoutManager);
        adapter = new RecyclerCommonAdapter<Integer>(this, codeList, R.layout.item_code) {
            @Override
            public void convert(RecyclerViewHolder holder, Integer item, int position) {
                holder.setText(R.id.tv_code, String.valueOf(item));
            }
        };
        recyclerNum.setAdapter(adapter);
    }

    private void initData() {
        if(flag == 0){
            object = SpUtil.get("tab","home_bean");
        }else if(flag == 1){
            object = SpUtil.get("tab","find_list");
        }else if(flag == 2){
            object = SpUtil.get("tab","set_list");
        }
        if(object != null) {
            refreshLayout.setRefreshing(false);
            ResultBean  resultBean = (ResultBean) object;
            if (resultBean.getData().size() > 0) {
                lotteryList.addAll(resultBean.getData());

                String result = lotteryList.get(0).getOpencode();
                String[] arr = result.split(",");
                codeList.clear();
                for (int i = 0; i < arr.length; i++) {
                    codeList.add(Integer.parseInt(arr[i]));
                    L.e("Integer.parseInt(arr[i])");
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @Override
    public void onRefresh() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }, 2000);
    }
}
