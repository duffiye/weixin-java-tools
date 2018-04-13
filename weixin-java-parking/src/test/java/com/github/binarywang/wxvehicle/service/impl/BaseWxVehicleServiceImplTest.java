package com.github.binarywang.wxvehicle.service.impl;

import com.github.binarywang.wxvehicle.bean.request.WxVehicleNotificationRequest;
import com.github.binarywang.wxvehicle.bean.request.WxVehiclePayapplyRequest;
import com.github.binarywang.wxvehicle.bean.result.WxVehicleNotificationResult;
import com.github.binarywang.wxvehicle.bean.result.WxVehiclePayapplyResult;
import com.github.binarywang.wxvehicle.constant.WxVehicleConstants.SignType;
import com.github.binarywang.wxvehicle.exception.WxVehicleException;
import com.github.binarywang.wxvehicle.service.WxVehicleService;
import com.github.binarywang.wxvehicle.testbase.ApiTestModule;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

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
      .sceneInfo("{\"scene_info\":{\"start_time\":\"20170926114339\",\"plate_number\":\"CB1000sdfasd\",\"car _type\":\"大型车\",\"parking_name\":\"欢乐海岸停车场\",\"free_time\":\"1200\"}}")
      .tradeScene("PARKING")
      .build();
    request.setSignType(SignType.HMAC_SHA256);
    WxVehicleNotificationResult result = this.wxVehicleService.notification(request);
    this.logger.info(result.toString());
    this.logger.warn(this.wxVehicleService.getWxApiData().toString());
  }

  @Test
  public void testWxVehiclePayapply() throws WxVehicleException {
    WxVehiclePayapplyRequest request = WxVehiclePayapplyRequest.newBuilder()
      .sceneInfo("{\"scene_info\":{\"start_time\":\"20170926114339\",\"plate_number\":\"CB1000sdfasd\",\"car _type\":\"大型车\",\"parking_name\":\"欢乐海岸停车场\",\"free_time\":\"1200\"}}")
      .tradeScene("PARKING")
      .build();
    request.setSignType(SignType.HMAC_SHA256);
    WxVehiclePayapplyResult result = this.wxVehicleService.payapply(request);
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
