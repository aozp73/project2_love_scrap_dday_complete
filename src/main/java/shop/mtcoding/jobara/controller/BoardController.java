package shop.mtcoding.jobara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.jobara.dto.board.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.service.BoardService;

@Controller
public class BoardController {

      @Autowired
      private BoardService boardService;

      @GetMapping({ "/", "/home" })
      public String home() {
            return "board/home";
      }

      @GetMapping("/board/{id}")
      public String detail(@PathVariable int id) {
            return "board/detail";
      }

      @GetMapping("/board/list")
      public String list(Model model) {
            List<BoardMainRespDto> boardList = boardService.getList();
            model.addAttribute("boardList", boardList);
            return "board/list";
      }

      @GetMapping("/board/saveForm")
      public String saveForm() {
            return "board/saveForm";
      }

      @GetMapping("/company/{id}/board")
      public String myBoardList(@PathVariable int id) {
            return "board/myBoardList";
      }

}
