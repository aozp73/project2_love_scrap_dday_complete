package shop.mtcoding.jobara.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.jobara.dto.board.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.dto.board.BoardResp.BoardMainRespDto;

@Mapper
public interface BoardRepository {

    public List<Board> findAll();

    public List<BoardMainRespDto> findAllWithCompany();

    public BoardDetailRespDto findByIdWithCompany(int boardId);

    public Board findById(int id);

    public int insert(Board board);

    public int updateById(Board board);

    public int deleteById(int id);
}
