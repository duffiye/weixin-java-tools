package com.github.binarywang.wxvehicle.service.impl;

import com.github.binarywang.wxvehicle.bean.WxVehicleApiData;
import com.github.binarywang.wxvehicle.bean.notify.WxVehicleOrderNotifyResult;
import com.github.binarywang.wxvehicle.bean.request.*;
import com.github.binarywang.wxvehicle.bean.result.*;
import com.github.binarywang.wxvehicle.config.WxVehicleConfig;
import com.github.binarywang.wxvehicle.exception.WxVehicleException;
import com.github.binarywang.wxvehicle.service.WxVehicleService;
import org.apache.commons.lang3.StringUtils;
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
public abstract class BaseWxVehicleServiceImpl implements WxVehicleService {
  private static final String VEHICLE_BASE_URL = "https://api.mch.weixin.qq.com";
  protected final Logger log = LoggerFactory.getLogger(this.getClass());
  protected static ThreadLocal<WxVehicleApiData> wxApiData = new ThreadLocal<>();


  protected WxVehicleConfig config;


  @Override
  public WxVehicleConfig getConfig() {
    return this.config;
  }

  @Override
  public void setConfig(WxVehicleConfig config) {
    this.config = config;
  }


  @Override
  public String getVehicleBaseUrl() {
    if (this.getConfig().useSandbox()) {
      return VEHICLE_BASE_URL + "/sandboxnew";
    }

    return VEHICLE_BASE_URL;
  }

  @Override
  public String getSandboxSignKey() throws WxVehicleException {
    WxVehicleDefaultRequest request = new WxVehicleDefaultRequest();
    request.checkAndSign(this.getConfig());

    String url = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
    String responseContent = this.post(url, request.toXML(), false);
    WxVehicleSandboxSignKeyResult result = BaseWxVehicleResult.fromXML(responseContent, WxVehicleSandboxSignKeyResult.class);
    result.checkResult(this, request.getSignType(), true);
    return result.getSandboxSignKey();
  }



  @Override
  public WxVehicleApiData getWxApiData() {
    try {
      return wxApiData.get();
    } finally {
      //一般来说，接口请求会在一个线程内进行，这种情况下，每个线程get的会是之前所存入的数据，
      // 但以防万一有同一线程多次请求的问题，所以每次获取完数据后移除对应数据
      wxApiData.remove();
    }
  }


  @Override
  public WxVehicleNotificationResult notification(WxVehicleNotificationRequest wxVehicleNotificationRequest) throws WxVehicleException {
    wxVehicleNotificationRequest.checkAndSign(this.getConfig());

    String url = this.getVehicleBaseUrl() + "/vehicle/partnerpay/notification";

    String responseContent = this.post(url, wxVehicleNotificationRequest.toXML(), false);
    WxVehicleNotificationResult wxVehicleNotificationResult = BaseWxVehicleResult.fromXML(responseContent, WxVehicleNotificationResult.class);
    wxVehicleNotificationResult.checkResult(this, wxVehicleNotificationRequest.getSignType(), true);
    return wxVehicleNotificationResult;
  }

  @Override
  public WxVehiclePayapplyResult payApply(WxVehiclePayApplyRequest wxVehiclePayApplyRequest) throws WxVehicleException {
    wxVehiclePayApplyRequest.checkAndSign(this.getConfig());
    String url = this.getVehicleBaseUrl() + "/vehicle/partnerpay/payapply";
    String responseContent = this.post(url, wxVehiclePayApplyRequest.toXML(), false);
    WxVehiclePayapplyResult wxVehiclePayapplyResult = BaseWxVehicleResult.fromXML(responseContent, WxVehiclePayapplyResult.class);
    return wxVehiclePayapplyResult;
  }

  @Override
  public WxVehicleOrderNotifyResult parseOrderNotifyResult(String xmlData) throws WxVehicleException {
    try {
      log.debug("微信支付异步通知请求参数：{}", xmlData);
      WxVehicleOrderNotifyResult result = WxVehicleOrderNotifyResult.fromXML(xmlData);
      log.debug("微信支付异步通知请求解析后的对象：{}", result);
      result.checkResult(this, this.getConfig().getSignType(), false);
      return result;
    } catch (WxVehicleException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new WxVehicleException("发生异常，" + e.getMessage(), e);
    }
  }

  @Override
  public WxVehicleQueryOrderResult queryOrder(String transactionId, String outTradeNo) throws WxVehicleException {
    WxVehicleQueryOrderRequest request = new WxVehicleQueryOrderRequest();
    request.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
    request.setTransactionId(StringUtils.trimToNull(transactionId));
    request.setSignType("HMAC-SHA256");
    request.checkAndSign(this.getConfig());

    String url = this.getVehicleBaseUrl() + "/transit/partnerpay/queryorder";
    String responseContent = this.post(url, request.toXML(), false);
    if (StringUtils.isBlank(responseContent)) {
      throw new WxVehicleException("无响应结果");
    }

    WxVehicleQueryOrderResult result = BaseWxVehicleResult.fromXML(responseContent, WxVehicleQueryOrderResult.class);
    result.composeCoupons();
    result.checkResult(this, request.getSignType(), true);
    return result;
  }

  @Override
  public WxVehicleQueryStateResult queryState(WxVehicleQueryStateRequest wxVehicleQueryStateRequest) throws WxVehicleException {
    wxVehicleQueryStateRequest.checkAndSign(this.getConfig());
    String url = this.getVehicleBaseUrl() + "/vehicle/partnerpay/querystate";
    String responseContent = this.post(url, wxVehicleQueryStateRequest.toXML(), false);
    WxVehicleQueryStateResult result = BaseWxVehicleResult.fromXML(responseContent, WxVehicleQueryStateResult.class);
    return result;
  }
}
