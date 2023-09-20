package com.example.board.controller;

import com.example.board.dto.BoardDto;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);

        return "board/list";
    }

    @GetMapping("/post")
    public String write() {
        return "board/write";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDto = boardService.getPost(no);

        model.addAttribute("boardDto", boardDto);
        return "board/detail";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDto = boardService.getPost(no);
        model.addAttribute("boardDto", boardDto);

        return "board/update";
    }

    @PutMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long id, BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/post/" + id;
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);
        return "redirect:/";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardDtoList);
        model.addAttribute("pageList", pageList);
        return "/board/list";
    }
}

