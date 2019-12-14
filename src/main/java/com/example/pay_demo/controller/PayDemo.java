package com.example.pay_demo.controller;

import com.example.pay_demo.entity.PayEntity;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/client/pay/")
public class PayDemo {
    String uri="https://api.mch.weixin.qq.com/pay/unifiedorder";
    String nonce_str,openid;
    String sign;
    String body;
    String out_trade_no;
    String spbill_create_ip="";
    String signType=WXPayConstants.MD5;
    String key="";  //此处需填
    String trade_type="JSAPI";
    String appid="";  //此处需填
    String mch_id="";  //此处需填
    String notify_url="http://zhst.hrzhst.com/shopping/api/client/pay/wxPayNotify";
    int total_fee=1;


    /**
     * 支付结果回调
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("wxPayNotify")
    @ResponseBody
    public String notify(HttpServletRequest request) throws Exception {
        String xml="";
        String line="";
        while ((line=request.getReader().readLine())!=null){
            xml+=line;
        }
        Map<String,String> map=new HashMap<>();
        System.out.println(xml);
        map=WXPayUtil.xmlToMap(xml);
        System.out.println("支付结果回调："+map.toString());
        PayEntity payEntity=new PayEntity( appid,  mch_id,  nonce_str,  sign,  body,
                out_trade_no,  total_fee,  spbill_create_ip,  notify_url,  openid,key);
        payEntity.setTrade_type("JSAPI");
        WXPay wxPay=new WXPay(payEntity);
        boolean bool=wxPay.isPayResultNotifySignatureValid(map);  //判断支付成功回调签名是否正确（此处验证签名目的为判断是否微信微信支付回调的，避免被直接调用此请求，修改订单结果，此处并未对数据库进行操作，因此不做处理）
        String returnStr=
                "<xml>" +
                "<return_code><![CDATA[SUCCESS]]></return_code>" +
                 "<return_msg><![CDATA[OK]]></return_msg>" +
                 "</xml>";  //此字符串为接受回调成功后向微信方返回数据，否则微信方进行三次推送
        return returnStr;

    }


    /**
     * 支付页面调用
     * 此处的openid为前面跳转的页面传递过来的，此处为普通的请求，但需要传入用户的相关信息
     * 由于并无具体的下单业务，因此（本人）就直接从微信入口获得openid后直接就带着openid跳转到支付页面
     * @param model
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @GetMapping(value = "unifiedOrder")
    public String demo(Model model,String openid,HttpServletRequest httpServletRequest) throws Exception {

        spbill_create_ip=getIpAddr(httpServletRequest);
        nonce_str=WXPayUtil.generateNonceStr();  //
        out_trade_no=String.valueOf(System.currentTimeMillis());  //个人系统订单号
        System.out.println("系统订单号："+out_trade_no);
        body="测试123ss";
        Map<String,String> map=new HashMap<>();
        map.put("total_fee",String.valueOf(total_fee));
        map.put("appid",appid);
        map.put("mch_id",mch_id);
        map.put("notify_url",notify_url);
        map.put("out_trade_no",out_trade_no);
        map.put("spbill_create_ip",spbill_create_ip);
        map.put("body",body);
        map.put("openid",openid);
        map.put("trade_type",trade_type);

        sign=WXPayUtil.generateSignature(map,key);  //调用微信api生成签名
        String xml=WXPayUtil.mapToXml(map);
        System.out.println(xml);
        PayEntity payEntity=new PayEntity( appid,  mch_id,  nonce_str,  sign,  body,
                 out_trade_no,  total_fee,  spbill_create_ip,  notify_url,  openid,key);
        payEntity.setTrade_type(trade_type);
        System.out.println("payentity:"+payEntity.toString());

        Map<String,String> map1=new HashMap<>();
        WXPay wxPay=new WXPay(payEntity);
        map1=wxPay.unifiedOrder(map);  //使用微信api，获得prepay_id值，用于后面调起微信支付页面
        System.out.println("map:"+map1.toString());

        String packages="prepay_id="+map1.get("prepay_id");

        long timeStamp=getCurrentTimestamp();
        String nonceStr=WXPayUtil.generateNonceStr();

        Map<String,String> pagePrams=new HashMap<>();
        pagePrams.put("appId", appid);
        pagePrams.put("nonceStr", nonceStr);
        pagePrams.put("package",  packages);
        pagePrams.put("signType", signType);
        pagePrams.put("timeStamp", String.valueOf(timeStamp));

        System.out.println(pagePrams.toString());
        String paySign=WXPayUtil.generateSignature(pagePrams,key);
        pagePrams.put("paySign", paySign);
        String xml2=WXPayUtil.mapToXml(pagePrams);
        System.out.println(xml2);


        model.addAttribute("appId",appid);
        model.addAttribute("timeStamp",timeStamp);
        model.addAttribute("nonceStr",nonceStr);
        model.addAttribute("package",packages);
        model.addAttribute("signType",signType);
        model.addAttribute("paySign",paySign);


        System.out.println(model.toString());

        return "index2";  //进行页面跳转，此页面位于resource下的templates下的index2.html
    }

    /**
     * 微信退款
     * 此处需要微信证书，证书获取已经在PayEntity类中
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("reback")
    @ResponseBody
    public String reback(HttpServletRequest httpServletRequest) throws Exception {
        spbill_create_ip=getIpAddr(httpServletRequest);

        nonce_str=WXPayUtil.generateNonceStr();
        notify_url="http://zhst.hrzhst.com/shopping/api/client/pay/wxPayNotify";  //退款成功回调
        out_trade_no=String.valueOf(System.currentTimeMillis());  //生成订单号
        body="测试123ss";
        openid="or6Jb1ZOLLdq5Dt3F3AMoSZ94b68";  //此处为用户的openid，应该动态获取（此处不应被写死）

        String transaction_id="4200000448201911249460503079";  //微信订单号（微信端生成的）
//        String out_trade_no="1575465531989";  //商户订单号（自己系统的订单号）
        //以上的为二选一

        String out_refund_no="1575465531989";  //商户退款单号（自己系统的订单号）
        int total_fee=1;
        int refund_fee=1;
        String refund_desc="退款原因";
        String notify_url="";
        Map<String,String> rebackMap=new HashMap<>();
        rebackMap.put("appid",appid);
        rebackMap.put("mch_id",mch_id);
        rebackMap.put("nonce_str",WXPayUtil.generateNonceStr());

//        rebackMap.put("transaction_id",out_refund_no);  //加入微信支付单号，支付成功后支付回调中有（与下面一条二选一）
        rebackMap.put("out_trade_no",out_refund_no);  //加入自己系统中的订单号

        rebackMap.put("out_refund_no",out_refund_no);  //退款订单号
        rebackMap.put("total_fee",String.valueOf(total_fee));  //改订单总金额
        rebackMap.put("refund_fee",String.valueOf(refund_fee));  //改订单退款金额
        rebackMap.put("refund_desc",refund_desc);
        String sign=WXPayUtil.generateSignature(rebackMap,key);
        PayEntity payEntity=new PayEntity( appid,  mch_id,  nonce_str,  sign,  body,
                out_trade_no,  total_fee,  spbill_create_ip,  notify_url,  openid,key);
        payEntity.setTrade_type("JSAPI");
        WXPay wxPay=new WXPay(payEntity);
        Map<String,String> result=new HashMap<>();
        result=wxPay.refund(rebackMap);   //调起微信退款
        System.out.println(rebackMap.toString());
        System.out.println(result.toString());






        return rebackMap.toString();
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

        /**
     * 获取当前时间戳，单位秒
     * @return
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis()/1000;
    }

    /**
     * 获取当前时间戳，单位毫秒
     * @return
     */
    public static long getCurrentTimestampMs() {
        return System.currentTimeMillis();
    }
}
