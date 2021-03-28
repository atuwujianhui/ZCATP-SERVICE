package com.fjzcit.extentreportdemo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ExtentReportDemo {

    @Test
    public void test01() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void test02() {
        Assert.assertEquals(1, 2);
    }

    @Test
    public void test03() {
        Assert.assertEquals("Hello", "Hello");
    }

    @Test
    public void logDemo() {
        Reporter.log("# Hello, my log!");
        throw new RuntimeException("# Hello, my runtimeException!");
    }
}
