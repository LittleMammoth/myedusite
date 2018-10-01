/**
 * 
 */
package com.inxedu.os.edu.dao.order;

import com.inxedu.os.edu.entity.order.Order;

/**
 * @author 王武
 * @website:www.lemonkt.com
 * @QQ:834667820
 */
public interface OrderDao {
	/**
	 * 创建订单
	 * @param article 订单实体
	 * @return 返回订单ID
	 */
	public void createOrder(Order order);

	/**
	 * @param order
	 * @return
	 */
	public int selectUserIdByOrderId(String outTradeNo);
}
