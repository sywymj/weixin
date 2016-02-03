package com.cnhis.util;
import java.util.Map;
import com.jfinal.weixin.sdk.kit.ParaMap;

/**	通过Http请求，同步HO中的数据。
 * @author wa
 *
 */
public class HOApi{
	
	/**用户资料登记 WX_UserReg
	 * @param openid	微信 OpenID
	 * @param name		姓名
	 * @param tel		手机号
	 * @param sex		性别 1 男 2 女 0 未知
	 * @param age		年龄
	 * @param idno		身份证号码 15/18
	 * @param type		0 自己 1 联系人
	 * @param sca01		编辑时需要提供，默认 0

	 * @return 直接返回请求结果，不做处理
	 */
	public static String WX_UserReg(String openid,String name,String tel,int sex,int age,String idno,int type,int sca01){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/WX_UserReg.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("openid",openid).put("name",name)
				.put("tel",tel).put("sex",String.valueOf(sex)).put("age",String.valueOf(age))
				.put("idno",idno).put("type",String.valueOf(type)).put("sca01",String.valueOf(sca01)).getData();
		String rtn = HttpKit.get(url, queryParas);
		return rtn;
	}
	
	/** 预约登记 WX_OrderReg		
	 * @param openid	微信 OpenID
	 * @param sca01		CRM 客人 ID
	 * @param hodepid	HIS 科室 ID
	 * @param hodocid	HIS 医生 ID
	 * @param type		预约类型 0 天 1 上午 下午 2 精确到时间
	 * @param ampm		0 上午 1 下午
	 * @param begintime	预约时间（起）日期格式 yyyy-MM-dd HH:mm:ss
	 * @param endtime	预约时间（止）日期格式 yyyy-MM-dd HH:mm:ss
	 * @param remark	预约说明/备注
	 * @return			直接返回请求结果，不做处理
	 */
	public static String WX_OrderReg(String openid,int sca01,int hodepid,int hodocid,int type,int ampm,String begintime,String endtime,String remark){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/WX_OrderReg.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("openid",openid).put("sca01",String.valueOf(sca01)).put("hodepid",String.valueOf(hodepid))
				.put("hodocid",String.valueOf(hodocid)).put("type",String.valueOf(type)).put("ampm", String.valueOf(ampm)).put("begintime", begintime)
				.put("endtime", endtime).put("remark", remark).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/**	挂号登记 WX_Reg
	 * @param openid		微信 OpenID
	 * @param sca01			CRM 客人 ID
	 * @param hodepid		HIS 科室 ID
	 * @param hodocid		HIS 医生 ID
	 * @param horegtype		Ho 挂号类型 ID
	 * @param regdate		挂号日期
	 * @param remark		备注
	 * @return		直接返回请求结果，不做处理
	 */
	public static String WX_Reg(String openid,int sca01,int hodepid,int hodocid,int horegtype,String regdate,String remark){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/WX_Reg.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("openid",openid).put("sca01",String.valueOf(sca01)).put("hodepid",String.valueOf(hodepid))
				.put("hodocid",String.valueOf(hodocid)).put("horegtype",String.valueOf(horegtype))
				.put("regdate", regdate).put("remark", remark).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/**	挂号支付检测 WX_RegPayCheck		点击微信支付按钮，调起微信支付之前 需要进行支付检测
	 * @param vac01		挂号 ID
	 * @param amount	支付金额格式：0.00
	 * @return		直接返回请求结果，不做处理
	 */
	public static String WX_RegPayCheck(int vac01,double amount){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/WX_RegPayCheck.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("vac01",String.valueOf(vac01)).put("amount",String.valueOf(amount)).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/** 挂号支付 WX_RegPay
	 * @param vac01		挂号 ID
	 * @param payno		微信支付单号
	 * @param payamount	支付金额 格式 0.00
	 * @param paytime	付款时间 格 式 yyyy-mm-dd hh:nn:ss
	 * @param nonstr	随机数
	 * @param sign		Md5 签名字符串
	 * @return		 直接返回请求结果，不做处理
	 */
	public static String WX_RegPay(int vac01,String payno,double payamount,String paytime,String nonstr,String sign){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/WX_RegPay.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("vac01",String.valueOf(vac01)).put("payno",payno).put("payamount",String.valueOf(payamount))
				.put("paytime",paytime).put("nonstr",nonstr).put("sign",sign).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	/**	微信会员充值 WX_MemberRecharge
	 * @param cardno		会员卡号
	 * @param amount		充值金额	 0.00
	 * @param billno		充值单号
	 * @param wxbillno		微信官方支付单号
	 * @param wxpaytime		微信官方支付时间
	 * @param wxopenid		用户 OpenID
	 * @param nonstr		随机字符串
	 * @param sign			签名字符串
	 * @return
	 */
	public static String WX_MemberRecharge(String cardno,double amount,String billno,String wxbillno,String wxpaytime,String wxopenid,String nonstr,String sign){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/WX_MemberRecharge.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("cardno",cardno).put("amount",String.valueOf(amount)).put("billno",billno).put("wxbillno", wxbillno)
				.put("wxpaytime",wxpaytime).put("wxopenid",wxopenid).put("nonstr",nonstr).put("sign",sign).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/** 微信在线支付检测 WX_OnlinePayCheck		点击支付按钮，调起微信支付前，需要执行检测
	 * @param amount	支付金额
	 * @param paylist	结账单 ID ；VAI01 	格式：1,2,3
	 * @return
	 */
	public static String WX_OnlinePayCheck(double amount,String paylist){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/WX_OnlinePayCheck.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("amount",String.valueOf(amount)).put("paylist",paylist).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/**	微信在线支付 WX_OnlinePay
	 * @param openid		微信OpenID
	 * @param billno		支付单号
	 * @param amount		支付金额
	 * @param paylist		结账单 ID;VAI01 格式：1,2,3
	 * @param wxbillno		微信支付单号
	 * @param wxpaytime		微信付款时间 yyyy-mm-dd hh:nn:ss
	 * @param nonstr		随机字符串
	 * @param sign			签名字符串
	 * @return
	 */
	public static String WX_OnlinePay(String openid,String billno,double amount,String paylist,String wxbillno,String wxpaytime,String nonstr,String sign){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/WX_OnlinePay.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("openid",openid).put("billno",billno).put("amount",String.valueOf(amount)).put("paylist",paylist)
				.put("wxbillno", wxbillno).put("wxpaytime", wxpaytime).put("nonstr", nonstr).put("sign", sign).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/**	参数配置 WX_SysConfigEdit	用于实现和HO同步
	 * @param key		
	 * @param value
	 * @param nonstr	随机字符串
	 * @param sign		签名
	 * @return
	 */
	public static String WX_SysConfigEdit(String key,String value,String nonstr,String sign){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/WX_SysConfigEdit.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("key",key).put("value",value).put("nonstr",nonstr).put("sign",sign).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/**卡号绑定 HO_CardBinding
	 * @param cardno	卡号
	 * @param tel		手机号
	 * @param id		身份证号
	 * @param type		类型 0 会员卡；1 门诊号；2 住院号；3体检登记号
	 * @return		直接返回请求结果，不做处理
	 */
	public static String HO_CardBinding(String cardno,String tel,String id,String type){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/HO_CardBinding.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("cardno", cardno).put("tel", tel)
				.put("id", id).put("type", type).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/**	会员帐户查询 HO_MemberAccountQuery
	 * @param cardno	会员卡号
	 * @return		直接返回请求结果，不做处理
	 */
	public static String HO_MemberAccountQuery(String cardno){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/HO_MemberAccountQuery.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("cardno", cardno).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/**	待付账单 HO_GetBillList		优先顺序：vaa01 > sca01 > cardno
	 *  三个参数都需要传值， 不需要使用的 int = 0, stirng = ‘’
	 * @param vaa01		关联病人 ID
	 * @param sca01		关联客户 ID
	 * @param cardno	会员卡号
	 * @return		直接返回请求结果，不做处理
	 */
	public static String HO_GetBillList(int vaa01,int sca01,String cardno){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/HO_GetBillList.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("cardno",cardno).put("vaa01",String.valueOf(vaa01))
				.put("sca01",String.valueOf(sca01)).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/**	会员充值记录 HO_MemberRechargeRecord
	 * @param cardno	会员卡号
	 * @return		直接返回请求结果，不做处理
	 */
	public static String HO_MemberRechargeRecord(String cardno){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/HO_MemberRechargeRecord.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("cardno", cardno).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/**	会员消费/交易记录 HO_MemberTradeRecord
	 * @param cardno	会员卡号
	 * @return		直接返回请求结果，不做处理
	 */
	public static String HO_MemberTradeRecord(String cardno){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/HO_MemberTradeRecord.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("cardno", cardno).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/**	检验报告 HO_LisReport		优先顺序：vaa01 > sca01 > cardno		
	 * 	三个参数都需要传值， 不需要使用的 int = 0, stirng = ‘’
	 * @param vaa01		关联病人 ID
	 * @param sca01		关联客户 ID
	 * @param cardno	会员卡号
	 * @return		直接返回请求结果，不做处理
	 */
	public static String HO_LisReport(int vaa01,int sca01,String cardno){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/HO_LisReport.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("cardno",cardno).put("vaa01",String.valueOf(vaa01))
				.put("sca01",String.valueOf(sca01)).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/**	体检报告 HO_PESReport		优先顺序：vaa01 > sca01 > cardno		
	 * 	三个参数都需要传值， 不需要使用的 int = 0, stirng = ‘’
	 * @param vaa01		关联病人 ID
	 * @param sca01		关联客户 ID
	 * @param cardno	会员卡号
	 * @return		直接返回请求结果，不做处理
	 */
	public static String HO_PESReport(int vaa01,int sca01,String cardno){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/HO_PESReport.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("cardno",cardno).put("vaa01",String.valueOf(vaa01))
				.put("sca01",String.valueOf(sca01)).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/**	挂号科室 HO_RegDeparts
	 * @return		直接返回请求结果，不做处理
	 */
	public static String HO_RegDeparts(){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/HO_RegDeparts.hoscript";
		String rtn = HttpKit.get(url);
		return rtn;
	}
	
	/**	挂号医生 HO_RegDoctors
	 * @param bck01		Ho 科室 ID
	 * @param isduty	是否要求有排班记录 
	 * 	如果需要实施获取当前科室有排班记录的医生，设置参数值 = 1，否则设置 = 0
	 * @return		直接返回请求结果，不做处理
	 */
	public static String HO_RegDoctors(int bck01,int isduty){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/HO_RegDoctors.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("bck01",String.valueOf(bck01)).put("isduty",String.valueOf(isduty)).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/**	医生实时值班挂号情况 HO_RegDutyList
	 * @param bck01		Ho 科室 ID
	 * @param bce01		Ho 医生 ID
	 * @param days		检索天数，默认 7 天
	 * 	如果要获取当前科室所选医生在 HO 的排班和挂号情况，才使用本接口，通常默认 7 天
	 * @return
	 */
	public static String HO_RegDutyList(int bck01,int bce01,int days){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/HO_RegDutyList.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("bck01",String.valueOf(bck01)).put("days",String.valueOf(days)).put("bce01",String.valueOf(bce01)).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	/**	获取系统病人 ID HO_GetVAA01
	 * @param type		0 会员 1 门诊 2 住院 3 体检登记号 4 客户 ID
	 * @param cardno	会员卡号
	 * @return
	 */
	public static String HO_GetVAA01(int type,String cardno){
		String url = "http://"+"121.199.21.95"+":8888"+"/wxcrm/HO_GetVAA01.hoscript";
		Map<String,String> queryParas = ParaMap.create().put("type",String.valueOf(type)).put("cardno",cardno).getData();
		String rtn = HttpKit.get(url,queryParas);
		return rtn;
	}
	
	public static void main(String[] args){
		String rtn = null;
		//rtn = HOApi.WX_UserReg("o6lXEtwZMjoW2PIey_abtKM0psXg","王安","13048883544",1,23,"362527199204082215",0,0);
		//rtn = WX_OrderReg("o6lXEtwZMjoW2PIey_abtKM0psXg",53635,140,467,1,0,"2016-02-02 09:10:00","","你好");		//没测通
		//rtn = HOApi.WX_Reg("o6lXEtwZMjoW2PIey_abtKM0psXg",53635,140,467,0,"2016-02-02 09:10:00","hello");
		//rtn = HOApi.WX_RegPayCheck(123163,7);
		
		//挂号支付 WX_RegPay
		/*String nonstr = System.currentTimeMillis()+"";
		Map<String,String> params = ParaMap.create().put("vac01","123162").put("payno","00002016020211624").put("payamount","7.00")
		.put("paytime","2016-02-02 16:24:12").getData();
		String sign = PaymentKit.createSign(params,nonstr+"www.cnhis.com");
		rtn = HOApi.WX_RegPay(123162,"00002016020211624",7.00,"2016-02-02 16:24:12",nonstr,sign);*/
		
		//微信支付检测
		//rtn = HOApi.WX_OnlinePayCheck(7,"152");
		//微信支付
		/*String nonstr = System.currentTimeMillis()+"";
		Map<String,String> params = ParaMap.create().put("openid","o6lXEtwZMjoW2PIey_abtKM0psXg").put("billno","00002016020211624").put("amount","7.00")
				.put("paylist","152").put("wxbillno","00002016020211624").put("wxpaytime","2016-02-02 16:24:12").getData();
		String sign = HOSignKit.createSign(params,nonstr+"www.cnhis.com");
		rtn = HOApi.WX_OnlinePay("o6lXEtwZMjoW2PIey_abtKM0psXg","00002016020211624",7.00,"152","00002016020211624","2016-02-02 16:24:12", nonstr, sign);*/
		
		//参数配置 WX_SysConfigEdit
		/*String nonstr = System.currentTimeMillis()+"";
		Map<String,String> params = ParaMap.create().put("key","BillNoLength").put("value","15").getData();
		String sign = HOSignKit.createSign(params,nonstr+"www.cnhis.com");
		rtn = HOApi.WX_SysConfigEdit("BillNoLength","15",nonstr,sign);*/
		
		//微信会员充值 
		/*String nonstr = System.currentTimeMillis()+"";
		Map<String,String> params = ParaMap.create().put("wxopenid","o6lXEtwZMjoW2PIey_abtKM0psXg").put("billno","00002016020211624").put("amount","7.00")
				.put("wxbillno","00002016020211624").put("cardno","t01").put("wxpaytime","2016-02-02 16:24:12").getData();
		String sign = HOSignKit.createSign(params,nonstr+"www.cnhis.com");
		rtn = HOApi.WX_MemberRecharge("t01",7.00,"00002016020211624","00002016020211624","2016-02-02 16:24:12","o6lXEtwZMjoW2PIey_abtKM0psXg", nonstr, sign);*/
		
		//rtn = HOApi.HO_CardBinding("t01","123456","","0");
		//rtn = HOApi.HO_MemberAccountQuery("t01");
		//rtn = HOApi.HO_GetBillList(0,53636,"t01");
		//rtn = HOApi.HO_MemberRechargeRecord("t01");
		//rtn = HOApi.HO_MemberTradeRecord("t01");
		//rtn = HOApi.HO_LisReport(0,0,"t01");
		//rtn = HOApi.HO_PESReport(213,0,"");
//		rtn = HOApi.HO_RegDeparts();
//		System.out.println(rtn);
//		rtn = HOApi.HO_RegDoctors(140,0);
//		System.out.println(rtn);
//		rtn = HOApi.HO_RegDutyList(140,467,7);
//		System.out.println(rtn);
//		rtn = HOApi.HO_GetVAA01(0,"t01");
		System.out.println(rtn);
	}
	
}
