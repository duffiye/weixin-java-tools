package com.github.binarywang.wxvehicle.service.impl;

import com.github.binarywang.wxvehicle.bean.WxProfitSharingApiData;
import com.github.binarywang.wxvehicle.bean.request.WxProfitSharingDefaultRequest;
import com.github.binarywang.wxvehicle.bean.request.WxProfitSharingRequest;
import com.github.binarywang.wxvehicle.bean.result.BaseWxProfitSharingResult;
import com.github.binarywang.wxvehicle.exception.WxProfitSharingException;
import com.github.binarywang.wxvehicle.service.WxProfitSharingService;
import com.github.binarywang.wxvehicle.bean.result.WxProfitSharingResult;
import com.github.binarywang.wxvehicle.bean.result.WxProfitSharingSandboxSignKeyResult;
import com.github.binarywang.wxvehicle.config.WxProfitSharingConfig;
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
public abstract class BaseWxProfitSharingServiceImpl implements WxProfitSharingService {
  private static final String VEHICLE_BASE_URL = "https://api.mch.weixin.qq.com";
  protected final Logger log = LoggerFactory.getLogger(this.getClass());
  protected static ThreadLocal<WxProfitSharingApiData> wxApiData = new ThreadLocal<>();


  protected WxProfitSharingConfig config;


  @Override
  public WxProfitSharingConfig getConfig() {
    return this.config;
  }

  @Override
  public void setConfig(WxProfitSharingConfig config) {
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
  public String getSandboxSignKey() throws WxProfitSharingException {
    WxProfitSharingDefaultRequest request = new WxProfitSharingDefaultRequest();
    request.checkAndSign(this.getConfig());

    String url = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
    String responseContent = this.post(url, request.toXML(), false);
    WxProfitSharingSandboxSignKeyResult result = BaseWxProfitSharingResult.fromXML(responseContent, WxProfitSharingSandboxSignKeyResult.class);
    result.checkResult(this, request.getSignType(), true);
    return result.getSandboxSignKey();
  }



  @Override
  public WxProfitSharingApiData getWxApiData() {
    try {
      return wxApiData.get();
    } finally {
      //一般来说，接口请求会在一个线程内进行，这种情况下，每个线程get的会是之前所存入的数据，
      // 但以防万一有同一线程多次请求的问题，所以每次获取完数据后移除对应数据
      wxApiData.remove();
    }
  }

  @Override
  public WxProfitSharingResult profitSharing(WxProfitSharingRequest wxProfitSharingRequest) throws WxProfitSharingException {
    wxProfitSharingRequest.checkAndSign(this.getConfig());
    String url = this.getVehicleBaseUrl() + "/secapi/pay/wxvehicle";
    String responseContent = this.post(url, wxProfitSharingRequest.toXML(), true);
    WxProfitSharingResult wxProfitSharingResult = BaseWxProfitSharingResult.fromXML(responseContent, WxProfitSharingResult.class);
    return wxProfitSharingResult;
  }




}
