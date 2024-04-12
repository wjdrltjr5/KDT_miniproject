package org.kdt.service;

import org.kdt.dao.MemberDAO;
import org.kdt.dto.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.kdt.Config;
import org.kdt.dao.MemberDAO;
import org.kdt.dto.MemberDTO;

public interface MemberService {
	public int insertMember(MemberDTO dto);
	public void setMemberDAO(MemberDAO memberDAO);
	public MemberDTO findbyId(String memberId);
	public boolean login(MemberDTO memberDTO);
	public boolean checkIdAndPassword(MemberDTO memberDTO, MemberDTO check);

}
