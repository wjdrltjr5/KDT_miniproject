package org.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.kdt.dto.MemberDTO;

public class MemberDAO {
    public MemberDTO findById(SqlSession session, String memberId) {
        return session.selectOne("MemberMapper.findById", memberId);
    }
}
