package com.standout.sopang.common.interceptor;

import java.sql.Wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.standout.sopang.member.vo.MemberVO;

public class ViewNameInterceptor extends HandlerInterceptorAdapter {

	//count�� ������ �ҷ����� ���� intercrptor�� sqlSession�߰�.
	@Autowired
	private SqlSession sqlSession;
	
	//�Ϲݻ��������, ������������ �����ϱ� ���� memberVO ���� ����Ѵ�.
	@Autowired
	private MemberVO memberVO;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		
		HttpSession session = request.getSession();
		
		try {
			//�����Ȯ��
			memberVO=(MemberVO)session.getAttribute("memberInfo");
			String  member_id=memberVO.getMember_id();
			
			//����, īƮ����, �ֹ�����, ���θӴ� ���
			int cartCount = 0;
			cartCount=sqlSession.selectOne("mapper.sopang.counts.cartLen",member_id);
			session.setAttribute("cartCount", cartCount);
			
			int deliveringCount = 0;
			deliveringCount=sqlSession.selectOne("mapper.sopang.counts.deliveringLen",member_id);
			session.setAttribute("deliveringCount", deliveringCount);
			
			Long sopang_money = 0L;
			sopang_money=(Long)sqlSession.selectOne("mapper.sopang.counts.sopang_money",member_id);
			session.setAttribute("sopang_money", sopang_money);
			
			//�������ϰ��, ��ǰ����, �ֹ��Ǽ�, �Ѹ��� ���.
			//System.out.println(member_id);
			if(member_id.equals("1111") == true) {
				int goodsLen = 0;
				goodsLen=sqlSession.selectOne("mapper.sopang.counts.goodsLen");
				session.setAttribute("goodsLen", goodsLen);
				
				int ordersLen = 0;
				ordersLen=sqlSession.selectOne("mapper.sopang.counts.ordersLen");
				session.setAttribute("ordersLen", ordersLen);
				
				Long totalSales = 0L;
				totalSales=(Long)sqlSession.selectOne("mapper.sopang.counts.totalSales");
				session.setAttribute("totalSales", totalSales);
				
				
			}
			
		}catch (Exception e) {
			//System.out.println("�α��������ʾҰų� �����ϱ� ����� ���ܰ� �߻��߽��ϴ�.");
		}
		
		//���� viewName ����.
		try {
			String viewName = getViewName(request);
			request.setAttribute("viewName", viewName);
		} catch (Exception e) {e.printStackTrace();}
		
		return true;
	}

	//��û�� ��û url���� viewName�� ������ getViewName �޼ҵ�, fileName return
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		
		//�Ķ���Ͱ� �������
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}
		
		//��ξȿ� ���ϵ��� �������
		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/", 1), fileName.length());
		}
		
		return fileName;
	}

}
