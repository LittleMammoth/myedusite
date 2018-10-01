/**
 * 
 */
package com.inxedu.os.edu.service.order;

import com.inxedu.os.edu.entity.order.Order;

/**
 * @author 王武
 * @website:www.lemonkt.com
 * @QQ:834667820
 */
public interface OrderService {
	public void createOrder(Order order);
	public int selectUserIdByOrderId(String outTradeNo);

}
