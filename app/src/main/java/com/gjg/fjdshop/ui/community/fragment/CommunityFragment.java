package com.gjg.fjdshop.ui.community.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gjg.fjdshop.base.BaseFragment;

/**
 * Created by JunGuang_Gao
 * on 2017/4/12  11:19.
 * 类描述：
 */

public class CommunityFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView textView=new TextView(mContext);
        textView.setText("CommunityFragment");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }
}
