package com.soho.ssc.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.soho.ssc.R;
import com.soho.ssc.ui.fragment.FindFragment;
import com.soho.ssc.ui.fragment.HomeFragment;
import com.soho.ssc.ui.fragment.VideoFragment;
import com.soho.ssc.ui.fragment.shop.ShopFragment;
import com.soho.ssc.ui.impl.Frag2ActivImp;
import com.soho.ssc.ui.view.FragmentTabHost;
import com.soho.ssc.utils.L;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Frag2ActivImp {
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;

    private String texts[] = {"首页", "发现","商城", "精选"};
    private int imageButton[] = { R.drawable.tab_main, R.drawable.tab_find, R.drawable.tab_shop,R.drawable.tab_mine };
    private Class fragmentArray[] = { FindFragment.class, VideoFragment.class,ShopFragment.class,HomeFragment.class };

    private int previous = 0;
    private int current = 0;

    private HomeFragment homgFragment;
    private FindFragment findFragment;
    private VideoFragment videoFragment;
    long waitTime = 2000;
    long touchTime = 0;
    private ShopFragment shopFragment;

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof HomeFragment){
            homgFragment = (HomeFragment) fragment;
        }else if (fragment instanceof VideoFragment){
            videoFragment = (VideoFragment) fragment;
        }else if (fragment instanceof FindFragment){
            findFragment = (FindFragment) fragment;
        }else if (fragment instanceof ShopFragment){
            shopFragment = (ShopFragment) fragment;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        current=getIntent().getIntExtra("current", 0);
        tabhost.setup(this, getSupportFragmentManager(), R.id.frameLayout);
        for(int i = 0; i < texts.length; i++){
            TabHost.TabSpec spec = tabhost.newTabSpec(texts[i]).setIndicator(getView(i));
            tabhost.addTab(spec, fragmentArray[i], null);
            tabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        }
        L.e("current:"+current);
        tabhost.setCurrentTab(current);

        initEvent();
    }
    private void initEvent() {
        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                L.e(""+tabId);
                int index = 0;
                current = index;
                for(String s : texts){
                    if (tabId.equals(s)){
                        previous = current;
                        current = index;
                        break;
                    }
                    index++;
                }
            }
        });
    }
    private View getView(int pos) {
        View view = View.inflate(MainActivity.this, R.layout.tabcontent, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        TextView textView = (TextView) view.findViewById(R.id.text);

        imageView.setImageResource(imageButton[pos]);
        textView.setText(texts[pos]);
        return view;
    }

    @Override
    public void postData(Fragment fragment, int[] data) {
        tabhost.setCurrentTab(data[0]);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            onBackPressed();
            return true;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }
    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - touchTime) >= waitTime) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            touchTime = currentTime;
        } else {
            MainActivity.this.finish();
        }
    }
}
