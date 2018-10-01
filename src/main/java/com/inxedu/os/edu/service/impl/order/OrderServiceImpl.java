/**
 * 
 */
package com.inxedu.os.edu.service.impl.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inxedu.os.edu.dao.order.OrderDao;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.service.order.OrderService;

/**
 * @author 王武
 * @website:www.lemonkt.com
 * @QQ:834667820
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	@Override
	public void createOrder(Order order) {
		orderDao.createOrder(order);
		return;
	}
	@Override
	public int selectUserIdByOrderId(String outTradeNo) {
		return orderDao.selectUserIdByOrderId(outTradeNo);
	}

}
