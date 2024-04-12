package org.kdt.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kdt.Config;
import org.kdt.dto.MemberDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberDAOTest {
    @DisplayName("회원ID로 조회시 해당하는 회원DTO 반환")
    @Test
    void findById(){
        //given
        MemberDAO dao = new MemberDAO();
        //when
        MemberDTO dto = dao.findById(Config.getConnection(), "wjdrltjr");
        //then
        assertThat(dto.getMember_name()).isEqualTo("정기석");
    }
}