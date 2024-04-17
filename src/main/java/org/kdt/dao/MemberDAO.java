package org.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kdt.dto.MemberDTO;

import java.util.List;

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

	public List<MemberDTO> findByIdOrEmail(SqlSession session, MemberDTO memberDTO) {
		return session.selectList("MemberMapper.findByIdOrEmail", memberDTO);
	}

	public int updateBalance(SqlSession session, MemberDTO memberDTO) {
		return session.update("MemberMapper.updateBalance",memberDTO);
	}

	public MemberDTO findByNo(SqlSession session, String memberNo){
		return session.selectOne("MemberMapper.findByNo",memberNo);
	}
}
