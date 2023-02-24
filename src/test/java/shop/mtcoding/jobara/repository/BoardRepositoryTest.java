package shop.mtcoding.jobara.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.jobara.dto.board.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.model.BoardRepository;

@MybatisTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findByIdWithCompany_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();
        int boardId = 1;

        // when
        BoardDetailRespDto boardDetailRespDto = boardRepository.findByIdWithCompany(boardId);
        // String responseBody = om.writeValueAsString(boardDetailRespDto);
        // System.out.println("테스트 : " + responseBody);

        // then
        assertThat(boardDetailRespDto.getCompanyScale()).isEqualTo("대기업");
    }
}
