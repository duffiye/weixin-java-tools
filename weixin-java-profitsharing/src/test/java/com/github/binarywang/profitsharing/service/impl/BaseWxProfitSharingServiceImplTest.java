package com.github.binarywang.profitsharing.service.impl;

import com.github.binarywang.profitsharing.bean.order.ProfitSharingReceiver;
import com.github.binarywang.profitsharing.bean.request.WxProfitSharingRequest;
import com.github.binarywang.profitsharing.bean.result.WxProfitSharingResult;
import com.github.binarywang.profitsharing.constant.WxProfitSharingConstants.SignType;
import com.github.binarywang.profitsharing.exception.WxProfitSharingException;
import com.github.binarywang.profitsharing.service.WxProfitSharingService;
import com.github.binarywang.profitsharing.testbase.ApiTestModule;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * 测试支付相关接口
 * Created by Binary Wang on 2016/7/28.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class BaseWxProfitSharingServiceImplTest {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Inject
  private WxProfitSharingService wxProfitSharingService;




  @Test
  public void testWxProfitSharingPayApply() throws WxProfitSharingException {
    List<ProfitSharingReceiver> list = new ArrayList<ProfitSharingReceiver>();
    ProfitSharingReceiver profitSharingReceiver = new ProfitSharingReceiver();
    profitSharingReceiver.setType("PERSONAL_WECHATID");
    profitSharingReceiver.setAccount("duffiye");
    profitSharingReceiver.setAmount(1);
    profitSharingReceiver.setDescription("test");

    list.add(profitSharingReceiver);

    Gson gson = new Gson();
    final String json = gson.toJson(list);

    WxProfitSharingRequest request = WxProfitSharingRequest.newBuilder()
      .outTradeNo("1016675339444682753")
      .transactionId("4208450740201411110007820472")
//      .totalFee("1")
      .receivers(json)
      .build();
    request.setSignType(SignType.HMAC_SHA256);
    WxProfitSharingResult result = this.wxProfitSharingService.profitSharing(request);
    this.logger.info(result.toString());
    this.logger.warn(this.wxProfitSharingService.getWxApiData().toString());
  }





  @Test
  public void testGetConfig() throws Exception {
    // no need to test
  }

  @Test
  public void testSetConfig() throws Exception {
    // no need to test
  }


  @Test
  public void testGetWxApiData() throws Exception {
    //see test in testUnifiedOrder()
  }

}
