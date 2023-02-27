package shop.mtcoding.jobara.apply;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.jobara.apply.dto.ApplyResp.ListRespDto;
import shop.mtcoding.jobara.common.dto.ResponseDto;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.company.model.Company;
import shop.mtcoding.jobara.user.model.User;

@Controller
public class ApplyController {
    @Autowired
    HttpSession session;

    @Autowired
    ApplyService applyService;

    @GetMapping("/board/{id}/apply")
    public ResponseEntity<?> apply(@PathVariable Integer id) {
        User usPrincipal = (User) session.getAttribute("usPrincipal");
        Verify.validateApiObject(usPrincipal, "로그인이 필요한 기능입니다");
        applyService.insertApply(id, usPrincipal.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "지원 성공", null), HttpStatus.OK);
    }

    @GetMapping("/company/apply")
    public String list(Model model){
        Company coPrincipal = (Company) session.getAttribute("coPrincipal");
        Verify.validateObject(coPrincipal, "로그인이 필요한 기능입니다",HttpStatus.UNAUTHORIZED, "/company/loginForm");
        List<ListRespDto> applyListDto = applyService.getApplyList(coPrincipal.getId());
        model.addAttribute("applyList", applyListDto);
    return "company/applyList";
    }
}
