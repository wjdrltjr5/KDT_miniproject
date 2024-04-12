package org.kdt.service;

import org.apache.ibatis.session.SqlSession;
import org.kdt.Config;
import org.kdt.dao.MemberDAO;
import org.kdt.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberServiceImpl implements MemberService {
	Logger log = LoggerFactory.getLogger(this.getClass());
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
    @Override
    public MemberDTO findbyId(String memberId){
        try(SqlSession session = Config.getConnection()) {
            log.info("session = {}",session);
            return memberDAO.findById(session,memberId);
        }

    }
    @Override
    public boolean login(MemberDTO memberDTO) {
        try(SqlSession session = Config.getConnection()){
            MemberDTO check = memberDAO.findById(session, memberDTO.getMember_id());
            if (check == null){
                return false;
            }
            return checkIdAndPassword(memberDTO,check);
        }

    }
    @Override
    public boolean checkIdAndPassword(MemberDTO memberDTO, MemberDTO check){
        if(memberDTO.getMember_id().equals(check.getMember_id())
                && memberDTO.getMember_passwd().equals(check.getMember_passwd())){
            return true;
        }else return false;
    }
}
