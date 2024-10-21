package com.ssafy.mvc.controller;

import com.ssafy.mvc.model.dto.Board;
import com.ssafy.mvc.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardService.getBoardList();
        model.addAttribute("boards", boards);
        // 포워딩임
        return "/board/list";
    }

    @GetMapping("/writeform")
    public String writeform(){
        return "/board/writeform";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute Board board){
        boardService.writeBoard(board);
        return "redirect:list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") int id, Model model){
        Board board = boardService.readBoard(id);
        model.addAttribute("board", board);
        return "/board/detail";
    }
    // post 요청 시 가산점?
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        boardService.removeBoard(id);
        return "redirect:list"; // url에 list로 요청 다시 보내! 라고 한 것
//        return "/board/list"; // view resolver에게 찾아오라고 한 것
    }

    @GetMapping("/updateform")
    public String updateform(@RequestParam("id") int id, Model model){
        // Model 바구니는 왜 만드는가?
        // 게시글을 하나 가져와서 updateform으로 넘겨야 한다
        model.addAttribute("board", boardService.readBoard(id));
        return "/board/updateform";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Board board){
        boardService.modifyBoard(board);
        return "redirect:detail?id="+board.getId();
    }
}
