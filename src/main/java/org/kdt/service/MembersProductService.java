package org.kdt.service;

import org.kdt.dto.MembersProductDTO;

import java.util.List;

public interface MembersProductService {

    List<MembersProductDTO> findByStatusHold();
    int requestOrderPermit(String OrderNo);
    int requestOrderFailure(String OrderNo);
    MembersProductDTO findByNo(String orderNo);

    List<MembersProductDTO> selectProductsByCategory(String category);
    List<MembersProductDTO> searchProductByName(String name);
    List<MembersProductDTO> selectAllProducts(String searchKeyword);
    List<MembersProductDTO> findByMemberNo(int memberNo);

    int requestStock(MembersProductDTO membersProductDTO);
    int deleteStock(MembersProductDTO membersProductDTO);
}
