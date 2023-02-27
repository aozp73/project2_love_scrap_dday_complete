package shop.mtcoding.jobara.apply;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.jobara.common.dto.ResponseDto;
import shop.mtcoding.jobara.common.util.Verify;
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
}
