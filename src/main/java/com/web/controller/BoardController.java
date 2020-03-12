package com.web.controller;

import com.web.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardController {

   // 의존성 주입
   @Autowired
   BoardService boardService;

   @GetMapping({ "", "/" })
   // RequestParam 사용으로 idx 파라미터 필수로 받음, binding 할 값 없다면 defaultValue는 0
   // findBoardByIdx(idx) 조회시 idx 값이 0 이라면 board 값는 null로 return 
   public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
      model.addAttribute("board", boardService.findBoardByIdx(idx));
      return "board/form";
   }

   @GetMapping("/list")
   // PageableDefault 의 parameter인 size, sort, direction 등을 사용해 페이징 처리 규약 정의 가능
   public String list(@PageableDefault Pageable pageable, Model model) {
      model.addAttribute("boardList", boardService.findBoardList(pageable));
      return "board/list";
   }

}