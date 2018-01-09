package com.soho.ssc.ui.fragment;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.soho.ssc.R;
import com.soho.ssc.model.VideoBean;
import com.soho.ssc.ui.activities.SetActivity;
import com.soho.ssc.ui.activities.VideoDescActivity;
import com.soho.ssc.ui.activities.VideoDetailActivity;
import com.soho.ssc.ui.adapter.common.HeaderViewRecyclerAdapter;
import com.soho.ssc.ui.adapter.common.RecyclerCommonAdapter;
import com.soho.ssc.ui.adapter.common.RecyclerViewHolder;
import com.soho.ssc.utils.L;
import com.soho.ssc.utils.OkHttpUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author dell
 * @data 2017/12/31.
 * desc: 视频api暂时省略，可以替换为自己api或者本地视频链接直接传进去播放
 */

public class VideoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
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
    private RecyclerCommonAdapter<VideoBean.DataBean.EntitiesBean> adapter;
    private ArrayList<VideoBean.DataBean.EntitiesBean> videoList = new ArrayList<>();
    private int page = 1;
    private int pageno = 10;
    private HeaderViewRecyclerAdapter mAdapter;
    private View loadMoreView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_find, container, false);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData(page);
    }
    private void initView() {
        setImg.setVisibility(View.VISIBLE);
        titleTv.setText("精选视频");
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
        adapter = new RecyclerCommonAdapter<VideoBean.DataBean.EntitiesBean>(mContext, videoList, R.layout.item_fragment_video) {
            @Override
            public void convert(RecyclerViewHolder holder, VideoBean.DataBean.EntitiesBean item, int position) {
                holder.setVideoFrescoImg(R.id.video_img, Uri.parse(item.getPoster()));
                holder.setText(R.id.video_name_tv,item.getName());
                holder.setText(R.id.video_time_tv,item.getPlayed_count() + "观看");
                holder.setText(R.id.tv_duration,item.getDuration());
            }
        };
        adapter.setOnItemClickListener(new RecyclerCommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(mContext,VideoDetailActivity.class)
                        .putExtra("name",videoList.get(position).getName())
                        .putExtra("url",videoList.get(position).getUrl())
                        .putExtra("photo_url",videoList.get(position).getPoster())
                        .putExtra("videoId",videoList.get(position).getId()));
            }
        });
        mAdapter = new HeaderViewRecyclerAdapter(adapter);
        createLoadMoreView();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && videoList.size() > 9) {
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
        if(mContext == null){
            return;
        }
        loadMoreView = LayoutInflater.from(mContext).inflate(R.layout.layout_load_more, recyclerView, false);
        mAdapter.addFooterView(loadMoreView);
        loadMoreView.setVisibility(View.GONE);
    }

    /**
     * api请求，分页加载
     * @param page
     */
    private void initData(int page) {
        //视频请求地址暂时省略
        new OkHttpUtil().get("视频url" + "?page=" + page + "&&per_page=" + pageno + "&&category=" + "doule", new OkHttpUtil.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                refreshLayout.setRefreshing(false);
                L.e("load data:" + data);
                VideoBean videoBean = new Gson().fromJson(data,VideoBean.class);
                if(videoBean != null){
                    videoList.addAll(videoBean.getData().getEntities());
                    adapter.notifyDataSetChanged();
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
                page = 1;
                initData(page);
            }
        }, 2000);
    }

    @OnClick(R.id.img_setting)
    public void onClick() {
        startActivity(new Intent(mContext,SetActivity.class));
    }
}
