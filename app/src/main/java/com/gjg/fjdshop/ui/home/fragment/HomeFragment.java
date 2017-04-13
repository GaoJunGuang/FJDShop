package com.gjg.fjdshop.ui.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.gjg.fjdshop.R;
import com.gjg.fjdshop.base.BaseFragment;
import com.gjg.fjdshop.ui.home.adapter.HomeFragmentAdapter;
import com.gjg.fjdshop.ui.home.bean.ResultBeanData;
import com.gjg.fjdshop.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.gjg.fjdshop.R.id.ib_top;

/**
 * Created by JunGuang_Gao
 * on 2017/4/12  09:56.
 * 类描述：
 */

public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.tv_search_home)
    TextView tvSearchHome;
    @BindView(R.id.tv_message_home)
    TextView tvMessageHome;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;

    @BindView(ib_top)
    ImageButton ibTop;

    Unbinder unbinder;

    /**
     * 返回的数据
     */
    private ResultBeanData.ResultBean resultBean;
    private HomeFragmentAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        unbinder =ButterKnife.bind(this, view);
        //设置点击事件
        initListener();
        return view;
    }

    private void initListener() {
        //置顶的监听
        ibTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                rvHome.scrollToPosition(0);
            }
        });

        //搜素的监听
        tvSearchHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });

        //消息的监听
        tvMessageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        //联网请求主页的数据
        getDataFromNet();

    }

    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /**
                     * 当请求失败的时候回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        Log.e(TAG,"首页请求失败=="+e.getMessage());
                    }

                    /**
                     * 当联网成功的时候回调
                     * @param response 请求成功的数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"首页请求成功=="+response);
                        //解析数据
                        processData(response);
                    }


                });
    }

    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json,ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        if(resultBean != null){
            //有数据
            //设置适配器
            adapter = new HomeFragmentAdapter(mContext,resultBean);
            rvHome.setAdapter(adapter);
            GridLayoutManager manager =  new GridLayoutManager(mContext,1);
            //设置跨度大小监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position <= 3){
                        //隐藏
                        ibTop.setVisibility(View.GONE);
                    }else{
                        //显示
                        ibTop.setVisibility(View.VISIBLE);
                    }
                    //只能返回1
                    return 1;
                }
            });
            //设置布局管理者
            rvHome.setLayoutManager(manager);

        }else{
            //没有数据
        }
        Log.e(TAG,"解析成功=="+resultBean.getHot_info().get(0).getName());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
