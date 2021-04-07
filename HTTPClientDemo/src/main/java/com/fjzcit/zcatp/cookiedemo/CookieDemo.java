package com.fjzcit.zcatp.cookiedemo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultHttpRequestFactory;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CookieDemo {

    private String url;
    private ResourceBundle bundle;
    private CookieStore cookieStore = new BasicCookieStore();

    @BeforeTest
    public void beforeTest() {
        // 获取“resources”目录下的“application”配置文件，可以省略“.properties”
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void test_02_GetCookies() throws IOException {
        // 获取URL
        String uri = this.bundle.getString("test.uri.getCookies");
        String testUrl = this.url + uri;
        // 发起GET请求获取相应数据，包括cookies
        HttpGet get = new HttpGet(testUrl);
        CloseableHttpClient client = HttpClients.custom().
                setDefaultCookieStore(this.cookieStore).build();
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

        // 获取Cookies
        List<Cookie> cookies = this.cookieStore.getCookies();
        for (Cookie cookie: cookies) {
            System.out.println(cookie);
        }
    }

    @Test(dependsOnMethods = {"test_02_GetCookies"})
    public void test_01_GetWithCookies() throws IOException {
        // 获取URL
        String uri = this.bundle.getString("test.uri.getWithCookies");
        String testUrl = this.url + uri;
        // Get请求带Cookies
        HttpGet get = new HttpGet(testUrl);
        CloseableHttpClient client = HttpClients.custom().
                setDefaultCookieStore(this.cookieStore).build();
        // 返回Get请求结果
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity());
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode + "："  + result);
    }

    @Test(dependsOnMethods = {"test_02_GetCookies"})
    public void test_03_PostWithCookies() throws IOException {
        // 获取URL
        String uri = this.bundle.getString("test.uri.postWithCookies");
        String testUrl = this.url + uri;
        System.out.println("POST请求：" + testUrl);

        // 声明Http方法，创建HttpPost对象
        HttpPost post = new HttpPost(testUrl);

        // 创建CloseableHttpClient对象，并设置Cookie信息
        CloseableHttpClient client = HttpClients.custom().
                setDefaultCookieStore(this.cookieStore).build();

        // 添加参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Atu");
        jsonObject.put("age", "24");
        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
        post.setEntity(entity);

        // 设置请求头
        post.setHeader("content-type", "application/json");

        // Post请求
        HttpResponse response = client.execute(post);

        // 获取返回结果（JSON）格式
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("状态码：" + statusCode);
        if (statusCode == 200) {
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
            JSONObject resultJson = new JSONObject(result);
            System.out.println(resultJson.get("code") + "：" + resultJson.get("msg"));
        }
    }
}
