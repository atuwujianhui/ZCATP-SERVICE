package com.fjzcit.testngdemo.other;

import org.testng.annotations.Test;

public class DependencyTest {

    public int count;

    @Test(dependsOnMethods = {"test02"})
    public void test01() {
        System.out.println(count ++);
        System.out.println("Test01!");
    }

    @Test
    public void test02() {
        System.out.println(count ++);
        System.out.println("Test02!");
//        throw new RuntimeException();
    }
}
