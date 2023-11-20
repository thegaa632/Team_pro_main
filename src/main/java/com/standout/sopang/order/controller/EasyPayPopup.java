package com.standout.sopang.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.standout.sopang.member.vo.MemberVO;
import com.standout.sopang.order.service.ApiService01;
import com.standout.sopang.order.vo.OrderVO;

@RestController
public class EasyPayPopup {
	

	@Autowired
	private ApiService01 apiService01;
	
	@RequestMapping(value="/test/kakaoOrder.do")
	public Map<String, Object> kakaoOrder(@RequestParam Map<String, String> dateMap , HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
//		System.out.println("���� ������" + dateMap.toString());
		Map<String, Object> resultMap = new HashMap<String, Object>();
//		System.out.println("�����µ�����" + resultMap.toString());
		
		//�ֹ��� ������ �����´�.
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("orderer");
		
		//�ֹ������� �����´�.
		List<OrderVO> myOrderList = (List<OrderVO>) session.getAttribute("myOrderList");
				
		String itemName = "";
		String orderNumber = "";
		for (int i = 0; i < myOrderList.size(); i++) {
			OrderVO orderVO = (OrderVO) myOrderList.get(i);
			if(myOrderList.size() == 1) {
				itemName = orderVO.getGoods_title();
			}else if(myOrderList.size() > 1){
				itemName = orderVO.getGoods_title() +"�� " + i + "��";
			}
			orderNumber = String.valueOf(orderVO.getOrder_seq_num());
		}

		String userAgent = "WP";
		String merchantId = "himedia";
//		String amount = dateMap.get("amount");
		String amount = "100";
		String userName = memberVO.getMember_name();
		String returnUrl = "��������뵥����";
		String apiCertKey = "ac805b30517f4fd08e3e80490e559f8e";
		String timestamp = "2023020400000000";
		String signature = apiService01.encrypt(merchantId + "|" + orderNumber + "|" + amount + "|" + apiCertKey + "|" + timestamp) ;
		
		Map<String, String> map = new HashMap<String, String>();
		String url = "https://api.testpayup.co.kr/ep/api/kakao/"+merchantId+"/order";
		
		map.put("itemName", itemName);
		map.put("orderNumber", orderNumber);
		map.put("userAgent", userAgent);
		map.put("merchantId", merchantId);
		map.put("amount", amount);
		map.put("userName", userName);
		map.put("returnUrl", returnUrl);
		map.put("apiCertKey", apiCertKey);
		map.put("timestamp", timestamp);
		map.put("signature", signature);
		
		resultMap = apiService01.restApi(map, url);
		System.out.println(resultMap.toString());
		
		return resultMap;
	}
	
	
	
	
	

	@RequestMapping(value="/test/naverOrder.do")
	public Map<String, Object> naverOrder(@RequestParam Map<String, String> dateMap , HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
//		System.out.println("���� ������" + dateMap.toString());
		Map<String, Object> resultMap = new HashMap<String, Object>();
//		System.out.println("�����µ�����" + resultMap.toString());
		
		//�ֹ��� ������ �����´�.
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("orderer");
		
		//�ֹ������� �����´�.
		List<OrderVO> myOrderList = (List<OrderVO>) session.getAttribute("myOrderList");
				
		
		String itemName = "";
		String orderNumber = "";
		for (int i = 0; i < myOrderList.size(); i++) {
			OrderVO orderVO = (OrderVO) myOrderList.get(i);
			if(myOrderList.size() == 1) {
				itemName = orderVO.getGoods_title();
			}else if(myOrderList.size() > 1){
				itemName = orderVO.getGoods_title() +"�� " + i + "��";
			}
			orderNumber = String.valueOf(orderVO.getOrder_seq_num());
		}
		String userAgent = "WP";
		String merchantId = "himedia";
//		String amount = dateMap.get("amount");
		String amount = "100";
		String userName = memberVO.getMember_name();
		String returnUrl = "��������뵥����";
		String apiCertKey = "ac805b30517f4fd08e3e80490e559f8e";
		String timestamp = "2023020400000000";
		String signature = apiService01.encrypt(merchantId + "|" + orderNumber + "|" + amount + "|" + apiCertKey + "|" + timestamp) ;
		
		Map<String, String> map = new HashMap<String, String>();
		String url = "https://api.testpayup.co.kr/ep/api/naver/"+merchantId+"/order";
		
		map.put("itemName", itemName);
		map.put("orderNumber", orderNumber);
		map.put("userAgent", userAgent);
		map.put("merchantId", merchantId);
		map.put("amount", amount);
		map.put("userName", userName);
		map.put("returnUrl", returnUrl);
		map.put("apiCertKey", apiCertKey);
		map.put("timestamp", timestamp);
		map.put("signature", signature);
		
		//���̹� payType ���� -  ī�� / ����Ʈ
		if("���̹�����(ī��)".equals(dateMap.get("payMethod"))) {
			map.put("payType", "CARD");	
		}else if("���̹�����(����Ʈ)".equals(dateMap.get("payMethod"))) {
			map.put("payType", "POINT");	
		}
		
		resultMap = apiService01.restApi(map, url);
		System.out.println(resultMap.toString());
		
		return resultMap;
	}
}
