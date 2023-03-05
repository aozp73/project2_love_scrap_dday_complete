package shop.mtcoding.jobara.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import shop.mtcoding.jobara.board.dto.BoardReq.BoardInsertReqDto;
import shop.mtcoding.jobara.board.dto.BoardReq.BoardUpdateReqDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardUpdateRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyBoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyScrapBoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.PagingDto;
import shop.mtcoding.jobara.common.dto.ResponseDto;
import shop.mtcoding.jobara.common.ex.CustomApiException;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.DateParse;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.love.LoveService;
import shop.mtcoding.jobara.love.dto.LoveResp.LoveDetailRespDto;
import shop.mtcoding.jobara.resume.model.Resume;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    LoveService loveService;

    @Autowired
    HttpSession session;

    @GetMapping({ "/", "/home" })
    public String home(Model model) {

        List<BoardMainRespDto> boardListPS = boardService.getListToMain();
        model.addAttribute("boardMainList", boardListPS);

        return "board/home";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, Model model) {
        BoardDetailRespDto boardPS = boardService.getDetail(id);
        UserVo principal = (UserVo) session.getAttribute("principal");
        if (principal != null) {
            List<Resume> resumeList = boardService.getResume(principal.getId());
            model.addAttribute("resumeList", resumeList);

            if (principal.getRole().equals("employee")) {
                LoveDetailRespDto lovePS = loveService.getLove(id, principal);
                model.addAttribute("love", lovePS);
            }
        }

        List<Integer> boardSkill = boardService.getSkillForDetail(id);
        model.addAttribute("boardSkill", boardSkill);
        model.addAttribute("board", boardPS);

        return "board/detail";
    }

    @GetMapping("/board/list")
    public String list(Model model, Integer page, String keyword) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        PagingDto pagingDto = boardService.getListWithPaging(page, keyword, principal);
        model.addAttribute("pagingDto", pagingDto);

        return "board/list";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        UserVo principal = (UserVo) session.getAttribute("principal");

        // 인증체크
        Verify.validateObject(principal, "로그인이 필요한 페이지입니다.", HttpStatus.BAD_REQUEST,
                "/loginForm");
        if (!principal.getRole().equals("company")) {
            throw new CustomException("기업회원으로 로그인 해주세요.");
        }

        return "board/saveForm";
    }

    @GetMapping("/board/updateForm/{id}")
    public String updateForm(Model model, @PathVariable int id) {

        UserVo principal = (UserVo) session.getAttribute("principal");

        // 인증체크
        Verify.validateObject(
                principal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST,
                "/loginForm");
        if (!principal.getRole().equals("company")) {
            throw new CustomException("기업회원으로 로그인 해주세요.");
        }

        List<Integer> boardSkill = boardService.getSkillForDetail(id);

        BoardUpdateRespDto boardDetailPS = boardService.getDetailForUpdate(id, principal.getId());
        model.addAttribute("boardDetail", boardDetailPS);
        model.addAttribute("boardSkill", boardSkill);
        System.out.println("테스트 : " + boardDetailPS.getDeadline());
        return "board/updateForm";
    }

    @PutMapping("/board/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody BoardUpdateReqDto boardUpdateReqDto) {
        UserVo principal = (UserVo) session.getAttribute("principal");
        // 인증체크
        Verify.validateApiObject(
                principal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST);
        if (!principal.getRole().equals("company")) {
            throw new CustomApiException("기업회원으로 로그인 해주세요.");
        }

        // 유효성

        Verify.validateApiString(boardUpdateReqDto.getDeadline(), "마감 날짜를 선택하세요");

        ArrayList<Object> resDateParse = DateParse.Dday(boardUpdateReqDto.getDeadline());
        if (!(0 < (Integer) resDateParse.get(0) && (Integer) resDateParse.get(0) < 100)) {
            throw new CustomApiException("1일~100일 내의 마감날짜를 선택 해주세요. (~" + (String) resDateParse.get(1) + ")");
        }

        if (boardUpdateReqDto.getCheckedValues().size() == 0) {
            throw new CustomApiException("선호기술을 한 가지 이상 선택해주세요.");
        }

        Verify.validateApiString(boardUpdateReqDto.getTitle(), "제목을 입력하세요");
        Verify.validateApiString(boardUpdateReqDto.getContent(), "내용을 입력하세요");
        Verify.validateApiString(boardUpdateReqDto.getCareerString(), "경력을 입력하세요");

        boardService.updateBoard(boardUpdateReqDto, principal.getId());
        boardService.updateTech(boardUpdateReqDto.getCheckedValues(), id);

        return new ResponseEntity<>(new ResponseDto<>(1, "게시글 수정완료", null), HttpStatus.OK);
    }

    @PostMapping("/board/save")
    public String save(BoardInsertReqDto boardInsertReqDto,
            @RequestParam(required = false, defaultValue = "") ArrayList<Integer> checkLang) {

        UserVo principal = (UserVo) session.getAttribute("principal");

        // 인증체크
        Verify.validateObject(
                principal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST,
                "/loginForm");
        if (!principal.getRole().equals("company")) {
            throw new CustomException("기업회원으로 로그인 해주세요.");
        }

        // 유효성
        Verify.validateString(boardInsertReqDto.getTitle(), "제목을 입력하세요");
        Verify.validateString(boardInsertReqDto.getContent(), "내용을 입력하세요");

        if (boardInsertReqDto.getCareerString().equals("경력선택")) {
            throw new CustomException("경력을 선택하세요");
        }
        if (boardInsertReqDto.getEducationString().equals("학력선택")) {
            throw new CustomException("학력을 선택하세요");
        }
        if (boardInsertReqDto.getJobTypeString().equals("근무형태")) {
            throw new CustomException("근무형태를 선택하세요");
        }

        Verify.validateString(boardInsertReqDto.getDeadline(), "마감 날짜를 선택하세요");

        ArrayList<Object> resDateParse = DateParse.Dday(boardInsertReqDto.getDeadline());
        if (!(0 < (Integer) resDateParse.get(0) && (Integer) resDateParse.get(0) < 100)) {
            throw new CustomException("1일~100일 내의 마감날짜를 선택 해주세요. (~" + (String) resDateParse.get(1) + ")");
        }

        if (checkLang.size() == 0) {
            throw new CustomException("선호기술을 한 가지 이상 선택해주세요.");
        }

        int boardId = boardService.insertBoard(boardInsertReqDto, principal.getId());
        boardService.insertSkill(checkLang, boardId);

        return "redirect:/board/boardList/" + principal.getId();
    }

    @GetMapping("/board/boardList/{id}")
    public String myBoardList(@PathVariable int id, Model model) {

        UserVo principal = (UserVo) session.getAttribute("principal");

        // 인증체크
        Verify.validateObject(
                principal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST,
                "/loginForm");

        if (!principal.getRole().equals("company")) {
            throw new CustomException("기업회원으로 로그인 해주세요.");
        }

        List<MyBoardListRespDto> myBoardListPS = boardService.getMyBoard(principal.getId(), id);
        model.addAttribute("myBoardList", myBoardListPS);

        return "board/myBoardList";
    }

    @GetMapping("/board/scrapList/{id}")
    public String myScrapBoardList(@PathVariable int id, Model model) {

        UserVo principal = (UserVo) session.getAttribute("principal");

        // 인증체크
        Verify.validateObject(
                principal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST,
                "/loginForm");

        if (!principal.getRole().equals("employee")) {
            throw new CustomException("구직회원으로 로그인 해주세요.");
        }

        List<MyScrapBoardListRespDto> myScrapBoardListPS = boardService.getMyScrapBoard(principal.getId(), id);
        model.addAttribute("myScrapBoardList", myScrapBoardListPS);

        return "board/myScrapBoardList";
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {

        UserVo principal = (UserVo) session.getAttribute("principal");
        Verify.validateApiObject(
                principal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST);
        if (!principal.getRole().equals("company")) {
            throw new CustomApiException("기업회원으로 로그인 해주세요.");
        }

        boardService.deleteBoard(id, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "게시글을 삭제하였습니다", null), HttpStatus.OK);
    }

}
