package shop.mtcoding.jobara.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.jobara.dto.board.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.dto.board.BoardResp.BoardListRespDto;
import shop.mtcoding.jobara.dto.board.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.dto.board.BoardResp.BoardUpdateRespDto;

@Mapper
public interface BoardRepository {

    public List<Board> findAll();

    public List<BoardMainRespDto> findAllWithCompanyToMain();

    public List<BoardListRespDto> findAllWithCompany();

    public BoardDetailRespDto findByIdWithCompany(int boardId);

    public Board findById(int id);

    public BoardUpdateRespDto findByIdForUpdate(int id);

    public int insert(Board board);

    public int updateById(Board board);

    public int deleteById(int id);
}
