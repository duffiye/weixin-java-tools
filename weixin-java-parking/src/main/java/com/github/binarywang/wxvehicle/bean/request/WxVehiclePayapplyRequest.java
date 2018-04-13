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
public class WxVehiclePayapplyRequest extends BaseWxVehicleRequest {

  /**
   * <pre>
   * 商品描述
   * body
   * 商品或支付单简要描述
   * </pre>
   */
  @Required
  @XStreamAlias("body")
  private String body;

  /**
   * <pre>
   * 商品详情
   * detail
   * 公交代扣:A 公交代扣:B 公交代扣:C
   * 商品名称明细列表
   * </pre>
   */
  @Required
  @XStreamAlias("detail")
  private String detail;

  /**
   * <pre>
   * 附加数据
   * attach
   * 附加数据，在查询 API 和支 付通知中原样返回，该字段 主要用亍商户携带订单的自 定义数据
   * </pre>
   */
  @Required
  @XStreamAlias("attach")
  private String attach;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 商户系统内部的订单号,32 个字符内、可包含字母
   * </pre>
   */
  @Required
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 总金额
   * total_fee
   * 订单总金额，单位为分，只能为整数
   * </pre>
   */
  @Required
  @XStreamAlias("total_fee")
  private String totalFee;

  /**
   * <pre>
   * 货币类型
   * fee_type
   * 符合 ISO 4217 标准的三位 字母代码，默讣人民币:CNY
   * </pre>
   */
  @Required
  @XStreamAlias("fee_type")
  private String feeType = "CNY";

  /**
   * <pre>
   * 终端 IP
   * spbill_create_ip
   * 调用微信支付 API 的机器 IP
   * </pre>
   */
  @Required
  @XStreamAlias("spbill_create_ip")
  private String spbillCreateIp;

  /**
   * <pre>
   * 商品标记
   * goods_tag
   * 商品标记，代金券或立减优
   * 惠功能的参数，说明详见代
   * 金券或立减优惠
   * </pre>
   */
  @Required
  @XStreamAlias("goods_tag")
  private String goodsTag;

  /**
   * <pre>
   * 回调通知 url
   * notify_url
   * 接受扣款结果异步回调通知 的 url
   * </pre>
   */
  @Required
  @XStreamAlias("notify_url")
  private String notifyUrl;

  /**
   * <pre>
   * 交易类型
   * trade_type
   * 交易类型 PAP-微信委托代 扣支付
   * </pre>
   */
  @Required
  @XStreamAlias("trade_type")
  private String tradeType = "PAP";

  /**
   * <pre>
   * 交易场景
   * trade_scene
   * 委托代扣的交易场景值，目 前支持 : 1. PARKING:车 场停车场景 ;2. PARKING SPACE 车位停车场景;该值 催缴时会向微信用户迚行展 示
   * </pre>
   */
  @Required
  @XStreamAlias("trade_scene")
  private String trade_scene;

  /**
   * <pre>
   * 场景信息
   * scene_info
   * 场景信息值，格式为 json， 丌同业务场景设置丌同的 值，具体如后面所列。
   * </pre>
   */
  @Required
  @XStreamAlias("scene_info")
  private String scene_info;

  @Override
  protected void checkConstraints() throws WxVehicleException {

  }
}
