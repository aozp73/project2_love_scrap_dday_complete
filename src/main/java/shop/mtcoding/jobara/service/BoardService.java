package shop.mtcoding.jobara.service;

import java.util.List;

import org.apache.ibatis.annotations.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.jobara.dto.board.BoardReq.BoardInsertReqDto;
import shop.mtcoding.jobara.dto.board.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.dto.board.BoardResp.BoardListRespDto;
import shop.mtcoding.jobara.dto.board.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.ex.CustomException;
import shop.mtcoding.jobara.model.Board;
import shop.mtcoding.jobara.model.BoardRepository;
import shop.mtcoding.jobara.util.Parse;

@Transactional(readOnly = true)
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void insertBoard(BoardInsertReqDto boardInsertReqDto, int companyId) {

        // career : String -> int parsing
        Integer career = Parse.careerToInt(boardInsertReqDto.getCareer());
        Board board = new Board(companyId, boardInsertReqDto.getTitle(), boardInsertReqDto.getContent(),
                career);

        try {
            boardRepository.insert(board);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public List<BoardMainRespDto> getListToMain() {

        List<BoardMainRespDto> boardListPS;

        try {
            boardListPS = boardRepository.findAllWithCompanyToMain();
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return boardListPS;
    }

    public BoardDetailRespDto getDetail(int id) {
        BoardDetailRespDto boardDetailPS;

        try {
            boardDetailPS = boardRepository.findByIdWithCompany(id);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return boardDetailPS;
    }

    public List<BoardListRespDto> getList() {
        List<BoardListRespDto> boardListPS;

        try {
            boardListPS = boardRepository.findAllWithCompany();
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return boardListPS;
    }

}
