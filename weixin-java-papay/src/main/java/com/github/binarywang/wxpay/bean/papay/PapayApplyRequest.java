package com.github.binarywang.wxpay.bean.papay;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;

/**
 * <pre>
 * 服务商委托代扣
 * Created by Binary Wang on 2017/12/21.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class PapayApplyRequest extends BaseWxPayRequest {

  /**
   * <pre>
   *     商品描述
   *     body
   *     是
   *     String(32)
   *     商品或支付单简要描述
   * </pre>
   */
  @XStreamAlias("body")
  private String body;

  @XStreamAlias("detail")
  private String detail;

  @XStreamAlias("attach")
  private String attach;

  @XStreamAlias("out_trade_no")
  private String out_trade_no;

  @XStreamAlias("total_fee")
  private String total_fee;

  @XStreamAlias("fee_type")
  private String fee_type;

  @XStreamAlias("spbill_create_ip")
  private String spbill_create_ip;

  @XStreamAlias("goods_tag")
  private String goods_tag;

  @XStreamAlias("notify_url")
  private String notify_url;

  @XStreamAlias("trade_type")
  private String trade_type;

  @XStreamAlias("contract_id")
  private String contract_id;


  @Override
  protected void checkConstraints() throws WxPayException {

  }
}
