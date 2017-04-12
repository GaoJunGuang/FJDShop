package com.gjg.fjdshop.ui.user.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gjg.fjdshop.base.BaseFragment;

/**
 * Created by JunGuang_Gao
 * on 2017/4/12  11:21.
 * 类描述：
 */

public class UserFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView textView=new TextView(mContext);
        textView.setText("UserFragment");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }
}
