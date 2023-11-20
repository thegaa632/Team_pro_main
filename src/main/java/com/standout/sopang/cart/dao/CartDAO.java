package com.standout.sopang.cart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.standout.sopang.cart.vo.CartVO;
import com.standout.sopang.goods.vo.GoodsVO;

public interface CartDAO {
	//��ٱ���
	public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException;
	public List<GoodsVO> selectGoodsList(List<CartVO> cartList) throws DataAccessException;
	
	//��ٱ��� �߰�
	public boolean selectCountInCart(CartVO cartVO) throws DataAccessException;
	public void insertGoodsInCart(CartVO cartVO) throws DataAccessException;
	
	//��ٱ��� ����
	public void deleteCartGoods(int cart_id) throws DataAccessException;
	
	//��ٱ��� ����
	public void updateCartGoodsQty(CartVO cartVO) throws DataAccessException;

}
