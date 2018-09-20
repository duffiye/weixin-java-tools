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
public class WxProfitSharingAddReceiverRequest extends BaseWxProfitSharingRequest  {

  /**
   * <pre>
   * 分账接收方
   * receiver
   * 分账接收方对象(不包含分账接收方全称)， json 格式
   * </pre>
   */
  @Required
  @XStreamAlias("receiver")
  private String receiver;


  @Override
  protected void checkConstraints() throws WxProfitSharingException {

  }
}
