package com.fjzcit.testngdemo;

import org.testng.annotations.*;

public class BasicAnnotation {

    /**
     * 所有用例执行前
     */
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("“beforeSuite”：所有用例执行前运行！");
    }

    /**
     * 每个用例执行前运行！
     */
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("“beforeMethod”：每个测试用例执行前运行！");
    }

    /**
     * 执行测试用例01！
     */
    @Test
    public void testCase01() {
        System.out.println("“testCase01”：测试用例01！");
    }

    /**
     * 执行测试用例02！
     */
    @Test
    public void testCase02() {
        System.out.println("“testCase01”：测试用例02！");
    }

    /**
     * 每个用例执行后运行！
     */
    @AfterMethod
    public void afterMethod() {
        System.out.println("“afterMethod”：每个测试用例执行后运行！");
    }

    /**
     * 所有用例执行后运行
     */
    @AfterSuite
    public void afterSuite() {
        System.out.println("“afterSuite”：所有用例执行后运行！");
    }
}
