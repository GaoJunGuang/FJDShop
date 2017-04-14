package com.gjg.fjdshop.ui.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gjg.fjdshop.R;
import com.gjg.fjdshop.ui.home.bean.ResultBeanData;
import com.gjg.fjdshop.utils.Constants;

import java.util.List;

/**
 * Created by JunGuang_Gao
 * on 2017/4/12  20:39.
 * 类描述：
 */

public class RecommendGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.RecommendInfoBean> list;
    public RecommendGridViewAdapter(Context context,List<ResultBeanData.ResultBean.RecommendInfoBean> list){
        this.mContext=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=View.inflate(mContext,R.layout.item_recommend_grid_view,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_recommend= (ImageView) convertView.findViewById(R.id.iv_recommend);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price= (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);


        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        //根据位置得到对应的数据
        ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = list.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+recommendInfoBean.getFigure()).into(viewHolder.iv_recommend);
        viewHolder.tv_name.setText(recommendInfoBean.getName());
        viewHolder.tv_price.setText("￥"+recommendInfoBean.getCover_price());
        return convertView;
    }

    static class ViewHolder{
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }
}
