package shop.mtcoding.jobara.apply;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.jobara.apply.dto.ApplyResp.EmployeeApplyRespDto;
import shop.mtcoding.jobara.apply.model.ApplyRepository;

@MybatisTest
public class ApplyRepositoryTest {
    
    @Autowired
    private ApplyRepository applyRepository;

    @Test
    public void findByUserIdWithBoardAndResume_test() throws Exception {
        // given
        // ObjectMapper om = new ObjectMapper();
        int userId = 1;

        // when
        List<EmployeeApplyRespDto> EmployeeApplyRespDtoList = applyRepository.findByUserIdWithBoardAndResume(userId);
        // String responseBody = om.writeValueAsString(EmployeeApplyRespDtoList);
        // System.out.println("테스트 : " + responseBody);

        // then
        assertThat(EmployeeApplyRespDtoList.get(0).getResumeTitle()).isEqualTo("이력제 제목1");
        assertThat(EmployeeApplyRespDtoList.get(0).getBoardTitle()).isEqualTo("공고제목1");
        assertThat(EmployeeApplyRespDtoList.get(0).getJobType()).isEqualTo(1);
    }
}
