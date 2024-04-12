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

    public MemberDTO findbyId(String memberId){
        try(SqlSession session = Config.getConnection()) {
            return memberDAO.findById(session,memberId);
        }

    }
    public boolean login(MemberDTO memberDTO) {
        try(SqlSession session = Config.getConnection()){
            MemberDTO check = memberDAO.findById(session, memberDTO.getMember_id());
            if (check == null){
                return false;
            }
            return checkIdAndPassword(memberDTO,check);
        }

    }

    public boolean checkIdAndPassword(MemberDTO memberDTO, MemberDTO check){
        if(memberDTO.getMember_id().equals(check.getMember_id())
                && memberDTO.getMember_passwd().equals(check.getMember_passwd())){
            return true;
        }else return false;
    }
}
