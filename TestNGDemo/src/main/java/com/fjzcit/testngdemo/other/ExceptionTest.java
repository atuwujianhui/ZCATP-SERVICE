package com.fjzcit.testngdemo.other;

import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;

public class ExceptionTest {

    @Test(expectedExceptions = RuntimeException.class)
    public void test01() {
        int i = 0 / 1;
//        int i = 1 / 0;
    }
}
