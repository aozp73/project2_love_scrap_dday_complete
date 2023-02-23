package shop.mtcoding.jobara.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

      @GetMapping({ "/", "/home" })
      public String home() {
            return "board/home";
      }

      @GetMapping("/board/{id}")
      public String detail(@PathVariable int id) {
            return "board/detail";
      }

      @GetMapping("/board/list")
      public String list() {
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
