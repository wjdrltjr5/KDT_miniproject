package org.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.kdt.dto.MemberDTO;

public class MemberDAO {
	
	// 회원가입
	public int insertMember(SqlSession session, MemberDTO dto) {
		int n = session.insert("MemberMapper.insertMember", dto);
		return n;
	}
    public MemberDTO findById(SqlSession session, String memberId) {
        return session.selectOne("MemberMapper.findById", memberId);
    }

}
