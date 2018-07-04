package com.gjg.fjdshop.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gjg.fjdshop.R;
import com.gjg.fjdshop.ui.home.bean.ResultBeanData;
import com.gjg.fjdshop.utils.Constants;

import java.util.List;


/**
 * Created by JunGuang_Gao
 * on 2017/4/12  17:56.
 * 类描述：
 */

public class SecondKillRecyclerViewAdapter extends RecyclerView.Adapter<SecondKillRecyclerViewAdapter.ViewHoldler> {

    private Context mContext;
    private List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> beanList;

    public SecondKillRecyclerViewAdapter(Context mContext, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {
        this.mContext=mContext;
        this.beanList=list;
    }


    @Override
    public ViewHoldler onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_seckill,null);
        return new ViewHoldler(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHoldler holder, int position) {
        //1.根据位置得到对应的数据
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = beanList.get(position);

        //2.绑定数据

        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+listBean.getFigure()).into(holder.iv_figure);
        holder.tv_cover_price.setText("￥"+listBean.getCover_price());
        holder.tv_origin_price.setText("￥"+listBean.getOrigin_price());
        holder.tv_origin_price.setTextSize(12);
    }



    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class ViewHoldler extends RecyclerView.ViewHolder{
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHoldler(View itemView) {
            super(itemView);
            iv_figure = (ImageView) itemView.findViewById(R.id.iv_figure);
            tv_cover_price = (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = (TextView) itemView.findViewById(R.id.tv_origin_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, "秒杀="+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    if(onSecondKillRecyclerViewListener != null){
                        onSecondKillRecyclerViewListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * 监听器
     */
    public interface OnSecondKillRecyclerViewListener{
        /**
         * 当某条被点击的时候回调
         * @param position
         */
         void onItemClick(int position);
    }

    private  OnSecondKillRecyclerViewListener onSecondKillRecyclerViewListener;

    /**
     * 设置item的监听
     * @param onSecondKillRecyclerViewListener
     */
    public void setOnSecondKillRecyclerViewListener(OnSecondKillRecyclerViewListener onSecondKillRecyclerViewListener) {
        this.onSecondKillRecyclerViewListener = onSecondKillRecyclerViewListener;
    }
}
