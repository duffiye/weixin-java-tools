package com.github.binarywang.wxvehicle.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxVehicleQueryStateResult extends BaseWxVehicleResult {

  /**
   * <pre>
   * 用户状态
   * user_state
   * NORMAL：正常用户，已开通车主服务，且已授权访问

   PAUSED：已暂停车主服务

   OVERDUE: 用户已开通车主服务，但欠费状态。提示用户还款，请跳转到车主服务

   UNAUTHORIZED：用户未授权使用当前业务，或未开通车主服务。请跳转到授权接口
   * </pre>
   */
  @XStreamAlias("user_state")
  private String userState;

  /**
   * <pre>
   * 用户标识
   * openid
   * 用户在商户appid下的唯一标识，当用户入驻车主平台时进行返回
   * </pre>
   */
  @XStreamAlias("openid")
  private String openid;

  /**
   * <pre>
   * 用户子标识
   * sub_openid
   * sub_appid下，用户的唯一标识
   * </pre>
   */
  @XStreamAlias("sub_openid")
  private String subOpenid;

  /**
   * <pre>
   * 跳转路径
   * path
   * 跳转车主小程序的页面路径
   * </pre>
   */
  @XStreamAlias("path")
  private String path;

  /**
   * <pre>
   * 车牌号
   * plate_number_list
   * 车牌号列表。仅包括省份+车牌，不包括特殊字符。多个车牌时，以分号间隔。
   注：在PARKING SPACE场景下无返回
   * </pre>
   */
  @XStreamAlias("plate_number_list")
  private String plateNumberList;
}
