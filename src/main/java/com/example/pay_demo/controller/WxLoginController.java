package com.example.pay_demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pay_demo.entity.WxEntity;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.sun.deploy.net.HttpUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping(value = "/api/client/")
public class WxLoginController {

    String uri="https://api.mch.weixin.qq.com/pay/unifiedorder";
    String secret="";  //此处需填
    String appid="";  //此处需填

    /**
     * 微信入口，获得用户的code进行跳转
     * @return
     */
    @GetMapping(value = "weChatlogin")
    public String weChatRedirect() {
//        https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
        String url = "http://zhst.hrzhst.com/shopping/api/client/pay/apiTest";
        String redirectURL ="https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid="+appid+"&redirect_uri="+url +
                "&response_type=code&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        return "redirect:" + redirectURL;
    }

    /**
     * 微信授权
     *
     * @param code 微信返回的 code 用于获取用户信息
     * @return 页面跳转
     */
    @GetMapping(value = "pay/apiTest")
    public String redirectToIndexPage(@RequestParam("code") String code) throws IOException {
        //返回需要跳转的页面锚点
        System.out.println("code:"+code);
        WxEntity wxEntity=getAccessToken(code);  //获得用户的相关信息

        System.out.println("openid:"+wxEntity.getOpenid());
        return "redirect:/api/client/pay/unifiedOrder?openid="+wxEntity.getOpenid();  //跳转到支付页面（由于没有写具体的下单流程，只是）
    }

    /**
     * 根据用户的code值向微信方请求用户的相关信息（access_token、openid、refresh_token）
     * @param code
     * @return
     * @throws IOException
     */
    public WxEntity getAccessToken(String code) throws IOException {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String uri = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = null;
        try {
            //使用HttpClient发起请求
            response = httpClient.execute(httpGet);
            //判断响应状态码是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //如果为200表示请求成功，获取返回数据
                //将返回的数据转换为字符串
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");

                //使用阿里巴巴的json转换工具，将请求返回的字符串转换为json格式，再将json格式数据转换为WxEntity类
                JSONObject jsonObject=JSONObject.parseObject(content);
                WxEntity wxEntity=JSONObject.toJavaObject(jsonObject,WxEntity.class);
                System.out.println("jsonObject:"+jsonObject.toString());
                System.out.println("wxEntity:"+wxEntity.toString());
                System.out.println(content);
                return wxEntity;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放连接
            if (response == null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                httpClient.close();
            }
        }
        return null;
    }

}
