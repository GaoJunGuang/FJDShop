package com.gjg.fjdshop.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gjg.fjdshop.R;
import com.gjg.fjdshop.app.GoodsInfoActivity;
import com.gjg.fjdshop.ui.home.bean.GoodsBean;
import com.gjg.fjdshop.ui.home.bean.ResultBeanData;
import com.gjg.fjdshop.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by JunGuang_Gao
 * on 2017/4/12  14:24.
 * 类描述：
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter {

    /**
     * 广告条幅类型
     */
    public static final int BANNER = 0;

    /**
     * 频道类型
     */
    public static final int CHANNEL = 1;
    /**
     * 活动类型
     */
    public static final int ACT = 2;
    /**
     * 秒杀类型
     */
    public static final int SECKILL = 3;
    /**
     * 推荐类型
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;
    private static final String GOODS_BEAN = "goodsBean";
    /**
     * 当前类型
     */
    public int currentType = BANNER;

    private final LayoutInflater mLayoutInflater;
    private Context mContext;
    private ResultBeanData.ResultBean resultBean;

    public HomeFragmentAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==BANNER){
            View itemView=mLayoutInflater.inflate(R.layout.banner_viewpager,parent,false);
            return new BannerViewHolder(itemView,mContext);
        }else if(viewType==CHANNEL){
            View itemView=mLayoutInflater.inflate(R.layout.channel_item,parent,false);
            return new ChannelViewHolder(itemView,mContext);
        }else if(viewType==ACT){
            View itemView=mLayoutInflater.inflate(R.layout.act_item,parent,false);
            return new ActivityViewHolder(itemView,mContext);
        }else if(viewType==SECKILL){
            View itemView=mLayoutInflater.inflate(R.layout.seckill_item,parent,false);
            return new SecondKillViewHolder(itemView,mContext);
        }else if(viewType==RECOMMEND){
            View itemView=mLayoutInflater.inflate(R.layout.recommend_item,parent,false);
            return new RecommendViewHolder(itemView,mContext);
        }else if(viewType==HOT){
            View itemView=mLayoutInflater.inflate(R.layout.hot_item,parent,false);
            return new HotViewHolder(itemView,mContext);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        }else if(getItemViewType(position) == CHANNEL){
            ChannelViewHolder channelViewHolder= (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        }else if(getItemViewType(position) == ACT){
            ActivityViewHolder activityViewHolder= (ActivityViewHolder) holder;
            activityViewHolder.setData(resultBean.getAct_info());
        }else if(getItemViewType(position) == SECKILL){
            SecondKillViewHolder secondKillViewHolder= (SecondKillViewHolder) holder;
            secondKillViewHolder.setData(resultBean.getSeckill_info());
        }else if(getItemViewType(position) == RECOMMEND){
            RecommendViewHolder recommendViewHolder= (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        }else if(getItemViewType(position) == HOT){
            HotViewHolder hotViewHolder= (HotViewHolder) holder;
            hotViewHolder.setData(resultBean.getHot_info());
        }
    }


    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        //开发过程中从1-->6
        return 6;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private Banner banner;

        public BannerViewHolder(View itemView,Context context) {
            super(itemView);
            this.mContext = context;
            this.banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //设置Banner的数据
            //得到图片集合地址
            List<String> imagesUrl = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }

            //设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);

            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(imagesUrl);
            //banner设置方法全部调用完毕时最后调用
            banner.start();

            //设置item的点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();

                    //startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }

    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //eg：

            //Glide 加载图片简单用法
            Glide.with(context).load(Constants.BASE_URL_IMAGE +path).into(imageView);

            /*//Picasso 加载图片简单用法
            Picasso.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);*/
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        /*@Override
        public ImageView createImageView(Context context) {
            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
            return simpleDraweeView;
        }*/
    }

    private class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gv_channel;
        private ChannelAdapter adapter;
        public ChannelViewHolder(View itemView, final Context mContext) {
            super(itemView);
            this.mContext = mContext;
            gv_channel = (GridView) itemView.findViewById(R.id.gv_channel);

            //设置item的点击事件
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            //得到数据了
            //设置GridView的适配器
            adapter = new ChannelAdapter(mContext, channel_info);
            gv_channel.setAdapter(adapter);
        }
    }

    private class ActivityViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager act_viewpager;

        public ActivityViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            act_viewpager.setPageMargin(20);
            act_viewpager.setOffscreenPageLimit(3);//>=3

            //setPageTransformer 决定动画效果
            act_viewpager.setPageTransformer(true,new ScaleInTransformer());
            //1.有数据了
            //2.设置适配器
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                /**
                 *
                 * @param view 页面
                 * @param object instantiateItem方法返回的值
                 * @return
                 */
                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                /**
                 *
                 * @param container ViewPager
                 * @param position 对应页面的位置
                 * @return
                 */
                @Override
                public Object instantiateItem(ViewGroup container, final int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE + act_info.get(position).getIcon_url()).into(imageView);
                    //添加到容器中
                    container.addView(imageView);

                    //设置点击事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                        }
                    });


                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }

    /*private MyHandler handler=new MyHandler();
    private long dt = 0;
    private static class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dt = dt - 1000;
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
            String time = formatter.format(new Date(dt));
            tv_time_seckill.setText(time);

            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0,1000);
            if(dt <=0){
                //把消息移除
                handler.removeCallbacksAndMessages(null);
            }
        }
    }*/


    private class SecondKillViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        private SecondKillRecyclerViewAdapter adapter;

        /**
         * 相差多少时间-毫秒
         */
        private long dt = 0;
        private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dt = dt - 1000;
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
                String time = formatter.format(new Date(dt));
                tv_time_seckill.setText(time);

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);
                if(dt <=0){
                    //把消息移除
                    handler.removeCallbacksAndMessages(null);
                }

            }
        };

        public SecondKillViewHolder(View itemView,Context mContext) {
            super(itemView);
            tv_time_seckill = (TextView) itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = (TextView) itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
            this.mContext = mContext;
        }

        public void setData(final ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
            //1.得到数据了
            //2.设置数据：文本和RecyclerView的数据
            adapter = new SecondKillRecyclerViewAdapter(mContext,seckill_info.getList());
            rv_seckill.setAdapter(adapter);

            //设置布局管理器
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            //设置item的点击事件
            adapter.setOnSecondKillRecyclerViewListener(new SecondKillRecyclerViewAdapter.OnSecondKillRecyclerViewListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext, "秒杀"+position, Toast.LENGTH_SHORT).show();

                    ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = seckill_info.getList().get(position);


                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(listBean.getCover_price());
                    goodsBean.setFigure(listBean.getFigure());
                    goodsBean.setName(listBean.getName());
                    goodsBean.setProduct_id(listBean.getProduct_id());

                    startGoodsInfoActivity(goodsBean);
                }
            });


            //秒杀倒计时 -毫秒
            dt =   Integer.valueOf(seckill_info.getEnd_time()) - Integer.valueOf(seckill_info.getStart_time());


            handler.sendEmptyMessageDelayed(0,1000);
        }

    }

    /**
     * 启动商品信息列表页面
     * @param goodsBean
     */
    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goodsBean);
        mContext.startActivity(intent);
    }

    private class RecommendViewHolder extends RecyclerView.ViewHolder{
        private final Context mContext;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private RecommendGridViewAdapter adapter;

        public RecommendViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            tv_more_recommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);

        }

        public void setData(final List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            //1.有数据了
            //2.设置适配器
            adapter = new RecommendGridViewAdapter(mContext,recommend_info);
            gv_recommend.setAdapter(adapter);

            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                    ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(position);

                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setFigure(recommendInfoBean.getFigure());
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());


                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }

    private class HotViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView tv_more_hot;
        private GridView gv_hot;
        public HotViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext=mContext;
            tv_more_hot= (TextView) itemView.findViewById(R.id.tv_more_hot);
            gv_hot= (GridView) itemView.findViewById(R.id.gv_hot);
        }

        public void setData(final List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {

            //1.有数据
            //2.设置GridView的适配器
            HotGridViewAdapter  adapter = new HotGridViewAdapter(mContext,hot_info);
            gv_hot.setAdapter(adapter);

            Log.d("hot_info",hot_info+"");

            //设置item的监听
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                    //热卖商品信息类
                    ResultBeanData.ResultBean.HotInfoBean hotInfoBean =  hot_info.get(position);
                    //商品信息类
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(hotInfoBean.getCover_price());
                    goodsBean.setFigure(hotInfoBean.getFigure());
                    goodsBean.setName(hotInfoBean.getName());
                    goodsBean.setProduct_id(hotInfoBean.getProduct_id());

                    startGoodsInfoActivity(goodsBean);
                }
            });

        }
    }
}
