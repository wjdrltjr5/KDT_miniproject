package org.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.kdt.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberDAO {
    Logger log = LoggerFactory.getLogger(this.getClass());
	// 회원가입
	public int insertMember(SqlSession session, MemberDTO dto) {
		int n = session.insert("MemberMapper.insertMember", dto);
		return n;
	}
    public MemberDTO findById(SqlSession session, String memberId) {
        return session.selectOne("MemberMapper.findById", memberId);
    }

}
