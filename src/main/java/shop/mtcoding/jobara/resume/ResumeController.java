package shop.mtcoding.jobara.resume;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.resume.dto.ResumeReq.ResumeSaveReq;
import shop.mtcoding.jobara.resume.model.Resume;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private HttpSession session;

    @PostMapping("/resume/save")
    public String saveResume(ResumeSaveReq resumeSaveReq) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateObject(principal, "로그인이 필요합니다.");
        if (!principal.getRole().equals("employee")) {
            throw new CustomException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        if (resumeSaveReq.getTitle() == null) {
            resumeSaveReq.setTitle("무제");
        }
        resumeService.insertResume(principal.getId(), resumeSaveReq);
        return "redirect:/";
    }

    @GetMapping("/resume/{id}")
    public String saveResumeForm(@PathVariable("id") Integer id, Model model) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateObject(principal, "로그인이 필요합니다.");
        if (!principal.getRole().equals("employee")) {
            throw new CustomException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        Resume resumePS = resumeService.findById(principal.getId(), id);
        model.addAttribute("resume", resumePS);
        return "employee/resumeForm";
    }
}
