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
 * on 2017/4/12  21:13.
 * 类描述：
 */

public class HotGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<ResultBeanData.ResultBean.HotInfoBean> hots_info;


    public HotGridViewAdapter(Context mContext, List<ResultBeanData.ResultBean.HotInfoBean> hots_info) {
        this.mContext=mContext;
        this.hots_info=hots_info;
    }

    @Override
    public int getCount() {
        return hots_info==null?0:hots_info.size();
    }

    @Override
    public Object getItem(int position) {
        return hots_info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=View.inflate(mContext, R.layout.item_hot_grid_view,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_hot= (ImageView) convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price= (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        ResultBeanData.ResultBean.HotInfoBean hotInfoBean = hots_info.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+hotInfoBean.getFigure()).into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(hotInfoBean.getName());
        viewHolder.tv_price.setText("￥"+hotInfoBean.getCover_price());
        return convertView;
    }

    static class ViewHolder{
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
