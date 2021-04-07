package com.fjzcit.zcatp.httpclientdemo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class HttpClientDemo {

    @Test
    public void test01() throws IOException {
        String result = null;
        HttpGet get = new HttpGet("http://www.baidu.com");
        HttpClient client = HttpClientBuilder.create().build();

        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }
}
