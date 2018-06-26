package com.github.binarywang.wxvehicle.service.impl;

import com.github.binarywang.wxvehicle.bean.notify.WxVehicleOrderNotifyResult;
import com.github.binarywang.wxvehicle.bean.request.WxVehicleNotificationRequest;
import com.github.binarywang.wxvehicle.bean.request.WxVehiclePayApplyRequest;
import com.github.binarywang.wxvehicle.bean.request.WxVehicleQueryStateRequest;
import com.github.binarywang.wxvehicle.bean.result.WxVehicleNotificationResult;
import com.github.binarywang.wxvehicle.bean.result.WxVehiclePayapplyResult;
import com.github.binarywang.wxvehicle.bean.result.WxVehicleQueryStateResult;
import com.github.binarywang.wxvehicle.constant.WxVehicleConstants.SignType;
import com.github.binarywang.wxvehicle.exception.WxVehicleException;
import com.github.binarywang.wxvehicle.service.WxVehicleService;
import com.github.binarywang.wxvehicle.testbase.ApiTestModule;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Date;


/**
 * 测试支付相关接口
 * Created by Binary Wang on 2016/7/28.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class BaseWxVehicleServiceImplTest {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Inject
  private WxVehicleService wxVehicleService;


  @Test
  public void testWxVehicleNotification() throws WxVehicleException {
    WxVehicleNotificationRequest request = WxVehicleNotificationRequest.newBuilder()
      .sceneInfo("{\"scene_info\":{\"start_time\":\"20180502094339\",\"plate_number\":\"湘A6L46C\",\"car _type\":\"小型车\",\"parking_name\":\"欢乐海岸停车场\",\"free_time\":\"1200\"}}")
      .tradeScene("PARKING")
      .build();
    request.setSignType(SignType.HMAC_SHA256);
    WxVehicleNotificationResult result = this.wxVehicleService.notification(request);
    this.logger.info(result.toString());
    this.logger.warn(this.wxVehicleService.getWxApiData().toString());
  }

  @Test
  public void testWxVehiclePayApply() throws WxVehicleException {
    WxVehiclePayApplyRequest request = WxVehiclePayApplyRequest.newBuilder()
      .body("停车费")
      .outTradeNo("T" + new Date().getTime())
      .feeType("CNY")
      .spbillCreateIp("127.0.0.1")
      .notifyUrl("http://www.baidu.com")
      .tradeType("PAP")
      .totalFee("1")
      .sceneInfo("{\"scene_info\":{\"start_time\":\"20180509114339\",\"plate_number\":\"湘A6L46C\",\"car _type\":\"大型车\",\"parking_name\":\"欢乐海岸停车场\",\"free_time\":\"1200\" ,\"charging_time\":\"3600\",}}")
      .tradeScene("PARKING")
      .build();
    request.setSignType(SignType.HMAC_SHA256);
    WxVehiclePayapplyResult result = this.wxVehicleService.payApply(request);
    this.logger.info(result.toString());
    this.logger.warn(this.wxVehicleService.getWxApiData().toString());
  }

  @Test
  public void testParseOrderNotifyResult() throws Exception {
    // 请参考com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResultTest 里的单元测试

    String xmlString = "<xml>\n" +
      "  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
      "  <attach><![CDATA[支付测试]]></attach>\n" +
      "  <bank_type><![CDATA[CFT]]></bank_type>\n" +
      "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
      "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
      "  <mch_id><![CDATA[10000100]]></mch_id>\n" +
      "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
      "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
      "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
      "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
      "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
      "  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" +
      "  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>\n" +
      "  <time_end><![CDATA[20140903131540]]></time_end>\n" +
      "  <total_fee>1</total_fee>\n" +
      "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
      "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
      "   <coupon_count>2</coupon_count>\n" +
      "   <coupon_type_0><![CDATA[CASH]]></coupon_type_0>\n" +
      "   <coupon_id_0>10000</coupon_id_0>\n" +
      "   <coupon_fee_0>100</coupon_fee_0>\n" +
      "   <coupon_type_1><![CDATA[NO_CASH]]></coupon_type_1>\n" +
      "   <coupon_id_1>10001</coupon_id_1>\n" +
      "   <coupon_fee_1>200</coupon_fee_1>\n" +
      "</xml>";

    WxVehicleOrderNotifyResult result = this.wxVehicleService.parseOrderNotifyResult(xmlString);
    System.out.println(result);
  }

  @Test
  public void testWxQueryState() throws WxVehicleException {
    WxVehicleQueryStateRequest request = WxVehicleQueryStateRequest.newBuilder()
      .subOpenid("11111111111")
//      .plateNumber("")
      .tradeScene("PARKING")
      .build();
    request.setSignType(SignType.HMAC_SHA256);
    WxVehicleQueryStateResult result = this.wxVehicleService.queryState(request);
    this.logger.info(result.toString());
    this.logger.warn(this.wxVehicleService.getWxApiData().toString());
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
