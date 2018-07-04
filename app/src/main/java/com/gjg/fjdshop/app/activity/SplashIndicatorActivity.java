package com.gjg.fjdshop.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gjg.fjdshop.R;
import com.gjg.fjdshop.app.adapter.ViewPagerAdapter;
import com.gjg.fjdshop.ui.user.activity.LoginActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashIndicatorActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.goComing)
    TextView goComing;
    @BindView(R.id.goLogin)
    TextView goLogin;
    @BindView(R.id.goLayout)
    RelativeLayout goLayout;
    @BindView(R.id.point_layout)
    LinearLayout pointLayout;

    private ViewPagerAdapter adapter;

    private ArrayList<SimpleDraweeView> views = new ArrayList<SimpleDraweeView>();

    private int length;


    /**
     * 底部指示器(小圆点)的图片
     */
    private ImageView[] points;

    /**
     * 当前选中的指针
     */
    private int currentIndex;

    /**
     * 引导界面本地图片
     */
    public int[] pics = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3, R.drawable.guide4};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashindicator);
        ButterKnife.bind(this);
        initData();

    }


    private void initData() {
        adapter = new ViewPagerAdapter(views);
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        for (int i = 0; i < pics.length; i++) {
            length = pics.length;
            SimpleDraweeView iv = new SimpleDraweeView(this);
            iv.setLayoutParams(mParams);
            //防止图片不能填满屏幕
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            //加载图片资源
            iv.setImageResource(pics[i]);
            views.add(iv);
        }


        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new OnPageChangeListener());
        initPoint();
    }

    /**
     * 初始化小圆点
     */
    private void initPoint() {
        //设定一个布局的参数
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.point_layout);
        //生成指示器个数
        points = new ImageView[length];

        for (int i = 0; i < length; i++) {
            points[i] = (ImageView) linearLayout.getChildAt(i);
            // 默认都设为灰色
            points[i].setEnabled(true);
            // 给每个小点设置监听
            points[i].setOnClickListener(this);
            // 设置位置tag，方便取出与当前位置对应
            points[i].setTag(i);
        }

        //设置当前默认的位置
        currentIndex = 0;
        //设置为白色即选中的状态
        points[currentIndex].setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        setCurrentView(position);
        setCurrentDot(position);
    }

    private final class OnPageChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            setCurrentDot(position);
            if (position == length - 1) {
                goLayout.setVisibility(View.VISIBLE);
                goComing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(SplashIndicatorActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                goLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("startUp", "welcome");
                        intent.setClass(SplashIndicatorActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            } else {
                goLayout.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }


    /**
     * 设置当前页面视图的状态
     *
     * @param position
     */
    private void setCurrentView(int position) {
        if (position < 0 || position >= length) {
            return;
        }
        viewPager.setCurrentItem(position);
    }


    private void setCurrentDot(int position) {
        if (position < 0 || position >= length) {
            return;
        }
        points[position].setEnabled(false);
        points[currentIndex].setEnabled(true);
        currentIndex = position;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
