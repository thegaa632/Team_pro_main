package com.standout.sopang.goods.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.standout.sopang.goods.vo.GoodsVO;
import com.standout.sopang.goods.vo.ImageFileVO;

public interface GoodsDAO {
	//���������� - ���� status��
	public List<GoodsVO> selectGoodsList(String goodsStatus ) throws DataAccessException;
	
	//header ī�װ���
	public List<GoodsVO> selectMenusList(String goodsStatus ) throws DataAccessException;
	
	//����Ʈ������
	public List<GoodsVO> selectGoodsByMenuGoods(String menuGoods) throws DataAccessException;
	
	//��õŰ����
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException;
	
	//�˻�
	public List<GoodsVO> selectGoodsBySearchWord(String searchWord) throws DataAccessException;
	
	//��ǰ��
	public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException;
	public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException;
}
