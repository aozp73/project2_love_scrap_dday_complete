package shop.mtcoding.jobara.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.jobara.common.dto.ResponseDto;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.user.dto.UserReq.UserJoinReqDto;
import shop.mtcoding.jobara.user.dto.UserReq.UserLoginReqDto;
import shop.mtcoding.jobara.user.dto.UserReq.UserResumeFormReqDto;
import shop.mtcoding.jobara.user.dto.UserReq.UserUpdateReqDto;
import shop.mtcoding.jobara.user.model.User;

@Controller
public class UserController {

      @Autowired
      private UserService userService;

      @Autowired
      private HttpSession session;

      @GetMapping("/user/{id}")
      public String detail(@PathVariable int id, Model model) {
            User userPS = userService.getUser(id);
            model.addAttribute("user", userPS);
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
      public String list(Model model) {
            List<User> userListPS = userService.getUser().subList(0, 4); // 임시로 4개까지만 추출
            model.addAttribute("userList", userListPS);
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

      @GetMapping("/user/{id}/resumeForm")
      public String resumeForm(@PathVariable int id, Model model) {
            User usPrincipal = (User) session.getAttribute("usPrincipal");
            Verify.validateObject(usPrincipal, "로그인이 필요합니다.");

            User userPS = userService.getUser(id);
            model.addAttribute("user", userPS);
            return "user/resumeForm";
      }

      @PostMapping("/user/{id}/resume")
      public @ResponseBody ResponseEntity<?> resume(@RequestBody UserResumeFormReqDto userResumeFormReqDto,
                  @PathVariable int id) {
            User usPrincipal = (User) session.getAttribute("usPrincipal");
            Verify.validateObject(usPrincipal, "로그인이 필요합니다.");

            User userPS = userService.updateUser(userResumeFormReqDto, id);

            session.removeAttribute("usPrincipal");
            Verify.validateObject(userPS, "이력서 정보를 갱신하는데 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
            session.setAttribute("usPrincipal", userPS);

            return new ResponseEntity<>(new ResponseDto<>(1, "작성 완료", null), HttpStatus.CREATED);
      }

      @GetMapping(value = "/user/checkUsername")
      public @ResponseBody ResponseDto<?> checkUsername(String username) {
            if (username == null || username.isEmpty()) {
                  return new ResponseDto<>(-1, "유저네임을 입력하세요.", null);
            }
            User user = userService.checkUsername(username);
            if (user != null) {
                  return new ResponseDto<>(-1, "이미 존재하는 유저네임 입니다.", false);
            } else {
                  return new ResponseDto<>(1, "사용가능한 유저네임 입니다.", true);
            }
      }

}
