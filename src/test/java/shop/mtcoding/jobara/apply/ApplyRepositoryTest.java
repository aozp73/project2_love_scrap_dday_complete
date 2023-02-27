package shop.mtcoding.jobara.apply;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.jobara.apply.dto.ApplyResp.ListRespDto;
import shop.mtcoding.jobara.apply.model.ApplyRepository;
import shop.mtcoding.jobara.board.dto.BoardResp.MyBoardListRespDto;

@MybatisTest
public class ApplyRepositoryTest {

    @Autowired
    private ApplyRepository applyRepository;

    @Test
    public void findByIdWithCompany_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();
        int companyId = 1;

        // when
        List<ListRespDto> listDtoList = applyRepository.findByCompanyIdWithBoardAndUser(companyId);
        String responseBody = om.writeValueAsString(listDtoList);
        System.out.println("테스트 : " + responseBody);

        // then
        assertThat(listDtoList.get(0).getRealName()).isEqualTo("김살");
    }
}
