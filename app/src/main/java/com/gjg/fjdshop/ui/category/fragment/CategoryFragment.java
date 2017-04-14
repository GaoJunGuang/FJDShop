package com.gjg.fjdshop.ui.category.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;
import com.gjg.fjdshop.R;
import com.gjg.fjdshop.base.BaseFragment;

import java.util.List;

/**
 * Created by JunGuang_Gao
 * on 2017/4/12  11:17.
 * 类描述：
 */

public class CategoryFragment extends BaseFragment {
    private SegmentTabLayout segmentTabLayout;
    private ImageView iv_type_search;
    private FrameLayout fl_type;
    private List<BaseFragment> fragmentList;
    private Fragment tempFragment;
    public ListFragment listFragment;
    public TagFragment tagFragment;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        segmentTabLayout = (SegmentTabLayout) view.findViewById(R.id.tl_1);
        iv_type_search = (ImageView) view.findViewById(R.id.iv_type_search);
        fl_type = (FrameLayout) view.findViewById(R.id.fl_type);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

    }
}
