package org.kdt.service;

import com.sun.javadoc.MemberDoc;
import org.kdt.dto.MemberDTO;
import org.kdt.dto.MembersProductDTO;
import org.kdt.dto.ProductDTO;
import org.kdt.exception.NoMoneyException;

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

    int requestStock(MembersProductDTO membersProductDTO, MemberDTO memberDTO) throws NoMoneyException;
    int deleteStock(MembersProductDTO membersProductDTO);

    List<MembersProductDTO> findByMemberNoAndProductsCategory(MemberDTO memberDTO, String category);
    List<MembersProductDTO> findByMemberNoAndProductsCategoryOrProductName(MemberDTO memberDTO,String searchKeyword);
    List<MembersProductDTO> findByMemberNoAndProductsName(MemberDTO memberDTO,String name);

    int updateStock(MembersProductDTO dto);
}
