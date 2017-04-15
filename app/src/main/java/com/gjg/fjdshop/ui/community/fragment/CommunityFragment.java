package com.gjg.fjdshop.ui.community.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gjg.fjdshop.R;
import com.gjg.fjdshop.base.BaseFragment;
import com.gjg.fjdshop.ui.community.adapter.CommunityViewPagerAdapter;

/**
 * Created by JunGuang_Gao
 * on 2017/4/12  11:19.
 * 类描述：
 */

public class CommunityFragment extends BaseFragment {
    private SimpleDraweeView ibCommunityIcon;
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ImageButton ibCommunityMessage;
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_community, null);
        ibCommunityIcon = (SimpleDraweeView) view.findViewById(R.id.ib_community_icon);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        ibCommunityMessage = (ImageButton) view.findViewById(R.id.ib_community_message);

        CommunityViewPagerAdapter adapter = new CommunityViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        tablayout.setVisibility(View.VISIBLE);
        tablayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
