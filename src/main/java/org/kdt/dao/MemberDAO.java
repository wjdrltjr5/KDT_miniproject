package org.kdt.dao;

import java.util.HashMap;
import java.util.List;

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

	public List<MemberDTO> findByIdOrEmail(SqlSession session, MemberDTO memberDTO) {
		return session.selectList("MemberMapper.findByIdOrEmail", memberDTO);
	}

	public int updateBalance(SqlSession session, MemberDTO memberDTO) {
		return session.update("MemberMapper.updateBalance",memberDTO);
	}

	public MemberDTO findByNo(SqlSession session, String memberNo){
		return session.selectOne("MemberMapper.findByNo",memberNo);
	}
	
	public String findId(SqlSession session, MemberDTO memberDTO) {
		HashMap<String, String> map = new HashMap<>();
		map.put("member_name", memberDTO.getMember_name());
		map.put("member_email", memberDTO.getMember_email());
		return session.selectOne("MemberMapper.findId",map);
	}
	public String findPasswd(SqlSession session, MemberDTO memberDTO) {
		HashMap<String, String> map = new HashMap<>();
		map.put("member_name", memberDTO.getMember_name());
		map.put("member_id", memberDTO.getMember_id());
		map.put("member_email", memberDTO.getMember_email());
		return session.selectOne("MemberMapper.findPasswd",map);
	}
}
