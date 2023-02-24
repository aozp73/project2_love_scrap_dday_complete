package shop.mtcoding.jobara.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import shop.mtcoding.jobara.dto.ResponseDto;
import shop.mtcoding.jobara.dto.company.CompanyReq.CompanyJoinReqDto;
import shop.mtcoding.jobara.dto.company.CompanyReq.CompanyLoginReqDto;
import shop.mtcoding.jobara.dto.company.CompanyReq.CompanyUpdateReqDto;
import shop.mtcoding.jobara.model.Company;
import shop.mtcoding.jobara.service.CompanyService;
import shop.mtcoding.jobara.util.Verify;

@Controller
public class CompanyController {

      @Autowired
      private CompanyService companyService;

      @Autowired
      private HttpSession session;

      @GetMapping("/company/joinForm")
      public String joinForm() {
            return "company/joinForm";
      }

      @GetMapping("/company/loginForm")
      public String loginForm() {
            return "company/loginForm";
      }

      @GetMapping("/company/updateForm")
      public String updateForm(Model model) {
            Company coPrincipal = (Company) session.getAttribute("coPrincipal");
            Verify.validateObject(coPrincipal, "로그인이 필요합니다.", HttpStatus.UNAUTHORIZED, "/loginForm");
            model.addAttribute("coPrincipal", coPrincipal);
            return "company/updateForm";
      }

      @PostMapping("/company/login")
      public String login(CompanyLoginReqDto companyLoginReqDto) {
            Verify.validateStiring(companyLoginReqDto.getUsername(), "유저네임을 입력하세요.");
            Verify.validateStiring(companyLoginReqDto.getPassword(), "암호를 입력하세요.");
            Company companyPS = companyService.getCompany(companyLoginReqDto);
            session.setAttribute("coPrincipal", companyPS);
            return "redirect:/";
      }

      @PostMapping("/company/join")
      public String join(CompanyJoinReqDto companyJoinReqDto) {
            Verify.validateStiring(companyJoinReqDto.getUsername(), "유저네임을 입력하세요.");
            Verify.validateStiring(companyJoinReqDto.getPassword(), "암호를 입력하세요.");
            Verify.validateStiring(companyJoinReqDto.getEmail(), "이메일을 입력하세요.");
            Verify.validateObject(companyJoinReqDto.getCompanyNumb(), "사업자 등록번호를 입력하세요.");
            Verify.validateStiring(companyJoinReqDto.getCompanyName(), "회사이름을 입력하세요.");
            Verify.validateStiring(companyJoinReqDto.getAddress(), "주소를 입력하세요.");
            Verify.validateStiring(companyJoinReqDto.getDetailAddress(), "상세주소를 입력하세요.");
            companyService.insertCompany(companyJoinReqDto);
            return "redirect:/company/loginForm";
      }

      @PostMapping("/company/update")
      public String update(CompanyUpdateReqDto companyUpdateReqDto) {
            Company coPrincipal = (Company) session.getAttribute("coPrincipal");
            Verify.validateObject(coPrincipal, "로그인이 필요합니다.", HttpStatus.UNAUTHORIZED, "/loginForm");
            Verify.validateStiring(companyUpdateReqDto.getAddress(), "주소를 입력하세요.");
            Verify.validateStiring(companyUpdateReqDto.getDetailAddress(), "상세 주소를 입력하세요.");
            Verify.validateStiring(companyUpdateReqDto.getCompanyScale(), "회사 규모란을 선택하세요.");
            Verify.validateStiring(companyUpdateReqDto.getCompanyField(), "회사 업종란을 선택하세요.");
            Verify.validateStiring(companyUpdateReqDto.getPassword(), "암호를 입력하세요.");
            Verify.validateStiring(companyUpdateReqDto.getEmail(), "이메일을 입력하세요.");
            Verify.validateStiring(companyUpdateReqDto.getCompanyName(), "회사 이름을 입력하세요.");
            Verify.validateStiring(companyUpdateReqDto.getTel(), "전화번호를 입력하세요.");
            Company companyPS = companyService.updateCompany(companyUpdateReqDto);
            session.removeAttribute("coPrincipal");
            session.setAttribute("coPrincipal", companyPS);
            return "redirect:/";
      }
}
