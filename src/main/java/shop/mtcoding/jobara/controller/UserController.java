package shop.mtcoding.jobara.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.jobara.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.jobara.ex.CustomException;
import shop.mtcoding.jobara.model.User;
import shop.mtcoding.jobara.service.UserService;

@Controller
public class UserController {

      @Autowired
      private UserService userService;

      @Autowired
      private HttpSession session;

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

      @PostMapping("/user/login")
      public String login(UserLoginReqDto userLoginReqDto) {
            if (userLoginReqDto.getUsername() == null || userLoginReqDto.getUsername().isEmpty()) {
                  throw new CustomException("username을 입력해 주세요.");
            }
            if (userLoginReqDto.getPassword() == null || userLoginReqDto.getPassword().isEmpty()) {
                  throw new CustomException("password를 입력해 주세요.");
            }
            User userPS = userService.login(userLoginReqDto);
            session.setAttribute("usPrincipal", userPS);
            return "redirect:/";
      }

      @GetMapping("/user/updateForm")
      public String updateForm() {
            return "user/updateForm";
      }

}
