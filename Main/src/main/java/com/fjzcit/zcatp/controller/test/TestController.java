package com.fjzcit.zcatp.controller.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@Api(value = "Swagger2接口测试")
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation(value = "测试方法！", httpMethod = "GET")
    public Object test() {
        return "Hello World!";
    }

    /**
     * 测试返回Cookies
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @ApiOperation(value = "测试返回Cookies！", httpMethod = "GET")
    public Object getCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        cookie = new Cookie("sessionId", "10086");
        response.addCookie(cookie);

        return "成功获取Cookies！";
    }

    /**
     * 开发一个需要携带Cookie才能访问的Get请求接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/getWithCookies", method = RequestMethod.GET)
    @ApiOperation(value = "开发一个需要携带Cookie才能访问的Get请求接口！", httpMethod = "GET")
    public Object getWithCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)) {
            return "GET请求没有携带Cookie，请求失败！";
        }
        for(Cookie cookie : cookies) {
            if ("login".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                return "成功完成一个带Cookie的GET请求！";
            }
        }
        return "Cookie错误，请求失败！";
    }

    /**
     * 开发一个需要携带参数才能访问的Get接口
     * 参数格式：key1=value1&key2=value2
     */
    @RequestMapping(value = "/getWithParams01", method = RequestMethod.GET)
    @ApiOperation(value = "开发一个需要携带参数才能访问的Get接口！", httpMethod = "GET")
    public Object getWithParams01(@RequestParam String p1, @RequestParam String p2) {
        List<String> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        return list;
    }

    /**
     * 开发一个需要携带参数才能访问的Get接口
     * 参数格式：/uri/value1&/value2
     */
    @RequestMapping(value = "/getWithParams02/{p1}/{p2}", method = RequestMethod.GET)
    @ApiOperation(value = "开发另一个需要携带参数才能访问的Get接口！", httpMethod = "GET")
    public Object getWithParams02(@PathVariable String p1, @PathVariable String p2) {
        List<String> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        return list;
    }
}
