package com.gjg.fjdshop.ui.category.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gjg.fjdshop.base.BaseFragment;

/**
 * Created by JunGuang_Gao
 * on 2017/4/12  11:17.
 * 类描述：
 */

public class CategoryFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView textView=new TextView(mContext);
        textView.setText("CategoryFragment");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }
}
