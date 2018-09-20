package com.github.binarywang.profitsharing.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxProfitSharingRemoveReceiverResult extends BaseWxProfitSharingResult {

  @XStreamAlias("receiver")
  private String receiver;

}

