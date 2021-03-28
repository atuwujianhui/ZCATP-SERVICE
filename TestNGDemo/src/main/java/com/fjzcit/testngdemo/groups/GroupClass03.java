package com.fjzcit.testngdemo.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupClass03 {

    public void teacher01() {
        System.out.println("GroupClass03 -> teacher01");
    }

    public void teacher02() {
        System.out.println("GroupClass03 -> teacher02");
    }
}
