package org.kdt.service;

import org.apache.ibatis.session.SqlSession;
import org.kdt.Config;
import org.kdt.dao.MembersProductDAO;
import org.kdt.dto.MembersProductDTO;

import java.util.List;

public class MembersProductServiceImpl implements MembersProductService{
    private final MembersProductDAO membersProductDao;

    public MembersProductServiceImpl(MembersProductDAO membersProductDao) {
        this.membersProductDao = membersProductDao;
    }
    @Override
    public List<MembersProductDTO> findByStatusHold(){
        try(SqlSession session = Config.getConnection()){
            return membersProductDao.findByStatusHold(session);
        }
    }

}
