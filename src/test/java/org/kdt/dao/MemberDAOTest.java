package org.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kdt.Config;
import org.kdt.dto.MemberDTO;
import org.kdt.service.MemberService;
import org.kdt.service.MemberServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

class MemberDAOTest {

    @DisplayName("아이디 또는 이메일로 조회")
    @Test
    void findByIdOrEmail(){
        //given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMember_id("wjdrltjr");

        MemberDAO dao = new MemberDAO();
        SqlSession session = Config.getConnection();
        //when
        List<MemberDTO> list = dao.findByIdOrEmail(session, memberDTO);
        //then
        assertThat(list).hasSize(2)
                .extracting("member_name")
                .contains("정기석","wjdrltjr");
    }

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