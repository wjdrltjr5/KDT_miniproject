package org.kdt.service;

import org.kdt.dao.MemberDAO;
import org.kdt.dto.MemberDTO;

public interface MemberService {
	public int insertMember(MemberDTO dto);
	public void setMemberDAO(MemberDAO memberDAO);
}
