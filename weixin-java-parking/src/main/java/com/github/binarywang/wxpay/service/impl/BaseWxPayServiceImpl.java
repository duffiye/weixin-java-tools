package com.github.binarywang.wxpay.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipException;

import com.github.binarywang.wxpay.bean.result.WxPayDefaultRequest;
import com.github.binarywang.wxpay.bean.result.WxPaySandboxSignKeyResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.github.binarywang.wxpay.bean.WxPayApiData;
import com.github.binarywang.wxpay.bean.coupon.WxPayCouponInfoQueryRequest;
import com.github.binarywang.wxpay.bean.coupon.WxPayCouponInfoQueryResult;
import com.github.binarywang.wxpay.bean.coupon.WxPayCouponSendRequest;
import com.github.binarywang.wxpay.bean.coupon.WxPayCouponSendResult;
import com.github.binarywang.wxpay.bean.coupon.WxPayCouponStockQueryRequest;
import com.github.binarywang.wxpay.bean.coupon.WxPayCouponStockQueryResult;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants.BillType;
import com.github.binarywang.wxpay.constant.WxPayConstants.SignType;
import com.github.binarywang.wxpay.constant.WxPayConstants.TradeType;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayVehicleService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import jodd.io.ZipUtil;

import static com.github.binarywang.wxpay.constant.WxPayConstants.QUERY_COMMENT_DATE_FORMAT;
import static com.github.binarywang.wxpay.constant.WxPayConstants.TarType;

/**
 * <pre>
 *  微信支付接口请求抽象实现类
 * Created by Binary Wang on 2017-7-8.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public abstract class BaseWxPayServiceImpl implements WxPayVehicleService {
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


  @Override
  public String getSandboxSignKey() throws WxPayException {
    WxPayDefaultRequest request = new WxPayDefaultRequest();
    request.checkAndSign(this.getConfig());

    String url = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
    String responseContent = this.post(url, request.toXML(), false);
    WxPaySandboxSignKeyResult result = BaseWxPayResult.fromXML(responseContent, WxPaySandboxSignKeyResult.class);
    result.checkResult(this, request.getSignType(), true);
    return result.getSandboxSignKey();
  }



  @Override
  public WxPayApiData getWxApiData() {
    try {
      return wxApiData.get();
    } finally {
      //一般来说，接口请求会在一个线程内进行，这种情况下，每个线程get的会是之前所存入的数据，
      // 但以防万一有同一线程多次请求的问题，所以每次获取完数据后移除对应数据
      wxApiData.remove();
    }
  }

}
