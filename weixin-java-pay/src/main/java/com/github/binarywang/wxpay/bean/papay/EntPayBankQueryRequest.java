package com.github.binarywang.wxpay.bean.papay;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;

/**
 * <pre>
 * 企业付款到银行卡的请求对象
 * Created by Binary Wang on 2017/12/21.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class EntPayBankQueryRequest extends EntPayQueryRequest {
  private static final long serialVersionUID = -479088843124447119L;

  @Override
  protected boolean ignoreAppId() {
    return true;
  }
}
