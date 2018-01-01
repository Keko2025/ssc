package com.soho.ssc.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.soho.ssc.R;
import com.soho.ssc.model.ResultBean;
import com.soho.ssc.ui.adapter.common.HeaderViewRecyclerAdapter;
import com.soho.ssc.ui.adapter.common.RecyclerCommonAdapter;
import com.soho.ssc.ui.adapter.common.RecyclerViewHolder;
import com.soho.ssc.utils.Constants;
import com.soho.ssc.utils.L;
import com.soho.ssc.utils.OkHttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



/**
 * @author dell
 * @data 2017/12/31.
 */

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.recycler_recommend)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private View rootView;
    private int page = 1;
    private int pageno = 10;
    private Activity mContext;
    private LinearLayoutManager layoutManager;

    List<ResultBean.DataBean> list = new ArrayList<>();
    private RecyclerCommonAdapter<ResultBean.DataBean> adapter;
    private HeaderViewRecyclerAdapter mAdapter;
    private View loadMoreView;
    private List<Integer> codeList = new ArrayList<>();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
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
        initData(pageno);
    }

    private void initView() {
        titleTv.setText("最新开奖");
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
        adapter = new RecyclerCommonAdapter<ResultBean.DataBean>(mContext,list,R.layout.item_home) {
            public RecyclerCommonAdapter codeAdapter;
            public RecyclerView codeRecyclerView;

            @Override
            public void convert(RecyclerViewHolder holder, ResultBean.DataBean item, int position) {
                holder.setText(R.id.text_name,"福彩3d");
                holder.setText(R.id.tv_time,"第" + item.getOpentime() + "期开奖结果");

                String data = item.getOpencode();
                String[] arr = data.split(",");
                for (int i = 0; i < arr.length; i++){
                    codeList.add(Integer.parseInt(arr[i]));
                }

                codeRecyclerView = (RecyclerView)holder.getView(R.id.recycler_num);
                LinearLayoutManager subLayoutManager = new LinearLayoutManager(mContext);
                subLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                codeRecyclerView.setLayoutManager(subLayoutManager);
                codeAdapter = new RecyclerCommonAdapter<Integer>(mContext, codeList, R.layout.item_code) {
                    @Override
                    public void convert(RecyclerViewHolder holder, Integer item, int position) {
                        holder.setText(R.id.tv_code,String.valueOf(item));
                    }
                };
                codeRecyclerView.setAdapter(codeAdapter);
            }
        };
        mAdapter = new HeaderViewRecyclerAdapter(adapter);
        createLoadMoreView();

        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && list.size() > 9) {
                    page++;
                    initData(page);
                    loadMoreView.setVisibility(View.VISIBLE);
                }

            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager instanceof LinearLayoutManager) {
                    lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                }
            }
        });

    }
    private void createLoadMoreView() {
        loadMoreView = LayoutInflater.from(mContext).inflate(R.layout.layout_load_more, recyclerView, false);
        mAdapter.addFooterView(loadMoreView);
        loadMoreView.setVisibility(View.GONE);
    }
    private void initData(int pageno) {
        new OkHttpUtil().get(Constants.BASE_URL + "pl3-" + pageno + ".json", new OkHttpUtil.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                refreshLayout.setRefreshing(false);
                loadMoreView.setVisibility(View.GONE);
                L.e("load data:" + data);
                ResultBean bean = new Gson().fromJson(data,ResultBean.class);
                if(bean != null){
                    list.addAll(bean.getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);

            }
        });
    }

    @OnClick(R.id.back)
    public void onClick() {
    }

    @Override
    public void onRefresh() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                initData(page);
            }
        }, 2000);
    }
}
