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

import com.soho.ssc.R;
import com.soho.ssc.ui.fragment.home.PlFragment;
import com.soho.ssc.ui.fragment.home.QxFragment;
import com.soho.ssc.utils.TabUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author dell
 * @data 2018/1/4.
 */

public class HomeFragment extends Fragment {
    @BindView(R.id.toolbar_tab)
    TabLayout toolbarTab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private View rootView;
    private Activity mContext;
    private List<Fragment> list = new ArrayList<Fragment>();
    private HomeTabsFragment homeTabsFragment;
    private QxFragment qxcFragment;
    private PlFragment peilieFragment;
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
            rootView = inflater.inflate(R.layout.fragment_home, container, false);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initTab() {
        toolbarTab.post(new Runnable() {
            @Override
            public void run() {
                TabUtil.setIndicator(toolbarTab, 60, 60);
            }
        });
    }

    private void initData() {
        initTab();
        if(homeTabsFragment == null){
            homeTabsFragment = new HomeTabsFragment();
        }
//        if(qxcFragment == null){
//            qxcFragment = new QxFragment();
//        }
        if(peilieFragment == null){
            peilieFragment = new PlFragment();
        }
        list.add(homeTabsFragment);
//        list.add(qxcFragment);
        list.add(peilieFragment);

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
