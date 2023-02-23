package shop.mtcoding.jobara.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

      @GetMapping("/user/{id}")
      public String detail(@PathVariable int id) {
            return "user/detail";
      }

      @GetMapping("/user/joinForm")
      public String joinForm() {
            return "user/joinForm";
      }

      @GetMapping("/user/list")
      public String list() {
            return "user/list";
      }

      @GetMapping("/user/loginForm")
      public String loginForm() {
            return "user/loginForm";
      }

      @GetMapping("/user/updateForm")
      public String updateForm() {
            return "user/updateForm";
      }

}
