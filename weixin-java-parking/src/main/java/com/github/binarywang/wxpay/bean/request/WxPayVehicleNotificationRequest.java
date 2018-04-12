package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxPayVehicleNotificationRequest extends BaseWxPayRequest {


  /**
   * <pre>
   * 交易场景
   * trade_scene
   * 委托代扣的交易场景值，目 前支持 : 1. PARKING:车 场停车场景 ;2. PARKING SPACE 车位停车场景;该值 催缴时会向微信用户迚行展 示
   * </pre>
   */
  @Required
  @XStreamAlias("trade_scene")
  private String tradecene;

  /**
   * <pre>
   *     场景信息
   *     {"scene_info":{"star t_time":"20170926 114339","plate_nu mber":"CB1000sdf asd","free_time":"1 200","car_type":"大 型车 ","parking_name":" 欢乐海岸停车场"}}
   *     场景信息值，格式为 json， 丌同业务场景设置丌同的 值，具体如后面所列。
   * </pre>
   */
  @Required
  @XStreamAlias("scene_info")
  private String sceneInfo;


  public String getTradecene() {
    return tradecene;
  }

  public WxPayVehicleNotificationRequest setTradecene(String tradecene) {
    this.tradecene = tradecene;
    return this;
  }

  public String getSceneInfo() {
    return sceneInfo;
  }

  public WxPayVehicleNotificationRequest setSceneInfo(String sceneInfo) {
    this.sceneInfo = sceneInfo;
    return this;
  }

  @Override
  protected void checkConstraints() throws WxPayException {

  }
}
