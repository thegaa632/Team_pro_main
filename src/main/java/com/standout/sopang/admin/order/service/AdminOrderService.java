package com.standout.sopang.admin.order.service;

import java.util.List;
import java.util.Map;

import com.standout.sopang.order.vo.OrderVO;

public interface AdminOrderService {
	
	//�ֹ����
	public List<OrderVO> listNewOrder(Map condMap) throws Exception;

	//�ֹ����� - ��ۼ���
	public void modifyDeliveryState(Map deliveryMap) throws Exception;
}
