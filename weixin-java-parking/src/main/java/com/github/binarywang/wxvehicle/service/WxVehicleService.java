package com.github.binarywang.wxvehicle.service;

import com.github.binarywang.wxvehicle.bean.WxVehicleApiData;
import com.github.binarywang.wxvehicle.bean.notify.WxVehicleOrderNotifyResult;
import com.github.binarywang.wxvehicle.bean.request.WxVehicleNotificationRequest;
import com.github.binarywang.wxvehicle.bean.request.WxVehiclePayApplyRequest;
import com.github.binarywang.wxvehicle.bean.request.WxVehicleQueryStateRequest;
import com.github.binarywang.wxvehicle.bean.result.WxVehicleNotificationResult;
import com.github.binarywang.wxvehicle.bean.result.WxVehiclePayapplyResult;
import com.github.binarywang.wxvehicle.bean.result.WxVehicleQueryOrderResult;
import com.github.binarywang.wxvehicle.bean.result.WxVehicleQueryStateResult;
import com.github.binarywang.wxvehicle.config.WxVehicleConfig;
import com.github.binarywang.wxvehicle.exception.WxVehicleException;

/**
 * <pre>
 * 微信支付相关接口.
 * Created by Binary Wang on 2016/7/28.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxVehicleService {

  /**
   * 获取微信支付请求url前缀，沙箱环境可能不一样.
   */
  String getVehicleBaseUrl();

  /**
   * 发送post请求，得到响应字节数组.
   *
   * @param url        请求地址
   * @param requestStr 请求信息
   * @param useKey     是否使用证书
   * @return 返回请求结果字节数组
   */
  byte[] postForBytes(String url, String requestStr, boolean useKey) throws WxVehicleException;

  /**
   * 发送post请求，得到响应字符串.
   *
   * @param url        请求地址
   * @param requestStr 请求信息
   * @param useKey     是否使用证书
   * @return 返回请求结果字符串
   */
  String post(String url, String requestStr, boolean useKey) throws WxVehicleException;

  /**
   * 获取配置.
   */
  WxVehicleConfig getConfig();

  /**
   * 设置配置对象.
   */
  void setConfig(WxVehicleConfig config);


  /**
   * <pre>
   * 获取仿真测试系统的验签密钥.
   * 请求Url： https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey
   * 是否需要证书： 否
   * 请求方式： POST
   * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=23_1
   * </pre>
   */
  String getSandboxSignKey() throws WxVehicleException;

  /**
   * 获取微信请求数据，方便接口调用方获取处理.
   */
  WxVehicleApiData getWxApiData();

  /**
   * 微信无感停车车辆进场通知
   * @param wxVehicleNotificationRequest
   * @return
   * @throws WxVehicleException
   */
  WxVehicleNotificationResult notification(WxVehicleNotificationRequest wxVehicleNotificationRequest) throws WxVehicleException;

  /**
   * 微信无感停车申请扣款
   * @param wxVehiclePayApplyRequest
   * @return
   * @throws WxVehicleException
   */
  WxVehiclePayapplyResult payApply(WxVehiclePayApplyRequest wxVehiclePayApplyRequest) throws WxVehicleException;



  /**
   * 解析支付结果通知.
   * 详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
   */
  WxVehicleOrderNotifyResult parseOrderNotifyResult(String xmlData) throws WxVehicleException;

  /**
   * <pre>
   * 查询订单.
   * 详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2
   * 该接口提供所有微信支付订单的查询，商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
   * 需要调用查询接口的情况：
   * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
   * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
   * ◆ 调用被扫支付API，返回USERPAYING的状态；
   * ◆ 调用关单或撤销接口API之前，需确认支付状态；
   * 接口地址：https://api.mch.weixin.qq.com/pay/orderquery
   * </pre>
   *
   * @param transactionId 微信订单号
   * @param outTradeNo    商户系统内部的订单号，当没提供transactionId时需要传这个。
   */
  WxVehicleQueryOrderResult queryOrder(String transactionId, String outTradeNo) throws WxVehicleException;

  /**
   * 用户状态查询
   * @param wxVehicleQueryStateRequest
   * @return
   * @throws WxVehicleException
   */
  WxVehicleQueryStateResult queryState(WxVehicleQueryStateRequest wxVehicleQueryStateRequest) throws WxVehicleException;
}
