package org.kdt.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kdt.dao.MemberDAO;
import org.kdt.dao.MembersProductDAO;
import org.kdt.dao.ProductDAO;
import org.kdt.dto.MembersProductDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MembersProductServiceImplTest {
    @DisplayName("입고 요청을 허가한다.")
    @Test
    void requestOrderPermit(){
        //given

        //when

        //then
    }

    @DisplayName("주문번호에 해당하는 memberProductDTO 조회")
    @Test
    void findByOrderNo(){
        //given
        MembersProductService membersProductService = new MembersProductServiceImpl(new MembersProductDAO(),new ProductDAO(),new MemberDAO());
        //when
        MembersProductDTO byNo = membersProductService.findByNo("1");
        //then
        assertThat(byNo).extracting("member_product_no","member_no")
                .contains("1","4");
    }

    @DisplayName("대기중인 입고 요청목록들을 보여준다.")
    @Test
    void findByStatusHold(){
        //given
        MembersProductService membersProductService = new MembersProductServiceImpl(new MembersProductDAO(), new ProductDAO(), new MemberDAO());
        //when
        List<MembersProductDTO> byStatusHold = membersProductService.findByStatusHold();
        //then
        assertThat(byStatusHold).hasSize(1);
    }
}