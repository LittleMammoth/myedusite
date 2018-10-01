/**
 * 
 */
package com.inxedu.os.edu.controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.inxedu.os.common.cache.EHCacheUtil;
import com.inxedu.os.common.constants.AlipayConfig;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.constants.Constants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.user.UserService;

/**
 * @author 王武
 * @website:www.lemonkt.com
 * @QQ:834667820
 */
@Controller
@RequestMapping("/front")
public class AlipayController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(AlipayController.class);

    // 登录
    private static final String toPay = getViewPath("/web/member/alipay");
    //未登录
    private static final String notUserToPay = getViewPath("/web/member/not-user-alipay");
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
    //not-user-alipay
    @RequestMapping("/member")
	public ModelAndView goToMemberPage(HttpServletRequest request){
		logger.info("进入会员支付页面");
		 ModelAndView model = new ModelAndView();
			Map<String,Object> json = new HashMap<String,Object>();
			try{
			String prefix = WebUtils.getCookie(request, CacheConstans.WEB_USER_LOGIN_PREFIX);
			Object object = EHCacheUtil.get(prefix);
			if(object!=null){
				 model.setViewName(toPay);
				}else{
					model.setViewName(notUserToPay);
				}
			}catch (Exception e) {
				logger.error("outLogin()--error",e);
			}
		
		return model;
	}
    @RequestMapping("/locaPay")
    public void gotoPayPage(HttpServletResponse response,HttpServletRequest request) throws IOException {
        // 订单模型
		PrintWriter writer = response.getWriter();
		// 6.对接支付代码 返回提交支付from表单元素给客户端
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);

		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = UUID.randomUUID().toString().replaceAll("-", "");
		// 付款金额，必填 企业金额
		String total_amount = 10 + "";
		// 订单名称，必填
		String subject = "柠檬课堂会员";
		// 商品描述，可空
		// String body = new
		// String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\","
				// + "\"body\":\""+ body +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		try {
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			int userId = SingletonLoginUtils.getLoginUserId(request);
			
			Order order = new Order();
			order.setOrderId(out_trade_no);
			order.setGoodsName(subject);
			order.setGoodsPrice(total_amount);
			order.setPaymentType("1");//支付宝
			order.setPayStatus("0");//0未支付
			order.setUserId(String.valueOf(userId));
			order.setCreateTime(new Date());
			order.setUpdateTime(new Date());
			orderService.createOrder(order);
	
			writer.write(result);
		} catch (Exception e) {
			logger.error("支付失败");
		}
		
		writer.flush();
		writer.close();
    }
	// 同步回调
	@RequestMapping("/synCallBack")
	public void synCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
		response.setContentType("text/html;charset=utf-8");
		System.out.println("同步回调");
		//获取支付宝GET过来反馈信息
		Map<String, String[]> requestParams = request.getParameterMap();
		Map<String, String> params = new HashMap<String, String>();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
		PrintWriter writer = response.getWriter();
		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			//商户订单号
			String outTradeNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//支付宝交易号
			String tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//付款金额
			String totalAmount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			
			String htmlFrom = "<form name='punchout_form'"
					+ " method='post' action='http://127.0.0.1/front/synSuccessPage' >"
					+ "<input type='hidden' name='outTradeNo' value='" + outTradeNo + "'>"
					+ "<input type='hidden' name='tradeNo' value='" + tradeNo  + "'>"
					+ "<input type='hidden' name='totalAmount' value='" + totalAmount + "'>"
					+ "<input type='submit' value='立即支付' style='display:none'>"
					+ "</form><script>document.forms[0].submit();" + "</script>";
			writer.println(htmlFrom);
		}else {
			writer.println("验签失败");
		}
		//——请在这里编写您的程序（以上代码仅作参考）——

	}
	@RequestMapping(value = "/synSuccessPage",method = RequestMethod.POST)
	public ModelAndView paySuccessPage(HttpServletRequest request,String outTradeNo,String tradeNo,String totalAmount) throws IOException, AlipayApiException {
		request.setAttribute("outTradeNo", outTradeNo);
		request.setAttribute("tradeNo", tradeNo);
		request.setAttribute("totalAmount", totalAmount);
        ModelAndView modelAndView = new ModelAndView(getViewPath("/web/member/pay_successPage"));// 支付成功页面
        return modelAndView;
		
	}
	// 异步回调
	@RequestMapping("/asynCallBack")
	@ResponseBody
	public String asynCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
		response.setContentType("text/html;charset=utf-8");
		System.out.println("异步回调");
		Map<String, String[]> requestParams = request.getParameterMap();
		Map<String, String> params = new HashMap<String, String>();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		logger.info("###支付宝异步通知asynCallBack###开始,params:{}",params);
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
		if (!signVerified) {
			return Constants.PAY_FAIL;
		}
		//商户订单号
		String outTradeNo = params.get("out_trade_no");
		//支付宝交易号
		String tradeNo = params.get("trade_no");
		//付款金额
		String totalmount = params.get("total_amount");
		//去订单表查询订单状态通过商户订单号
		int userId = orderService.selectUserIdByOrderId(outTradeNo);
		//修改用户会员状态
		userService.updateUserMemberState(userId);
		//修改订单状态
		
		return "success";
	}
}
