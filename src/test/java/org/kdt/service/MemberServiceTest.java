package org.kdt.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kdt.dao.MemberDAO;
import org.kdt.dto.MemberDTO;

class MemberServiceTest {

    @DisplayName("아이디와 비밀번호가 일치시 true 반환")
    @Test
    void loginSuccess(){
        //given
        MemberDAO dao = new MemberDAO();
        MemberService memberService = new MemberServiceImpl();
        MemberDTO dto = new MemberDTO();
        dto.setMember_id("wjdrltjr");
        dto.setMember_passwd("1234");
        //when
        boolean check = memberService.login(dto);
        //then
        assertThat(check).isTrue();
    }


    @DisplayName("아이디와 비밀번호가 불일치시 false 반환")
    @Test
    void loginFailed(){
        //given
        MemberDAO dao = new MemberDAO();
        MemberService memberService = new MemberServiceImpl();
        MemberDTO dto = new MemberDTO();
        dto.setMember_id("wjdrltjr");
        dto.setMember_passwd("123213124");
        //when
        boolean check = memberService.login(dto);
        //then
        assertThat(check).isFalse();
    }

}