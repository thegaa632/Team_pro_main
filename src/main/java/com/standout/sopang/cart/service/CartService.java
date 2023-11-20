package com.standout.sopang.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.standout.sopang.cart.vo.CartVO;
import com.standout.sopang.goods.vo.GoodsVO;

public interface CartService {
	//��ٱ���
	public Map<String ,List> myCartList(CartVO cartVO) throws Exception;
	
	//��ٱ��� �߰�
	boolean findCartGoods(CartVO cartVO) throws Exception;
	public void addGoodsInCart(CartVO cartVO) throws Exception;
	
	//��ٱ��� ����
	public void removeCartGoods(int cart_id) throws Exception;
	
	//��ٱ��� ����
	public boolean modifyCartQty(CartVO cartVO) throws Exception;
}
