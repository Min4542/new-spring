package com.spring.mvc.reply.repository;

import com.spring.mvc.common.paging.Page;
import com.spring.mvc.reply.domain.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    //댓글 입력
    boolean save(Reply reply);

    //댓글 수정
    boolean update(Reply reply);

    //댓글 삭제
    boolean delete(int replyNo);

    /*
    * 마이바티스 매퍼는 기본적으로 1개의 매객값만 받릉수 있음
    * 그러나 @param을 통해 여러개를 처리 할 수 있음
    * @param boardNo
    * @param page
    * @return*/
    //댓글 목록 조회
    List<Reply> getList(@Param("boardNo") int boardNo , @Param("page") Page page);

    //총 댓글 수 조회
    int getCount(int boardNo);

    //댓글 개별 조회
    Reply read(int replyNo);
}
