package org.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.kdt.dto.MembersProductDTO;

import java.util.List;

public class MembersProductDAO {

    public List<MembersProductDTO> findByStatusHold(SqlSession session){
        return session.selectList("MembersProductMapper.findByStatusHold");
    }

    public int requestOrderPermit(SqlSession session, String orderNo) {
        return 0;
    }
    public MembersProductDTO findByOrderNo(SqlSession session, String orderNo){
        return session.selectOne("MembersProductMapper.findByOrderNo",orderNo);
    }
}
