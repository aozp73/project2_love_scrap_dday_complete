package shop.mtcoding.jobara.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.jobara.dto.board.BoardReq.BoardInsertReqDto;
import shop.mtcoding.jobara.dto.board.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.dto.board.BoardResp.BoardListRespDto;
import shop.mtcoding.jobara.dto.board.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.ex.CustomException;
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

      @PostMapping("/board/save")
      public String save(BoardInsertReqDto boardInsertReqDto) {
            // Mock
            Company mockCompanyUser = companyRepository.findById(1);
            session.setAttribute("coPrincipal", mockCompanyUser);
            Company coPrincipal = (Company) session.getAttribute("coPrincipal");

            // 인증
            Verify.validateObject(coPrincipal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST, "/company/loginForm");

            // 유효성
            Verify.validateStiring(boardInsertReqDto.getTitle(), "제목을 입력하세요");
            Verify.validateStiring(boardInsertReqDto.getContent(), "내용을 입력하세요");
            Verify.validateStiring(boardInsertReqDto.getCareer(), "경력을 입력하세요");

            if (boardInsertReqDto.getCareer().equals("경력선택")) {
                  throw new CustomException("경력을 선택하세요");
            }

            boardService.insertBoard();

            return "redirect:/board/list";
      }

      @GetMapping("/company/{id}/board")
      public String myBoardList(@PathVariable int id) {
            return "board/myBoardList";
      }

}
