package com.gjg.fjdshop.ui.category.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gjg.fjdshop.R;
import com.gjg.fjdshop.ui.category.bean.TagBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JunGuang_Gao
 * on 2017/4/14  09:33.
 * 类描述：
 */

public class TagGridViewAdapter extends BaseAdapter{
    private Context mContext;
    private List<TagBean.ResultBean> result;
    private int[] colors = {Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"),
            Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"), Color.parseColor("#f0a420"),
            Color.parseColor("#f0839a"), Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2")
    };

    public TagGridViewAdapter(Context context, List<TagBean.ResultBean> resultBeen) {
        this.mContext=context;
        this.result=resultBeen;
    }

    @Override
    public int getCount() {
        return result==null?0:result.size();
    }

    @Override
    public Object getItem(int position) {
        return result.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=View.inflate(mContext, R.layout.item_tab_gridview,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        TagBean.ResultBean resultBean = result.get(position);
        viewHolder.tv_tag.setText(resultBean.getName());
        viewHolder.tv_tag.setTextColor(colors[position % colors.length]);
        return convertView;
    }

    static class ViewHolder{
        @BindView(R.id.tv_tag)
        TextView tv_tag;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }

    }
}
