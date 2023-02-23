package shop.mtcoding.jobara.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.jobara.dto.board.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.ex.CustomException;
import shop.mtcoding.jobara.model.BoardRepository;

@Transactional(readOnly = true)
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<BoardMainRespDto> getList() {
        List<BoardMainRespDto> boardPS;

        try {
            boardPS = boardRepository.findAllWithCompany();
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return boardPS;
    }

}
