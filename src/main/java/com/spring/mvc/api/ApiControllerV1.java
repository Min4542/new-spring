package com.spring.mvc.api;


import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.service.BoardService;
import com.spring.mvc.common.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1")
public class ApiControllerV1 {

    @GetMapping("/hello")
    @ResponseBody //클라이언트에게 순수 데이터 리턴
    public String hello(){
        return "안녕";  //안녕.jsp가 열리는데 안녕.jsp가 없어서 404열림
    }

    @GetMapping("/hobby")
    @ResponseBody
    public String[] hobby(){
        return  new String[] {"음악감상", "축구", "RHc"};
    }

    @GetMapping("/major")
    @ResponseBody
    public List<String> major(){
        return Arrays.asList("정보보안","성악과","수학","컴공");
    }

    @GetMapping("/article")
    @ResponseBody
    public Board board(){
        Board board = new Board();
        board.setBoardNo(600);
        board.setTitle("JSON제목");
        board.setContent("하잉");
        board.setWriter("제이");
        return board;
    }
    
    @GetMapping("/food")
    @ResponseBody
    public Map<String,String> food(){
        Map<String,String> foods = new HashMap<>();
        foods.put("한식","육개장");
        foods.put("중식","치킨");
        foods.put("양식","육개장");
        return foods;
    }

    @Autowired
    private BoardService boardService;

    @GetMapping("/b-list")
    @ResponseBody
    public List<Board> list(){
        return boardService.getArticles(new Page(1,20));
    }
}
