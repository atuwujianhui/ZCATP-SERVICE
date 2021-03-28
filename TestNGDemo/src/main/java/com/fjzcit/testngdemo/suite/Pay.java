package com.fjzcit.testngdemo.suite;

import org.testng.annotations.Test;

public class Pay {


    @Test(enabled = false)
    public void search() {
        System.out.println("------# 搜索商品！");
    }

    @Test
    public void addToShoppingCart() {
        System.out.println("------# 加入购物车！");
    }

    @Test
    public void pay() {
        System.out.println("------# 支付！");
    }
}
