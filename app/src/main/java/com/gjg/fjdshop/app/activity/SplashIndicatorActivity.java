package com.gjg.fjdshop.app.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gjg.fjdshop.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashIndicatorActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashindicator);
        ButterKnife.bind(this);

    }
}
