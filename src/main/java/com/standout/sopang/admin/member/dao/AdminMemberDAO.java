package com.standout.sopang.admin.member.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.standout.sopang.member.vo.MemberVO;

public interface AdminMemberDAO {
	// ȸ������
	public ArrayList<MemberVO> listMember(HashMap condMap) throws DataAccessException;
}
