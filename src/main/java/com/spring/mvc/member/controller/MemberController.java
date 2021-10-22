package com.spring.mvc.member.controller;

import com.spring.mvc.member.domain.Member;
import com.spring.mvc.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입 양식 요청
    @GetMapping("/member/sign-up")
    public String signUp(){
        return "/member/join";
    }

    //아이디, 이메일 중복체크 비동기 요청 처리
    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<Boolean> check(String type, String keyword){
        log.info("/check 비동기 요청 GET!"  + type + "/" + keyword);
        return memberService.isDuplicate(type,keyword)
                ?new ResponseEntity<>(true, HttpStatus.OK)
                :new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //회원가입 들옥 처리
    @PostMapping("/member/sign-up")
    public String signUp(Member member){
        log.info("/member/sign-up POST !" + member);

        memberService.signUp(member);
        return "redirect:/board/list";
    }

    //로그인 양식 요청
    @GetMapping("/member/sign-in")
    public String signIn(){
        return "member/login";
    }
    
    //로그인 요청 처리 
    @PostMapping("loginCheck")
    public String loginCheck(String account , String password , Model model, HttpSession session){
        log.info("/loginCheck POST! - " +account);

        String resultMessage = memberService.login(account, password);
        log.info(resultMessage);
        model.addAttribute("result",resultMessage);

        //로그인 성공시
        if (resultMessage.equals("loginSuccess")){
            session.setAttribute("loginUser",memberService.getMember(account));
            return  "redirect:/board/list";
        }


        return  "member/login-result";
    }

    //로그아웃 처리
    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        log.info("/member/logout GET!");
        Object loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser != null){ //로그인했다면
            session.removeAttribute("loginUser");
            session.invalidate(); //세션 무효화 ,초기화
        }
        return "redirect:/board/list";
    }


}
