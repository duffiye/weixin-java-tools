package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.papay.PapayApplyRequest;
import com.github.binarywang.wxpay.bean.papay.PapayApplyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.PapayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * <pre>
 *  企业付款测试类.
 *  Created by BinaryWang on 2017/12/19.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class PaPayServiceImplTest {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Inject
  private PapayService papayService;


  @Test
  public void testPayBank(){


    PapayApplyResult result = new PapayApplyResult();
    try {
      result = this.papayService.apply(PapayApplyRequest.newBuilder()
        .out_trade_no("2018080814390001")
        .body("body")
        .detail("detail")
        .contract_id("Wx15463511252015071056489715")
        .attach("")
        .total_fee("1")
        .spbill_create_ip("127.0.0.1")
        .trade_type("PAP")
        .notify_url("http://yoursite.com/wxpay.html")



        .build());
    } catch (WxPayException e) {
      e.printStackTrace();
      logger.info(e.getErrCode());
    }

    this.logger.info("result 信息====================" + result.getReturnCode());



  }
}
