package org.kdt.service;

import org.kdt.dto.MemberDTO;

public interface MemberService {
	int insertMember(MemberDTO dto);
	MemberDTO findbyId(String memberId);
	boolean login(MemberDTO memberDTO);

}
