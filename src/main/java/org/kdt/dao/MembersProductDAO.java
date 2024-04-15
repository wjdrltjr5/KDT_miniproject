package org.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.kdt.dto.MembersProductDTO;

import java.util.List;

public class MembersProductDAO {

    public List<MembersProductDTO> findByStatusHold(SqlSession session){
        return session.selectList("MembersProductMapper.findByStatusHold");
    }

    public int requestOrderPermit(SqlSession session, String orderNo) {
        return session.update("MembersProductMapper.requestOrderPermit", orderNo);
    }
    public MembersProductDTO findByOrderNo(SqlSession session, String orderNo){
        return session.selectOne("MembersProductMapper.findByOrderNo",orderNo);
    }

    public List<MembersProductDTO> selectProductsByCategory(SqlSession session, String category) {
        return session.selectList("MembersProductMapper.selectProductsByCategory", category);
    }

    public List<MembersProductDTO> searchProductByName(SqlSession session, String name) {
        return session.selectList("MembersProductMapper.searchProductByName",name);
    }
    public List<MembersProductDTO> selectProductByAll(SqlSession session, String keyword){
        return session.selectList("MembersProductMapper.selectProductByAll",keyword);
    }

    public int requestOrderFailure(SqlSession session, String orderNo) {
        return session.update("MembersProductMapper.requestOrderFailure",orderNo);
    }

    public List<MembersProductDTO> findByMemberNo(SqlSession session, int memberNo) {
        return session.selectList("MembersProductMapper.findByMemberNo",memberNo);
    }
    public int requestStock(SqlSession session, MembersProductDTO membersProductDTO){
        return session.insert("MembersProductMapper.requestStock", membersProductDTO);
    }

    public int deleteStock(SqlSession session, MembersProductDTO membersProductDTO){
        return session.delete("MembersProductMapper.stockDelete", membersProductDTO);
    }

}
