package com.fjzcit.testngdemo.groups;

import org.testng.annotations.Test;

@Test(groups = "student")
public class GroupClass01 {

    public void student01() {
        System.out.println("GroupClass01 -> student01");
    }

    public void student02() {
        System.out.println("GroupClass01 -> student02");
    }
}
