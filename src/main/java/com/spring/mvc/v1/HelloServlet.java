package com.spring.mvc.v1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
   # 서블릿: 웹 브라우저의 동적인 요청을 처리하여
         서버에서 html을 생성하여 응답하는 클래스
 */
//URL부여 : 우리서버에 /hello라고 주소요청을 하면 내가 처리하겠다
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    public HelloServlet() {
        System.out.println("헬로서블릿 객체 생성됨!");
    }

    //HTTP요청이 왔을 때 WAS에 의해 자동호출되는 메서드
    //용도: 핵심 로직을 기술하는 곳
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientIP = req.getRemoteAddr();
        System.out.println("환영합니다!! - " + clientIP);
        String age = req.getParameter("age");
        System.out.println("데이터: " + age);
    }
}
