package com.soho.ssc.ui.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.soho.ssc.R;
import com.soho.ssc.model.ResultBean;
import com.soho.ssc.ui.activities.SetActivity;
import com.soho.ssc.ui.activities.home.FcActivity;
import com.soho.ssc.ui.adapter.common.RecyclerCommonAdapter;
import com.soho.ssc.ui.adapter.common.RecyclerViewHolder;
import com.soho.ssc.utils.Constants;
import com.soho.ssc.utils.L;
import com.soho.ssc.utils.OkHttpUtil;
import com.soho.ssc.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author dell
 * @data 2018/1/4.
 */

public class PlFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.recycler_recommend)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.img_setting)
    ImageView setImg;
    private View rootView;
    private Context mContext;
    private LinearLayoutManager layoutManager;
    private RecyclerCommonAdapter<ResultBean.DataBean> adapter;
    private ArrayList<ResultBean.DataBean> lotteryList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_tab_home, container, false);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setImg.setVisibility(View.VISIBLE);
        titleTv.setText("排列5开奖公告");
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);
        refreshLayout.setRefreshing(true);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerCommonAdapter<ResultBean.DataBean>(mContext, lotteryList, R.layout.item_home) {
            public List<Integer> codeList = new ArrayList<>();
            public RecyclerCommonAdapter codeAdapter;
            public RecyclerView codeRecyclerView;

            @Override
            public void convert(RecyclerViewHolder holder, final ResultBean.DataBean item, int position) {
                holder.getView(R.id.img_flag).setVisibility(View.VISIBLE);
                holder.setText(R.id.text_name, "排列5");
                holder.setText(R.id.tv_time, "第" + item.getExpect() + "期开奖结果");
                holder.setText(R.id.tv_date, "开奖时间：" + item.getOpentime());
                holder.setImageResource(R.id.img_flag, R.drawable.lottery_pailie5);


                String data = lotteryList.get(position).getOpencode();
                String[] arr = data.split(",");
                codeList.clear();
                for (int i = 0; i < arr.length; i++) {
                    codeList.add(Integer.parseInt(arr[i]));
                }
                codeRecyclerView = (RecyclerView) holder.getView(R.id.recycler_num);
                LinearLayoutManager subLayoutManager = new LinearLayoutManager(mContext);
                subLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                codeRecyclerView.setLayoutManager(subLayoutManager);
                codeAdapter = new RecyclerCommonAdapter<Integer>(mContext, codeList, R.layout.item_code) {
                    @Override
                    public void convert(RecyclerViewHolder holder, Integer item, int position) {
                        holder.setText(R.id.tv_code, String.valueOf(item));
                    }
                };
                codeRecyclerView.setAdapter(codeAdapter);
                holder.setOnClickListener(R.id.rl_rootview, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext,FcActivity.class).putExtra("title","排列5")
                                .putExtra("time",item.getExpect())
                                .putExtra("date",item.getOpentime())
                                .putExtra("flag",2));

                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        new OkHttpUtil().get(Constants.BASE_URL + "pl5-" + 20 + ".json", new OkHttpUtil.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                refreshLayout.setRefreshing(false);
                L.e("load data:" + data);
                ResultBean bean = new Gson().fromJson(data, ResultBean.class);
                if (bean != null) {
                    lotteryList.addAll(bean.getData());
                    adapter.notifyDataSetChanged();
                    SpUtil.save("tab","set_list",bean);     //缓存bean对象
                }
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                refreshLayout.setRefreshing(false);
            }
        });
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

    @OnClick(R.id.img_setting)
    public void onClick() {
        startActivity(new Intent(mContext,SetActivity.class));
    }
}

