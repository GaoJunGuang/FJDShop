package com.gjg.fjdshop.ui.home.bean;

import java.io.Serializable;

/**
 * Created by JunGuang_Gao
 * on 2017/4/12  18:40.
 * 类描述：
 */

public class GoodsBean implements Serializable {
    //价格
    private String cover_price;
    //图片
    private String figure;
    //名称
    private String name;
    //产品id
    private String product_id;
    //购物车中商品的数量 默认为1
    private int number = 1;

    /**
     * 是否被选中
     */
    private boolean isSelected = true;

    public GoodsBean() {
    }

    public GoodsBean(String name, String cover_price, String figure, String product_id) {
        this.name=name;
        this.cover_price=cover_price;
        this.figure=figure;
        this.product_id=product_id;

    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                ", number=" + number +
                ", isSelected=" + isSelected +
                '}';
    }
}
