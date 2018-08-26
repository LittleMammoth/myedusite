/**
 * 
 */
package com.inxedu.os.edu.controller.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.inxedu.os.common.cache.EHCacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.WebUtils;

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
    //not-user-alipay
    @RequestMapping("/member")
	public ModelAndView goToPayPage(HttpServletRequest request){
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

}
