package com.spring.mvc.member.service;


import com.spring.mvc.member.domain.Member;
import com.spring.mvc.member.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;


    //회원가입 기능
    public void signUp(Member member) {

        //비미번호 암호화
        String rawPw = member.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePw = encoder.encode(rawPw);
        member.setPassword(encodePw);

        memberMapper.register(member);

    }


    /*
     * 중복확인 기능
     * @param type  -검사 유형
     * @param keyword - 검사값
     * @중보
     */
    public boolean isDuplicate(String type, String keyword) {

        Map<String, Object> checkMap = new HashMap<>();
        checkMap.put("type", type);
        checkMap.put("keyword", keyword);


        return memberMapper.isDuplicate(checkMap) == 1;

    }

    //회원정보 조회기능
    public Member getMember(String account) {
        return memberMapper.getUser(account);
    }


    //로그인 처리 기능
    public String login(String inputId, String inputPw){

        Member member = memberMapper.getUser(inputId); // 있으면 정보를 리턴 없으면 널 리턴
        if (member != null){
            String dbPw = member.getPassword();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.matches(inputPw, dbPw) ? "loginSuccess" : "pwFail";

        }else {
            return "idFail";
        }
    }

}
