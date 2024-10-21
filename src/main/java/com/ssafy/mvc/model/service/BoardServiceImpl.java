package com.ssafy.mvc.model.service;

import com.ssafy.mvc.model.dao.BoardDao;
import com.ssafy.mvc.model.dto.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    // bean에 등록 되어 있어서 autowired 할 필요가 없다.
    private final BoardDao boardDao;

    public BoardServiceImpl(BoardDao boardDao){
        this.boardDao = boardDao;
    }

    @Override
    public List<Board> getBoardList() {
        System.out.println("모든 게시글을 가지고 왔습니다");
        return boardDao.selectAll();
    }

    @Override
    public Board readBoard(int id) {
        System.out.println(id + "번 글을 읽어옵니다.");
        boardDao.updateViewCnt(id);
        return boardDao.selectOne(id);
    }

    @Override
    public void writeBoard(Board board) {
        System.out.println("게시글 작성했습니다.");
        boardDao.insertBoard(board);
    }

    @Override
    public void removeBoard(int id) {
        System.out.println("게시글을 삭제합니다.");
        boardDao.deleteBoard(id);
    }

    @Override
    public void modifyBoard(Board board) {
        System.out.println("게시글을 수정합니다");
        boardDao.updateBoard(board);

    }
}
