package com.github.binarywang.profitsharing.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;


@Data
public class WxProfitSharingQueryResult extends BaseWxProfitSharingResult {

  @XStreamAlias("transaction_id")
  private String transaction_id;

  @XStreamAlias("out_order_no")
  private String outOrderNo;

  @XStreamAlias("status")
  private String status;

  @XStreamAlias("close_reason")
  private String closeReason;

  @XStreamAlias("receivers")
  private String receivers;



}
