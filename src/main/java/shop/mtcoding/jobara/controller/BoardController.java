package shop.mtcoding.jobara.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.jobara.dto.board.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.dto.board.BoardResp.BoardListRespDto;
import shop.mtcoding.jobara.dto.board.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.model.Company;
import shop.mtcoding.jobara.model.CompanyRepository;
import shop.mtcoding.jobara.service.BoardService;
import shop.mtcoding.jobara.util.Verify;

@Controller
public class BoardController {

      @Autowired
      private BoardService boardService;

      @Autowired
      private CompanyRepository companyRepository;

      @Autowired
      private HttpSession session;

      @GetMapping({ "/", "/home" })
      public String home(Model model) {
            List<BoardMainRespDto> boardListPS = boardService.getListToMain();
            model.addAttribute("boardMainList", boardListPS);
            return "board/home";
      }

      @GetMapping("/board/{id}")
      public String detail(@PathVariable int id, Model model) {
            BoardDetailRespDto boardPS = boardService.getDetail(id);
            model.addAttribute("board", boardPS);
            return "board/detail";
      }

      @GetMapping("/board/list")
      public String list(Model model) {
            List<BoardListRespDto> boardListPS = boardService.getList();
            model.addAttribute("boardList", boardListPS);
            return "board/list";
      }

      @GetMapping("/board/saveForm")
      public String saveForm() {
            // Mock
            Company mockCompanyUser = companyRepository.findById(1);
            session.setAttribute("coPrincipal", mockCompanyUser);
            Company coPrincipal = (Company) session.getAttribute("coPrincipal");

            // 인증체크
            Verify.validateObject(coPrincipal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST, "/company/loginForm");

            return "board/saveForm";
      }

      @GetMapping("/company/{id}/board")
      public String myBoardList(@PathVariable int id) {
            return "board/myBoardList";
      }

}
