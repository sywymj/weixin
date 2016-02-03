package com.cnhis.controller;

import java.util.HashMap;
import java.util.Map;


import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.PaymentApi;
import com.jfinal.weixin.sdk.api.PaymentApi.TradeType;
import com.jfinal.weixin.sdk.kit.IpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * ��л *�뱭* ͯЬ����֧��API
 * @author L.cm
 */
public class WeixinPayController extends Controller {
	//�̻��������
	private static String appid = "";
	private static String partner = "";
	private static String paternerKey = "";
	private static String notify_url = "http://www.xxx.com/pay/pay_notify";
	
	public void index() {
		// openId������ ��ҳ��Ȩ��ȡ access_token API��SnsAccessTokenApi��ȡ
		String openId = "";

		// ͳһ�µ��ĵ���ַ��https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", appid);
		params.put("mch_id", partner);
		params.put("body", "JFinal2.0���ٿ���");
		params.put("out_trade_no", "977773682111");
		params.put("total_fee", "1");
		
		String ip = IpKit.getRealIp(getRequest());
		if (StrKit.isBlank(ip)) {
			ip = "127.0.0.1";
		}
		
		params.put("spbill_create_ip", ip);
		params.put("trade_type", TradeType.JSAPI.name());
		params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
		params.put("notify_url", notify_url);
		params.put("openid", openId);

		String sign = PaymentKit.createSign(params, paternerKey);
		params.put("sign", sign);
		String xmlResult = PaymentApi.pushOrder(params);
		
		System.out.println(xmlResult);
		Map<String, String> result = PaymentKit.xmlToMap(xmlResult);
		
		String return_code = result.get("return_code");
		String return_msg = result.get("return_msg");
		if (StrKit.isBlank(return_code) || !"SUCCESS".equals(return_code)) {
			renderText(return_msg);
			return;
		}
		String result_code = result.get("result_code");
		if (StrKit.isBlank(result_code) || !"SUCCESS".equals(result_code)) {
			renderText(return_msg);
			return;
		}
		// �����ֶ���return_code ��result_code��ΪSUCCESS��ʱ���з���
		String prepay_id = result.get("prepay_id");
		
		Map<String, String> packageParams = new HashMap<String, String>();
		packageParams.put("appId", appid);
		packageParams.put("timeStamp", System.currentTimeMillis() / 1000 + "");
		packageParams.put("nonceStr", System.currentTimeMillis() + "");
		packageParams.put("package", "prepay_id=" + prepay_id);
		packageParams.put("signType", "MD5");
		String packageSign = PaymentKit.createSign(packageParams, paternerKey);
		packageParams.put("paySign", packageSign);
		
		String jsonStr = JsonUtils.toJson(packageParams);
		setAttr("json", jsonStr);
		System.out.println(jsonStr);
		render("/jsp/pay.jsp");
	}
	
	public void pay_notify() {
		// ֧�����ͨ��֪ͨ�ĵ�: https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
		String xmlMsg = HttpKit.readData(getRequest());
		System.out.println("֧��֪ͨ="+xmlMsg);
		Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);
		
		String result_code  = params.get("result_code");
		// �ܽ��
		String totalFee     = params.get("total_fee");
		// �̻�������
		String orderId      = params.get("out_trade_no");
		// ΢��֧��������
		String transId      = params.get("transaction_id");
		// ֧�����ʱ�䣬��ʽΪyyyyMMddHHmmss
		String timeEnd      = params.get("time_end");
		
		// ע���ظ�֪ͨ�������ͬһ�����ſ����յ����֪ͨ����ע��һ�����ж϶���״̬
		// �����Ѿ��ɹ����رա��˿�Ķ������ٴθ���
		
		if(PaymentKit.verifyNotify(params, paternerKey)){
			if (("SUCCESS").equals(result_code)) {
				//���¶�����Ϣ
				System.out.println("���¶�����Ϣ");
				
				Map<String, String> xml = new HashMap<String, String>();
				xml.put("return_code", "SUCCESS");
				xml.put("return_msg", "OK");
				renderText(PaymentKit.toXml(xml));
				return;
			}
		}
		renderText("");
	}
}
