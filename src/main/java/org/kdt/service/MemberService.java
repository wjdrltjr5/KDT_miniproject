package org.kdt.service;

import java.util.HashMap;

import org.kdt.dto.MemberDTO;

public interface MemberService {
	int insertMember(MemberDTO dto);
	MemberDTO findbyId(String memberId);
	boolean login(MemberDTO memberDTO);
	String findId(MemberDTO memberDTO);
	String findPasswd(MemberDTO memberDTO);
	
    int chargeMoney(MemberDTO memberDTO, int money);
}
