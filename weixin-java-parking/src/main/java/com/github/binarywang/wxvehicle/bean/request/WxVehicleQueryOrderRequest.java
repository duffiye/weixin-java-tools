package com.github.binarywang.wxvehicle.bean.request;

import com.github.binarywang.wxvehicle.exception.WxVehicleException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxVehicleQueryOrderRequest extends BaseWxVehicleRequest {

  /**
   * <pre>
   * 微信订单号
   * transaction_id
   * 微信的订单号，优先使 用
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 商户系统内部的订单
   * 号，当没提供transaction_id 时需要 传这个。
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;



  @Override
  protected void checkConstraints() throws WxVehicleException {

  }
}
