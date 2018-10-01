/**
 * 
 */
package com.inxedu.os.edu.entity.order;

import java.util.Date;

import lombok.Data;

/**
 * @author 王武
 * @website:www.lemonkt.com
 * @QQ:834667820
 */
@Data
public class Order {
	private String orderId;//订单号
	private String userId;//用户id
	private String goodsName;//商品名称
	private String goodsPrice;//商品价格
	private String payStatus;//支付状态0待付款 1已付款
	private String paymentType;//支付方式:1.支付宝
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	

}
