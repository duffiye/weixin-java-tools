package com.github.binarywang.profitsharing.config;

import org.testng.annotations.Test;

/**
 * <pre>
 *  Created by BinaryWang on 2017/6/18.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxVehicleConfigTest {
  private WxVehicleConfig payConfig = new WxVehicleConfig();

  @Test
  public void testInitSSLContext() throws Exception {
    payConfig.setMchId("123");
    payConfig.setKeyPath("classpath:/abc.p12");
    payConfig.initSSLContext();
  }

}
