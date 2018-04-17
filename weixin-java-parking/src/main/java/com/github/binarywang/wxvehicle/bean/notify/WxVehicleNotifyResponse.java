package com.github.binarywang.wxvehicle.bean.notify;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;

/**
 * 微信异步通知共用的响应类
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxVehicleNotifyResponse {
  @XStreamOmitField
  private transient static final String FAIL = "FAIL";
  @XStreamOmitField
  private transient static final String SUCCESS = "SUCCESS";

  @XStreamAlias("return_code")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String returnCode;
  @XStreamConverter(value = XStreamCDataConverter.class)
  @XStreamAlias("return_msg")
  private String returnMsg;

  public static String fail(String msg) {
    WxVehicleNotifyResponse response = new WxVehicleNotifyResponse(FAIL, msg);
    XStream xstream = XStreamInitializer.getInstance();
    xstream.autodetectAnnotations(true);
    return xstream.toXML(response);
  }

  public static String success(String msg) {
    WxVehicleNotifyResponse response = new WxVehicleNotifyResponse(SUCCESS, msg);
    XStream xstream = XStreamInitializer.getInstance();
    xstream.autodetectAnnotations(true);
    return xstream.toXML(response);
  }

}
