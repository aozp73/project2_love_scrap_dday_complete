package shop.mtcoding.jobara.apply;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.jobara.apply.dto.ApplyResp.ListRespDto;
import shop.mtcoding.jobara.company.model.Company;
import shop.mtcoding.jobara.user.model.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ApplyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper om;

    private MockHttpSession mockSession;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setId(1);
        user.setUsername("ssar");
        user.setPassword("1234");
        user.setEmail("ssar@nate.com");
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        mockSession = new MockHttpSession();
        mockSession.setAttribute("usPrincipal", user);

        Company company = new Company();
        company.setId(1);
        company.setUsername("ssar");
        company.setPassword("1234");
        company.setEmail("ssar@nate.com");
        company.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        mockSession = new MockHttpSession();
        mockSession.setAttribute("coPrincipal", company);
    }

    @Test
    public void apply_success_test() throws Exception {
        // given
        int id = 3;
        // when
        ResultActions resultActions = mvc.perform(
                get("/board/" + id + "/apply").session(mockSession));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        // System.out.println("테스트 : " + responseBody);
        // verify
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.msg").value("지원 성공"));
    }

    @Test
    public void apply_notLogin_failure_test() throws Exception {
        // given
        int id = 3;
        // when
        ResultActions resultActions = mvc.perform(
                get("/board/" + id + "/apply"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);
        // verify
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void applyList_success_test() throws Exception {
        // given
        int id = 3;
        // when
        ResultActions resultActions = mvc.perform(
                get("/company/" + id).session(mockSession));
        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        List<ListRespDto> applyListDto = (List<ListRespDto>) map.get("applyList");
        String response = om.writeValueAsString(applyListDto);
        System.out.println("테스트 : " + response);
        // verify
        resultActions.andExpect(status().isOk());
        // resultActions.andExpect(jsonPath("$.code").value(1));
        // resultActions.andExpect(jsonPath("$.msg").value("지원 성공"));
    }
}
