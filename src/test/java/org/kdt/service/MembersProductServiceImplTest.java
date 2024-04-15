package org.kdt.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kdt.dao.MembersProductDAO;
import org.kdt.dto.MembersProductDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MembersProductServiceImplTest {


    @DisplayName("대기중인 입고 요청목록들을 보여준다.")
    @Test
    void findByStatusHold(){
        //given
        MembersProductService membersProductService = new MembersProductServiceImpl(new MembersProductDAO());
        //when
        List<MembersProductDTO> byStatusHold = membersProductService.findByStatusHold();
        //then
        assertThat(byStatusHold).hasSize(1);
    }
}