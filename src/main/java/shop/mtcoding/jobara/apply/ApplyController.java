package shop.mtcoding.jobara.apply;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.jobara.common.dto.ResponseDto;
import shop.mtcoding.jobara.common.ex.CustomApiException;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
public class ApplyController {

    @Autowired
    private HttpSession session;

    @Autowired
    private ApplyService applyService;

    @GetMapping("/board/{id}/apply")
    public ResponseEntity<?> apply(@PathVariable Integer id) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateApiObject(principal, "로그인이 필요한 기능입니다");
        if (!principal.getRole().equals("employee")) {
            throw new CustomApiException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        applyService.insertApply(id, principal.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "지원 성공", null), HttpStatus.OK);
    }
}
