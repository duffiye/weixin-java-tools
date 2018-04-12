package com.github.binarywang.wxvehicle.testbase;

import com.github.binarywang.wxvehicle.config.WxVehicleConfig;
import com.github.binarywang.wxvehicle.service.WxVehicleService;
import com.github.binarywang.wxvehicle.service.impl.WxVehicleServiceImpl;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.thoughtworks.xstream.XStream;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;

import java.io.IOException;
import java.io.InputStream;

public class ApiTestModule implements Module {
  private static final String TEST_CONFIG_XML = "test-config.xml";

  @Override
  public void configure(Binder binder) {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(TEST_CONFIG_XML)) {
      if (inputStream == null) {
        throw new RuntimeException("测试配置文件【" + TEST_CONFIG_XML + "】未找到");
      }

      XmlWxVehicleConfig config = this.fromXml(XmlWxVehicleConfig.class, inputStream);
      WxVehicleService wxService = new WxVehicleServiceImpl();
      wxService.setConfig(config);

      binder.bind(WxVehicleService.class).toInstance(wxService);
      binder.bind(WxVehicleConfig.class).toInstance(config);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private <T> T fromXml(Class<T> clazz, InputStream is) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.alias("xml", clazz);
    xstream.processAnnotations(clazz);
    return (T) xstream.fromXML(is);
  }

}
