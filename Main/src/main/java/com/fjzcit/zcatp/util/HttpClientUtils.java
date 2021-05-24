package com.fjzcit.zcatp.util;

import com.alibaba.fastjson.JSONObject;
import com.fjzcit.zcatp.common.constant.http.ContentTypeEnum;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

/**
 * HttpClient工具类
 * GET和POST均返回JSONObject
 * 根据实际情况进行扩展
 *
 * @author Atu
 */
public class HttpClientUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class); // 日志记录

    private static RequestConfig requestConfig = null;

    static {
        // 设置请求和传输超时时间
        requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
    }

    /**
     * post请求传输json参数
     *
     * @param url  url地址
     * @param jsonParam 参数
     * @return
     */
//    public static JSONObject httpPost(String url, JSONObject jsonParam) {
//        // post请求返回结果
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        JSONObject jsonResult = null;
//        HttpPost httpPost = new HttpPost(url);
//        // 设置请求和传输超时时间
//        httpPost.setConfig(requestConfig);
//        try {
//            if (null != jsonParam) {
//                // 解决中文乱码问题
//                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
//                entity.setContentEncoding("UTF-8");
//                entity.setContentType("application/json");
//                httpPost.setEntity(entity);
//            }
//            CloseableHttpResponse result = httpClient.execute(httpPost);
//            // 请求发送成功，并得到响应
//            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                String str = "";
//                try {
//                    // 读取服务器返回过来的json字符串数据
//                    str = EntityUtils.toString(result.getEntity(), "utf-8");
//                    // 把json字符串转换成json对象
//                    jsonResult = JSONObject.parseObject(str);
//                } catch (Exception e) {
//                    logger.error("post请求提交失败:" + url, e);
//                }
//            }
//        } catch (IOException e) {
//            logger.error("post请求提交失败:" + url, e);
//        } finally {
//            httpPost.releaseConnection();
//        }
//        return jsonResult;
//    }

    /**
     * post请求传输String参数 例如：name=Jack&sex=1&type=2
     * Content-type:application/x-www-form-urlencoded
     *
     * @param url      url地址
     * @param jsonParam 参数
     * @param contentType 参数
     * @return
     */
    public static String httpPost(String url, JSONObject jsonParam, ContentTypeEnum contentType) {
        // 定义代理，用于Fiddler抓包调试
        HttpHost proxy = new HttpHost("127.0.0.1",8888);
        requestConfig = RequestConfig.custom().setProxy(proxy).build();  //timeout之类配置省略

        // post请求返回结果
        String result = null;

        // CloseableHttpClient httpClient = HttpClients.createDefault();
        // CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        CloseableHttpClient httpClient = HttpClients.custom().build();
        // 定义Post请求对象
        HttpPost httpPost = new HttpPost(url);
        // 设置代理
        httpPost.setConfig(requestConfig);
        try {
            if (null != jsonParam) {
                switch (contentType) {
                    // 默认情况下，“Content-Type”为“application/x-www-form-urlencoded”
                    case FORM:
                        List<BasicNameValuePair> list = new ArrayList<>();
                        jsonParam.getInnerMap().forEach((key, value) -> list.add(new BasicNameValuePair(key, (String) value)));
                        httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
                        break;
                    case JSON:
                        // 解决中文乱码问题
                        StringEntity entity = new StringEntity(jsonParam.toString(), "UTF-8");
                        entity.setContentEncoding("UTF-8");
                        entity.setContentType(contentType.getName());
                        httpPost.setEntity(entity);
                        break;
                    default:
                        break;
                }
                // 设置请求头
                // httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                // httpPost.setHeader("Content-Type", "application/json");

            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                try {
                    // 读取服务器返回过来的json字符串数据
                    result = EntityUtils.toString(response.getEntity(), "utf-8");
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            httpPost.releaseConnection();
        }
        return result;
    }

    /**
     * 测试方法
     * @throws IOException
     */
    @Test
    public void postTest() throws IOException {
        // 获取URL
//        String url = "http://localhost:6666/postDemo";
        String url = "http://localhost:6666/postWithParameter";
//        String url = "http://localhost:6666/postWithJSONParameter";

        // 设置代理，用于Fiddler抓包调试
        HttpHost proxy = new HttpHost("127.0.0.1",8888);
        requestConfig = RequestConfig.custom().setProxy(proxy).build();  //timeout之类配置省略

        // 声明Http方法，创建HttpPost对象
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);


        // 创建CloseableHttpClient对象，并设置Cookie信息
        // CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();
        CloseableHttpClient client = HttpClients.custom().build();

//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

        // 添加参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Atu");
        jsonObject.put("age", "24");
//        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
//        httpPost.setEntity(entity);


        List<BasicNameValuePair> list = new ArrayList<>();
        jsonObject.getInnerMap().forEach((key, value) -> list.add(new BasicNameValuePair(key, (String) value)));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
        // 设置请求头
//        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("content-type", "application/x-www-form-urlencoded");

        // Post请求
        HttpResponse response = client.execute(httpPost);

        // 获取返回结果（JSON）格式
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("状态码：" + statusCode);
        if (statusCode == 200) {
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
            org.json.JSONObject resultJson = new org.json.JSONObject(result);
            System.out.println(resultJson.get("code") + "：" + resultJson.get("msg"));
        }
    }

    public static void main(String[] args) {
        String url = "http://localhost:6666/postWithJSONParameter";
        JSONObject params = JSONObject.parseObject("{'name': 'Atu', 'age': '24'}");
        String responseText = HttpClientUtils.httpPost(url, params, ContentTypeEnum.JSON);
        System.out.println(responseText);
    }

    public static JSONObject httpPostJson(String url, String strParam) {
        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        try {
            if (null != strParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(strParam, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/x-www-form-urlencoded");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(httpPost);
            // 请求发送成功，并得到响应
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String str = "";
                try {
                    // 读取服务器返回过来的json字符串数据
                    str = EntityUtils.toString(result.getEntity(), "utf-8");
                    // 把json字符串转换成json对象
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        } finally {
            httpPost.releaseConnection();
        }
        return jsonResult;
    }

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return
     */
    public static String httpGet(String url) {
        // get请求返回结果
        String result = null;
        CloseableHttpClient client = HttpClients.createDefault();
        // 发送get请求
        HttpGet request = new HttpGet(url);
        request.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = client.execute(request);
            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 读取服务器返回过来的json字符串数据
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf-8");
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        } finally {
            request.releaseConnection();
        }
        return result;
    }

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return
     */
    public static JSONObject httpGetJson(String url) {
        // get请求返回结果
        JSONObject result = null;
        CloseableHttpClient client = HttpClients.createDefault();
        // 发送get请求
        HttpGet request = new HttpGet(url);
        request.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = client.execute(request);
            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 读取服务器返回过来的json字符串数据
                HttpEntity entity = response.getEntity();
                String strResult = EntityUtils.toString(entity, "utf-8");
                // 把json字符串转换成json对象
                result = JSONObject.parseObject(strResult);
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        } finally {
            request.releaseConnection();
        }
        return result;
    }

}
