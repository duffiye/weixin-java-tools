package com.github.binarywang.wxvehicle.bean.request;

import com.github.binarywang.wxvehicle.exception.WxVehicleException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxVehicleQueryStateRequest  extends BaseWxVehicleRequest {

  /**
   * <pre>
   * 交易场景
   * trade_scene
   * 委托代扣的交易场景值，目前支持 : 1. PARKING:车场停车场景；2. PARKING SPACE 车位停车场景；3. GAS 加油场景；4. HIGHWAY 高速场景；该值会向微信用户进行展示
   * </pre>
   */
  @Required
  @XStreamAlias("trade_scene")
  private String tradeScene;

  /**
   * <pre>
   * 跳转场景
   * jump_scene
   * 商户跳转的业务场景，目前支持小程序和H5跳转，默认是小程序
   * </pre>
   */
  @XStreamAlias("jump_scene")
  private String jumpScene;

  /**
   * <pre>
   * 用户标识
   * openid
   * 此参数必传，用户在商户appid下的唯一标识。openid和sub_openid可以选传其中之一，如果选择传sub_openid,则必须传sub_appid
   * </pre>
   */
  @XStreamAlias("openid")
  private String openid;


  /**
   * <pre>
   * 用户子标识
   * sub_openid
   * 此参数必传，用户在子商户appid下的唯一标识。openid和sub_openid可以选传其中之一，如果选择传sub_openid,则必须传sub_appid
   * </pre>
   */
  @XStreamAlias("sub_openid")
  private String subOpenid;


  /**
   * <pre>
   * 车牌号
   * plate_number
   * 车牌号。仅包括省份+车牌，不包括特殊字符。
   * </pre>
   */
  @XStreamAlias("plate_number")
  private String plateNumber;


  @Override
  protected void checkConstraints() throws WxVehicleException {

  }
}
