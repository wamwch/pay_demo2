package com.example.pay_demo.entity;

import com.github.wxpay.sdk.WXPayConfig;
import lombok.Data;

import java.io.*;
import java.net.URL;

@Data
public class PayEntity implements WXPayConfig {
    /**
     * 公众号id
     */
    private String appid;

    /**
     * 商户号
     */
    private String mch_id;

    /**
     * 随机字符串
     */
    private String nonce_str;

    /**
     * 签名
     */
    private String sign;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 商户订单号
     */
    private String out_trade_no;

    /**
     * 标价金额
     */
    private int total_fee;

    /**
     * 终端ip
     */
    private String spbill_create_ip;

    /**
     * 通知地址
     */
    private String notify_url;

    /**
     * 交易类型
     */
    private String trade_type;

    /**
     * 用户标识
     */
    private String openid;

    private String key;

    public PayEntity(String nonce_str, String sign, String body, String out_trade_no, int total_fee, String spbill_create_ip,String notify_url,String appid) {
        this.nonce_str = nonce_str;
        this.sign = sign;
        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.spbill_create_ip = spbill_create_ip;
        this.notify_url=notify_url;
    }

    public PayEntity(String appid, String mch_id, String nonce_str, String sign, String body,
                     String out_trade_no, int total_fee, String spbill_create_ip, String notify_url, String openid,String key) {
        this.appid = appid;
        this.mch_id = mch_id;
        this.nonce_str = nonce_str;
        this.sign = sign;
        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.spbill_create_ip = spbill_create_ip;
        this.notify_url = notify_url;
        this.openid = openid;
        this.key=key;
    }

    @Override
    public String getAppID() {
        return this.appid;
    }

    @Override
    public String getMchID() {
        return this.getMch_id();
    }

    @Override
    public String getKey() {
        return this.key;
    }

    /**
     * 从类路径下加载微信证书
     * @return
     */
    @Override
    public InputStream getCertStream() {
        ClassLoader classLoader=getClass().getClassLoader();
        URL url=classLoader.getResource("apiclient_cert.p12");
        File file=new File(url.getFile());
        try {
            InputStream inputStream=new FileInputStream(file);
            return  inputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }
}
