package shop.mtcoding.jobara.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.jobara.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.jobara.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.jobara.dto.user.UserReq.UserUpdateReqDto;
import shop.mtcoding.jobara.model.User;
import shop.mtcoding.jobara.service.UserService;
import shop.mtcoding.jobara.util.Verify;

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

      @PostMapping("/user/join")
      public String login(UserJoinReqDto userJoinReqDto) {
            Verify.validateStiring(userJoinReqDto.getUsername(), "유저네임을 입력하세요.");
            Verify.validateStiring(userJoinReqDto.getPassword(), "암호를 입력하세요.");
            Verify.validateStiring(userJoinReqDto.getEmail(), "이메일을 입력하세요.");
            userService.insertUser(userJoinReqDto);
            return "redirect:/";
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
            Verify.validateStiring(userLoginReqDto.getUsername(), "유저네임을 입력하세요.");
            Verify.validateStiring(userLoginReqDto.getPassword(), "암호를 입력하세요.");
            User userPS = userService.getUser(userLoginReqDto);
            session.setAttribute("usPrincipal", userPS);
            return "redirect:/";
      }

      @GetMapping("/user/updateForm")
      public String updateForm(Model model) {
            User usPrincipal = (User) session.getAttribute("usPrincipal");
            Verify.validateObject(usPrincipal, "로그인이 필요합니다.");

            User userPS = userService.getUser(usPrincipal.getId());
            model.addAttribute("user", userPS);
            return "user/updateForm";
      }

      @PostMapping("/user/update")
      public String update(UserUpdateReqDto userUpdateReqDto) {
            User usPrincipal = (User) session.getAttribute("usPrincipal");
            Verify.validateObject(usPrincipal, "로그인이 필요합니다.");
            Verify.validateStiring(userUpdateReqDto.getPassword(), "암호를 입력하세요.");
            Verify.validateStiring(userUpdateReqDto.getEmail(), "이메일을 입력하세요.");

            User userPS = userService.updateUser(userUpdateReqDto, usPrincipal.getId());

            session.removeAttribute("usPrincipal");
            Verify.validateObject(userPS, "유저 정보를 갱신하는데 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
            session.setAttribute("usPrincipal", userPS);
            return "redirect:/";
      }

}
