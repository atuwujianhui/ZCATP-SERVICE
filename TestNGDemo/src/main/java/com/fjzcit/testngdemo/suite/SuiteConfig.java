package com.fjzcit.testngdemo.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConfig {

    /**
     * 整个用例套件（所有用例集）执行前运行！
     */
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("==》整个用例套件（所有用例集）执行前运行！");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("----> 单个用例集（类）执行前运行！");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("----> 单个用例集（类）执行后运行！");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("==》整个用例套件（所有用例集）执行后运行！");
    }
}
