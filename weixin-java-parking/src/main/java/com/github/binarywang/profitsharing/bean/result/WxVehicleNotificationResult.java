package com.github.binarywang.profitsharing.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 入场通知响应结果类
 * Created by Binary Wang on 2017-3-23.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxVehicleNotificationResult extends BaseWxVehicleResult {

  /**
   * NORMAL:正常用户， 已开通车主服务，发入 场通知
   * BLOCKED:丌符合免密 规则用户
   * OVERDUE: 用户欠费 状态，提示用户到微信 还款
   */
  @XStreamAlias("userState")
  private String user_state;

  /**
   * 用户在商户 appid 下的 唯一标识，当用户入驻
   * 车主平台时迚行返回
   */
  @XStreamAlias("openid")
  private String openId;

  public String getUser_state() {
    return user_state;
  }

  public WxVehicleNotificationResult setUser_state(String user_state) {
    this.user_state = user_state;
    return this;
  }

  public String getOpenId() {
    return openId;
  }

  public WxVehicleNotificationResult setOpenId(String openId) {
    this.openId = openId;
    return this;
  }
}
