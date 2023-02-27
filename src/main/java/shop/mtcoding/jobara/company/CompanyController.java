package shop.mtcoding.jobara.company;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import shop.mtcoding.jobara.common.dto.ResponseDto;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.company.dto.CompanyReq.CompanyJoinReqDto;
import shop.mtcoding.jobara.company.dto.CompanyReq.CompanyLoginReqDto;
import shop.mtcoding.jobara.company.dto.CompanyReq.CompanyUpdateReqDto;
import shop.mtcoding.jobara.company.model.Company;

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
            Verify.validateObject(coPrincipal, "로그인이 필요합니다.", HttpStatus.UNAUTHORIZED, "/company/loginForm");
            model.addAttribute("coPrincipal", coPrincipal);
            return "company/updateForm";
      }

      @GetMapping("/logout")
      public String logout() {
            session.invalidate();
            return "redirect:/";
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
      public String update(CompanyUpdateReqDto companyUpdateReqDto, MultipartFile profile) {
            Company coPrincipal = (Company) session.getAttribute("coPrincipal");
            Verify.validateObject(coPrincipal, "로그인이 필요합니다.", HttpStatus.UNAUTHORIZED, "/company/loginForm");
            Verify.validateStiring(companyUpdateReqDto.getAddress(), "주소를 입력하세요.");
            Verify.validateStiring(companyUpdateReqDto.getDetailAddress(), "상세 주소를 입력하세요.");
            Verify.validateStiring(companyUpdateReqDto.getCompanyScale(), "회사 규모란을 선택하세요.");
            Verify.validateStiring(companyUpdateReqDto.getCompanyField(), "회사 업종란을 선택하세요.");
            Verify.validateStiring(companyUpdateReqDto.getPassword(), "암호를 입력하세요.");
            Verify.validateStiring(companyUpdateReqDto.getEmail(), "이메일을 입력하세요.");
            Verify.validateStiring(companyUpdateReqDto.getCompanyName(), "회사 이름을 입력하세요.");
            Verify.validateStiring(companyUpdateReqDto.getTel(), "전화번호를 입력하세요.");
            Company companyPS = companyService.updateCompany(companyUpdateReqDto, coPrincipal.getId(), profile);
            session.removeAttribute("coPrincipal");
            session.setAttribute("coPrincipal", companyPS);
            return "redirect:/";
      }

      @GetMapping("/company/usernameSameCheck")
      public @ResponseBody ResponseEntity<?> usernameSameCheck(String username) {
            // 유효성 검사
            Verify.validateStiring(username, "유저네임을 입력하세요.");

            if (companyService.checkUsername(username) == null) {
                  return new ResponseEntity<>(new ResponseDto<>(1, "사용가능한 유저네임 입니다.", true), HttpStatus.OK);
            } else {
                  return new ResponseEntity<>(new ResponseDto<>(-1, "이미 존재하는 유저네임 입니다.", false), HttpStatus.OK);
            }

      }
}
