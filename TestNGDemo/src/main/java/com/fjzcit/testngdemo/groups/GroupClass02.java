package com.fjzcit.testngdemo.groups;

import org.testng.annotations.Test;

@Test(groups = "student")
public class GroupClass02 {

    public void student01() {
        System.out.println("GroupClass02 -> student01");
    }

    public void student02() {
        System.out.println("GroupClass02 -> student02");
    }
}
