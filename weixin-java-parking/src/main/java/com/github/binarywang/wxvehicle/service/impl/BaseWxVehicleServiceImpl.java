package com.github.binarywang.wxvehicle.service.impl;

import com.github.binarywang.wxvehicle.bean.WxVehicleApiData;
import com.github.binarywang.wxvehicle.bean.request.WxVehicleNotificationRequest;
import com.github.binarywang.wxvehicle.bean.request.WxVehiclePayapplyRequest;
import com.github.binarywang.wxvehicle.bean.result.BaseWxVehicleResult;
import com.github.binarywang.wxvehicle.bean.request.WxVehicleDefaultRequest;
import com.github.binarywang.wxvehicle.bean.result.WxVehiclePayapplyResult;
import com.github.binarywang.wxvehicle.bean.result.WxVehicleSandboxSignKeyResult;
import com.github.binarywang.wxvehicle.bean.result.WxVehicleNotificationResult;
import com.github.binarywang.wxvehicle.config.WxVehicleConfig;
import com.github.binarywang.wxvehicle.exception.WxVehicleException;
import com.github.binarywang.wxvehicle.service.WxVehicleService;
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

    String url = this.getVehicleBaseUrl() + "/vehicle/pay/notification";

    String responseContent = this.post(url, wxVehicleNotificationRequest.toXML(), true);
    WxVehicleNotificationResult wxVehicleNotificationResult = BaseWxVehicleResult.fromXML(responseContent, WxVehicleNotificationResult.class);
    wxVehicleNotificationResult.checkResult(this, wxVehicleNotificationRequest.getSignType(), true);
    return wxVehicleNotificationResult;
  }

  @Override
  public WxVehiclePayapplyResult payapply(WxVehiclePayapplyRequest wxVehiclePayapplyRequest) throws WxVehicleException {
    wxVehiclePayapplyRequest.checkAndSign(this.getConfig());
    String url = this.getVehicleBaseUrl() + "/vehicle/pay/payapply";
    String responseContent = this.post(url, wxVehiclePayapplyRequest.toXML(), true);
    WxVehiclePayapplyResult wxVehiclePayapplyResult = BaseWxVehicleResult.fromXML(responseContent, WxVehiclePayapplyResult.class);
    return wxVehiclePayapplyResult;
  }
}
