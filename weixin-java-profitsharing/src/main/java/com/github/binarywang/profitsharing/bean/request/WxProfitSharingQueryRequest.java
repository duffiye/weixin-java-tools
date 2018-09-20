package com.github.binarywang.profitsharing.bean.request;

import com.github.binarywang.profitsharing.exception.WxProfitSharingException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxProfitSharingQueryRequest extends BaseWxProfitSharingRequest {

  /**
   * <pre>
   * 商户订单号
   * out_order_no
   * 商户系统内部的订单号,32 个字符内、可包含字母
   * </pre>
   */
  @Required
  @XStreamAlias("out_order_no")
  private String outOrderNo;

  /**
   * <pre>
   * 微信订单号
   * transaction_id
   * 微信支付订单号
   * </pre>
   */
  @Required
  @XStreamAlias("transaction_id")
  private String transactionId;

  @Override
  protected void checkConstraints() throws WxProfitSharingException {

  }
}
