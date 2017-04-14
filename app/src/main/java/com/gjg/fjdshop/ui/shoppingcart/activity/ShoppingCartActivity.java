package com.gjg.fjdshop.ui.shoppingcart.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gjg.fjdshop.R;
import com.gjg.fjdshop.ui.activity.MainActivity;
import com.gjg.fjdshop.ui.home.bean.GoodsBean;
import com.gjg.fjdshop.ui.shoppingcart.adapter.ShoppingCartAdapter;
import com.gjg.fjdshop.ui.shoppingcart.utils.CartStorage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.gjg.fjdshop.R.id.cb_all;
import static com.gjg.fjdshop.R.id.ll_check_all;
import static com.gjg.fjdshop.R.id.ll_delete;
import static com.gjg.fjdshop.R.id.ll_empty_shopcart;

public class ShoppingCartActivity extends Activity {
    /**
     * 编辑状态
     */
    private static final int ACTION_EDIT = 0;
    /**
     * 完成状态
     */
    private static final int ACTION_COMPLETE = 1;

    @BindView(R.id.ib_shopcart_back)
    ImageButton ibShopcartBack;
    @BindView(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @BindView(ll_empty_shopcart)
    LinearLayout llEmptyShopcart;
    @BindView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @BindView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @BindView(R.id.btn_check_out)
    Button btnCheckOut;
    @BindView(ll_check_all)
    LinearLayout llCheckAll;
    @BindView(cb_all)
    CheckBox cbAll;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_collection)
    Button btnCollection;
    @BindView(ll_delete)
    LinearLayout llDelete;

    private ShoppingCartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ButterKnife.bind(this);

        showData();
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");
    }

    @OnClick(R.id.ib_shopcart_back)
    public void finishActivity(View view){
        finish();
    }

    @OnClick(R.id.tv_shopcart_edit)
    public void handleEditButton(View view){
        //设置编辑的点击事件
        int tag = (int) tvShopcartEdit.getTag();
        if (tag == ACTION_EDIT) {
            //变成完成状态
            showDelete();
        } else {
            //变成编辑状态
            hideDelete();
        }
    }

    @OnClick(R.id.btn_delete)
    public void handleDeleteButton(View view){
        adapter.deleteData();
        adapter.showTotalPrice();
        //显示空空如也
        checkData();
        adapter.checkAll();
    }


    @OnClick(R.id.tv_empty_cart_tobuy)
    public void handleEmptyCart(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void hideDelete() {
        tvShopcartEdit.setText("编辑");
        tvShopcartEdit.setTag(ACTION_EDIT);

        adapter.checkAll_none(true);
        llDelete.setVisibility(View.GONE);
        llCheckAll.setVisibility(View.VISIBLE);

        adapter.showTotalPrice();
    }

    private void showDelete() {
        tvShopcartEdit.setText("完成");
        tvShopcartEdit.setTag(ACTION_COMPLETE);

        adapter.checkAll_none(false);
        cbAll.setChecked(false);
        checkboxAll.setChecked(false);

        llDelete.setVisibility(View.VISIBLE);
        llCheckAll.setVisibility(View.GONE);

        adapter.showTotalPrice();
    }



    //-----------------------------------------
    private void checkData() {
        if (adapter != null && adapter.getItemCount() > 0) {
            tvShopcartEdit.setVisibility(View.VISIBLE);
            llEmptyShopcart.setVisibility(View.GONE);
            llCheckAll.setVisibility(View.GONE);
        } else {
            llEmptyShopcart.setVisibility(View.VISIBLE);
            tvShopcartEdit.setVisibility(View.GONE);
            llCheckAll.setVisibility(View.GONE);
            llDelete.setVisibility(View.GONE);
        }
    }

    private void showData() {

        List<GoodsBean> goodsBeanList = CartStorage.getInstance().getAllData();

        if (goodsBeanList != null && goodsBeanList.size() > 0) {
            tvShopcartEdit.setVisibility(View.VISIBLE);
            llCheckAll.setVisibility(View.VISIBLE);
            //有数据
            //把当没有数据显示的布局-隐藏
            llEmptyShopcart.setVisibility(View.GONE);

            //设置适配器
            adapter = new ShoppingCartAdapter(ShoppingCartActivity.this, goodsBeanList, tvShopcartTotal, checkboxAll, cbAll);
            recyclerview.setAdapter(adapter);

            //设置布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(ShoppingCartActivity.this, LinearLayoutManager.VERTICAL, false));


        } else {
            //没有数据
            //显示数据为空的布局
            emptyShoppingCart();
        }

    }


    private void emptyShoppingCart() {
        llEmptyShopcart.setVisibility(View.VISIBLE);
        tvShopcartEdit.setVisibility(View.GONE);
        llDelete.setVisibility(View.GONE);
    }
}
