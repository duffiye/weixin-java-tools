package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.WxPayApiData;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.PapayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *  微信支付接口请求抽象实现类
 * Created by Binary Wang on 2017-7-8.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public abstract class BaseWxPapayServiceImpl implements PapayService {
  private static final String PAY_BASE_URL = "https://api.mch.weixin.qq.com";
  protected final Logger log = LoggerFactory.getLogger(this.getClass());
  protected static ThreadLocal<WxPayApiData> wxApiData = new ThreadLocal<>();


  protected WxPayConfig config;


  @Override
  public WxPayConfig getConfig() {
    return this.config;
  }

  @Override
  public void setConfig(WxPayConfig config) {
    this.config = config;
  }

  @Override
  public String getPayBaseUrl() {
    if (this.getConfig().useSandbox()) {
      return PAY_BASE_URL + "/sandboxnew";
    }

    return PAY_BASE_URL;
  }







}
