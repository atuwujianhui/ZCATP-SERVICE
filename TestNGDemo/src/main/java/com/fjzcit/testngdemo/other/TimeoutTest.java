package com.fjzcit.testngdemo.other;

import org.testng.annotations.Test;

public class TimeoutTest {

    @Test(timeOut = 2000)
    public void testSucceed() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("超时测试成功，超时了！");
    }

    @Test(timeOut = 3000)
    public void testFailure() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("超时测试失败，没有超时！");
    }
}
