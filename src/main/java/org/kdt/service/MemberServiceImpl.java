package org.kdt.service;

import org.apache.ibatis.session.SqlSession;
import org.kdt.Config;
import org.kdt.dao.MemberDAO;
import org.kdt.dto.MemberDTO;

public class MemberServiceImpl implements MemberService {
	
	private MemberDAO memberDAO;
	@Override
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public int insertMember(MemberDTO dto) {
		int n = 0;
		SqlSession session = null;
		try {
			session = Config.getConnection();
			n = memberDAO.insertMember(session, dto);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}
}
