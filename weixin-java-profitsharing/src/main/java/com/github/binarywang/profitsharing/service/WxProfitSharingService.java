package com.github.binarywang.profitsharing.service;

import com.github.binarywang.profitsharing.bean.WxProfitSharingApiData;
import com.github.binarywang.profitsharing.bean.request.WxProfitSharingAddReceiverRequest;
import com.github.binarywang.profitsharing.bean.request.WxProfitSharingQueryRequest;
import com.github.binarywang.profitsharing.bean.request.WxProfitSharingRemoveReceiverRequest;
import com.github.binarywang.profitsharing.bean.request.WxProfitSharingRequest;
import com.github.binarywang.profitsharing.bean.result.WxProfitSharingAddReceiverResult;
import com.github.binarywang.profitsharing.bean.result.WxProfitSharingQueryResult;
import com.github.binarywang.profitsharing.bean.result.WxProfitSharingRemoveReceiverResult;
import com.github.binarywang.profitsharing.exception.WxProfitSharingException;
import com.github.binarywang.profitsharing.bean.result.WxProfitSharingResult;
import com.github.binarywang.profitsharing.config.WxProfitSharingConfig;

/**
 * <pre>
 * 微信支付相关接口.
 * Created by Binary Wang on 2016/7/28.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxProfitSharingService {

  /**
   * 获取微信支付请求url前缀，沙箱环境可能不一样.
   */
  String getProfitSharingBaseUrl();

  /**
   * 发送post请求，得到响应字节数组.
   *
   * @param url        请求地址
   * @param requestStr 请求信息
   * @param useKey     是否使用证书
   * @return 返回请求结果字节数组
   */
  byte[] postForBytes(String url, String requestStr, boolean useKey) throws WxProfitSharingException;

  /**
   * 发送post请求，得到响应字符串.
   *
   * @param url        请求地址
   * @param requestStr 请求信息
   * @param useKey     是否使用证书
   * @return 返回请求结果字符串
   */
  String post(String url, String requestStr, boolean useKey) throws WxProfitSharingException;

  /**
   * 获取配置.
   */
  WxProfitSharingConfig getConfig();

  /**
   * 设置配置对象.
   */
  void setConfig(WxProfitSharingConfig config);


  /**
   * <pre>
   * 获取仿真测试系统的验签密钥.
   * 请求Url： https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey
   * 是否需要证书： 否
   * 请求方式： POST
   * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=23_1
   * </pre>
   */
  String getSandboxSignKey() throws WxProfitSharingException;

  /**
   * 获取微信请求数据，方便接口调用方获取处理.
   */
  WxProfitSharingApiData getWxApiData();

  /**
   * 微信分账-申请分账
   * @param wxProfitSharingRequest
   * @return
   * @throws WxProfitSharingException
   */
  WxProfitSharingResult profitSharing(WxProfitSharingRequest wxProfitSharingRequest) throws WxProfitSharingException;

  /**
   * 微信分账-查询
   * @param wxProfitSharingQueryRequest
   * @return
   * @throws WxProfitSharingException
   */
  WxProfitSharingQueryResult query(WxProfitSharingQueryRequest wxProfitSharingQueryRequest) throws WxProfitSharingException;

  /**
   * 微信分账-添加分账接受方
   * @param wxProfitSharingAddReceiverRequest
   * @return
   * @throws WxProfitSharingException
   */
  WxProfitSharingAddReceiverResult add(WxProfitSharingAddReceiverRequest wxProfitSharingAddReceiverRequest) throws WxProfitSharingException;

  /**
   * 微信分账-删除微信分账方
   * @param wxProfitSharingRemoveReceiverRequest
   * @return
   * @throws WxProfitSharingException
   */
  WxProfitSharingRemoveReceiverResult remove(WxProfitSharingRemoveReceiverRequest wxProfitSharingRemoveReceiverRequest) throws WxProfitSharingException;

}
