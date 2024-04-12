package org.kdt.service;

import org.apache.ibatis.session.SqlSession;
import org.kdt.Config;
import org.kdt.dao.MemberDAO;
import org.kdt.dto.MemberDTO;

public class MemberService {

    private final MemberDAO dao;

    public MemberService(MemberDAO dao) {
        this.dao = dao;
    }

    public boolean login(MemberDTO memberDTO) {
        try(SqlSession session = Config.getConnection()){
            MemberDTO check = dao.findById(session, memberDTO.getMember_id());
            return checkIdAndPassword(memberDTO,check);
        }

    }

    private boolean checkIdAndPassword(MemberDTO memberDTO, MemberDTO check){
        if(memberDTO.getMember_id().equals(check.getMember_id())
                && memberDTO.getMember_passwd().equals(check.getMember_passwd())){
            return true;
        }else return false;
    }
}
