package com.soho.ssc.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soho.ssc.R;
import com.soho.ssc.ui.fragment.find.FroFragment;
import com.soho.ssc.ui.fragment.find.NewsFragment;
import com.soho.ssc.ui.fragment.find.TecFragment;
import com.soho.ssc.ui.fragment.home.FactFragment;
import com.soho.ssc.ui.fragment.home.ReviewFragment;
import com.soho.ssc.ui.fragment.home.VisualFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author dell
 * @data 2017/12/31.
 */

public class FindFragment extends Fragment {
    @BindView(R.id.toolbar_tab)
    TabLayout toolbarTab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.title_tv)
    TextView titleTv;
    private View rootView;
    private Activity mContext;
    private List<Fragment> list = new ArrayList<Fragment>();
    private NewsFragment newsFragment;
    private TecFragment tecFragment;
    private FroFragment froFragment;
    private FactFragment factFragment;
    private VisualFragment visualFragment;
    private ReviewFragment reviewFragment;

    private FragmentPagerAdapter viewPageAdapter;
    private List<String> titles = new ArrayList<>();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_find_tab, container, false);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        titleTv.setText("热点新闻");
    }

    private void initData() {
        if(newsFragment == null){
            newsFragment = new NewsFragment();
        }
        if(tecFragment == null){
            tecFragment = new TecFragment();
        }
        if(froFragment == null){
            froFragment = new FroFragment();
        }
        if(visualFragment == null){
            visualFragment = new VisualFragment();
        }
        if(factFragment == null){
            factFragment = new FactFragment();
        }
        if(reviewFragment == null){
            reviewFragment = new ReviewFragment();
        }

        list.add(froFragment);
        list.add(tecFragment);
        list.add(newsFragment);
        list.add(visualFragment);
        list.add(factFragment);
        list.add(reviewFragment);

        viewPageAdapter = new FragmentPagerAdapter(this.getChildFragmentManager()) {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }
        };
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(viewPageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(toolbarTab));
        toolbarTab.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }
}
