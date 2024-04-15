package org.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.kdt.dto.MembersProductDTO;

import java.util.List;

public class MembersProductDAO {

    public List<MembersProductDTO> findByStatusHold(SqlSession session){
        return session.selectList("MembersProductMapper.findByStatusHold");
    }
}
