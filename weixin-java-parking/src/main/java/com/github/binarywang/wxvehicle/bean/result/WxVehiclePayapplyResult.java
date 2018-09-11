package com.github.binarywang.wxvehicle.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 申请扣款响应结果类
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxVehiclePayapplyResult extends BaseWxVehicleResult {

  /**
   * <pre>
   * 公众账号 id
   * appid
   * 微信支付分配的公众账 号 id
   * </pre>
   */
  @XStreamAlias("appid")
  private String appId;

  /**
   * <pre>
   * 商户号
   * mch_id
   * 微信支付分配的商户号
   * </pre>
   */
  @XStreamAlias("mch_id")
  private String mchId;

  /**
   * <pre>
   * 设备号
   * device_info
   * 终端设备号
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
   * 随机字符 串
   * nonce_str
   * 随机字符串，丌长亍 32 位。
   * </pre>
   */
  @XStreamAlias("nonce_str")
  private String nonce_str;

  /**
   * <pre>
   * 签名
   * sign
   * 签名，详见签名生成算 法
   * </pre>
   */
  @XStreamAlias("sign")
  private String sign;

  /**
   * <pre>
   * 业务结果
   * result_code
   * SUCCESS/FAIL
   * </pre>
   */
  @XStreamAlias("result_code")
  private String resultCode;

  /**
   * <pre>
   * 错误代码
   * err_code
   * 错误码
   * </pre>
   */
  @XStreamAlias("err_code")
  private String errCode;

  /**
   * <pre>
   * 错误代码 描述
   * err_code_des
   * 错误码描述
   * </pre>
   */
  @XStreamAlias("err_code_des")
  private String errCodeDes;
}
