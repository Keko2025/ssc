package com.soho.ssc.ui.fragment.find;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.soho.ssc.R;
import com.soho.ssc.model.NewsBean;
import com.soho.ssc.ui.adapter.common.RecyclerCommonAdapter;
import com.soho.ssc.ui.adapter.common.RecyclerViewHolder;
import com.soho.ssc.utils.OkHttpUtil;
import com.soho.ssc.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author dell
 * @data 2018/1/5.
 */

public class FroFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private Activity mContext;
    private View rootView;
    @BindView(R.id.recycler_recommend)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private LinearLayoutManager layoutManager;
    private List<NewsBean.ResultBean> newsList = new ArrayList<>();
    private RecyclerCommonAdapter<NewsBean.ResultBean> adapter;
    private NewsBean newsBean;

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
        initData();
    }

    private void initView() {
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
        adapter = new RecyclerCommonAdapter<NewsBean.ResultBean>(mContext, newsList, R.layout.item_news) {
            @Override
            public void convert(RecyclerViewHolder holder, final NewsBean.ResultBean item, int position) {
                holder.setText(R.id.tv_title,item.getTitle());
                holder.setText(R.id.tv_hot,"热度："+item.getReplies_count()+"");
                holder.setNewsFrescoImg(R.id.news_img, Uri.parse(item.getImage_info().getUrl()));
            }
        };
        adapter.setOnItemClickListener(new RecyclerCommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(mContext,NewDescActivity.class)
                        .putExtra("url",newsList.get(position).getUrl())
                        .putExtra("img_url",newsList.get(position).getImage_info().getUrl()));
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        new OkHttpUtil().get("https://www.guokr.com/apis/minisite/article.json?retrieve_type=by_channel&channel_key=frontier", new OkHttpUtil.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                refreshLayout.setRefreshing(false);
                newsBean = new Gson().fromJson(data,NewsBean.class);
                newsList.clear();
                if(newsBean != null && newsBean.getResult().size() > 0){
                    newsList.addAll(newsBean.getResult());
                    adapter.notifyDataSetChanged();
                    SpUtil.save("tab","find_list",newsBean);
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
        }, 3000);
    }
}


