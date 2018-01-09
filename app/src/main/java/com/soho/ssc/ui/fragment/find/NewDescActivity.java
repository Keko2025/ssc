package com.soho.ssc.ui.fragment.find;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.facebook.drawee.view.SimpleDraweeView;
import com.soho.ssc.MyApplication;
import com.soho.ssc.R;
import com.soho.ssc.utils.DisplayUtil;
import com.soho.ssc.utils.HttpUtil;
import com.soho.ssc.utils.ImageUtil;
import com.soho.ssc.utils.L;
import com.soho.ssc.utils.ScienceContentParser;
import com.soho.ssc.utils.Utils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author dell
 * @data 2018/1/4.
 */
public class NewDescActivity extends AppCompatActivity {
    @BindView(R.id.progressBarTopPic)
    ProgressBar progressBarTopPic;
    @BindView(R.id.topImage)
    SimpleDraweeView topImage;
    @BindView(R.id.content_view)
    WebView contentView;
    @BindView(R.id.content_card)
    CardView contentCard;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.networkBtn)
    ImageButton networkBtn;
    @BindView(R.id.stbar)
    LinearLayout stbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarcontent)
    FrameLayout toolbarcontent;
    @BindView(R.id.main_content)
    FrameLayout mainContent;
    private Intent intent;
    private String newsUrl;
    private String imgUrl;
    private boolean isCollected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdesc);
        ButterKnife.bind(this);

        intent = getIntent();
        if(intent != null){
            newsUrl = intent.getStringExtra("url");
            imgUrl = intent.getStringExtra("img_url");
            setMainContentBg(imgUrl);
        }
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        //对toolbar进行下移
        int height = DisplayUtil.getScreenHeight(MyApplication.getContext());
        LinearLayout ll = (LinearLayout) findViewById(R.id.stbar);
        LinearLayout.LayoutParams llp = (LinearLayout.LayoutParams) ll.getLayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            llp.height = (int) (height * 0.03);
            ll.setLayoutParams(llp);
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.top_gradient));

        contentView.getSettings().setJavaScriptEnabled(true);

        // 开启缓存
        contentView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        contentView.getSettings().setDomStorageEnabled(true);
        contentView.getSettings().setDatabaseEnabled(true);

        contentView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideLoading();
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                displayNetworkError();
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                contentView.loadUrl(url);
                return false;
            }
        });
        networkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkBtn.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                onDataRefresh();
            }
        });
        onDataRefresh();
    }
    protected void setMainContentBg(final String url) {
        if (Utils.hasString(url) == false) {
            setDefaultColor();
            return;
        }

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request = builder.build();

        HttpUtil.enqueue(request, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        setDefaultColor();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if(bitmap == null){
                            setDefaultColor();
                            L.e("onResponse bitmap null: " + url);
                            return;
                        }
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                            topImage.setBackground(new BitmapDrawable(getResources(), bitmap));
                        } else{
                            topImage.setImageURI(Uri.parse(url));
                        }
                        L.e("onResponse: " + url);
                        mainContent.setBackgroundColor(ImageUtil.getImageColor(bitmap));
                        progressBarTopPic.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
    private void onDataRefresh() {
        Utils.getRawHtmlFromUrl(newsUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.e("onFailure：" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String rawData = response.body().string();
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ScienceContentParser myParse = new ScienceContentParser(rawData);
                        String data = myParse.getEndStr();
                        scrollView.setVisibility(View.VISIBLE);
                        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                            @Override
                            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                                topImage.setTranslationY(Math.max(-scrollY / 2, -DisplayUtil.dp2px(getBaseContext(), 170)));
                            }
                        });
                        contentView.loadDataWithBaseURL("file:///android_asset/", "<link rel=\"stylesheet\" type=\"text/css\" href=\"guokr.css\" />" + data, "text/html", "utf-8", null);
                    }
                });
            }
        });
        hideLoading();
    }

    public void hideLoading() {
        if(progressBar != null){
            progressBar.setVisibility(View.GONE);
            progressBarTopPic.setVisibility(View.VISIBLE);
        }
    }

    public void displayNetworkError() {
        if(networkBtn != null){
            networkBtn.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置布局背景，其实就是边缘空隙的颜色，颜色取自顶部图片的主色调
     *
     * @param
     */

    protected void setDefaultColor(){
        mainContent.setBackgroundColor(Color.rgb(67,76,66));
        progressBarTopPic.setVisibility(View.GONE);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_share,menu);
//        updateCollectionMenu(menu.findItem(R.id.menu_collect));
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId() == R.id.menu_collect){
//            if(isCollected){
//                removeFromCollection();
//                isCollected = false;
//                updateCollectionMenu(item);
//                Snackbar.make(mainContent, R.string.notify_remove_from_collection,Snackbar.LENGTH_SHORT).show();
//            }else {
//                addToCollection();
//                isCollected = true;
//                updateCollectionMenu(item);
//                Snackbar.make(mainContent, R.string.notify_add_to_collection,Snackbar.LENGTH_SHORT).show();
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void addToCollection() {
//    }
//
//    private void removeFromCollection() {
//    }
//
//    protected void updateCollectionMenu(MenuItem item){
//        if(isCollected){
//            item.setIcon(R.drawable.ic_star_black);
//        }else {
//            item.setIcon(R.drawable.ic_star_white);
//        }
//    }


}
