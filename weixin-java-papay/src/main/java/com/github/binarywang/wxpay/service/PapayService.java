package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.papay.*;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 *  服务商委托代扣.
 *  Created by BinaryWang on 2017/12/19.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface PapayService {

  /**
   * 获取配置.
   */
  WxPayConfig getConfig();

  /**
   * 设置配置对象.
   */
  void setConfig(WxPayConfig config);


  /**
   * 获取微信支付请求url前缀，沙箱环境可能不一样.
   */
  String getPayBaseUrl();

  /**
   * 发送post请求，得到响应字节数组.
   *
   * @param url        请求地址
   * @param requestStr 请求信息
   * @param useKey     是否使用证书
   * @return 返回请求结果字节数组
   */
  byte[] postForBytes(String url, String requestStr, boolean useKey) throws WxPayException;

  /**
   * 发送post请求，得到响应字符串.
   *
   * @param url        请求地址
   * @param requestStr 请求信息
   * @param useKey     是否使用证书
   * @return 返回请求结果字符串
   */
  String post(String url, String requestStr, boolean useKey) throws WxPayException;

  /**
   * <pre>
   * 申请扣款API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/pap_sl.php?chapter=18_18&index=6
   * 接口链接：https://pay.weixin.qq.com/wiki/doc/api/pap_sl.php?chapter=18_18&index=6
   * </pre>
   *
   * @param request 请求对象
   */
  PapayApplyResult apply(PapayApplyRequest request) throws WxPayException;


}
