package com.fjzcit.testngdemo.suite;

import org.testng.annotations.*;

public class Login {

    @BeforeGroups("login")
    public void beforeLoginGroups() {
        System.out.println("------# 登录分组执行前前操作");
    }

    @AfterGroups("login")
    public void afterLoginGroups() {
        System.out.println("------# 登录分组执行后运行！");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("--------# Login单个用例（方法）执行前运行！");
    }

    @Test(groups = "login")
    public void inputUserName() {
        System.out.println("--------# 输入用户名！");
    }

    @Test(groups = "login")
    public void inputPassword() {
        System.out.println("--------# 输入密码！");
    }

    @Test(groups = "login")
    public void login() {
        System.out.println("--------# 点击“登录”按钮，登录淘宝！");
    }

    @Test
    public void logout() {
        System.out.println("--------# 退出淘宝！");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("--------# Login单个用例（方法）执行后运行！");
    }
}
