package shop.mtcoding.jobara.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {

      @GetMapping("/company/joinForm")
      public String joinForm() {
            return "company/joinForm";
      }

      @GetMapping("/company/loginForm")
      public String loginForm() {
            return "company/loginForm";
      }

      @GetMapping("/company/updateForm")
      public String updateForm() {
            return "company/updateForm";
      }

}
