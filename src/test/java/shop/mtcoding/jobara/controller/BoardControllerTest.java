package shop.mtcoding.jobara.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import shop.mtcoding.jobara.dto.board.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.dto.board.BoardResp.BoardListRespDto;
import shop.mtcoding.jobara.dto.board.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.model.Company;
import shop.mtcoding.jobara.model.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BoardControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

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
        mockSession.setAttribute("principal", user);
    }

    @Test
    public void saveForm_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(
                get("/board/saveForm"));

        HttpSession session = resultActions.andReturn().getRequest().getSession();
        Company coPrincipal = (Company) session.getAttribute("coPrincipal");

        // then
        assertThat(coPrincipal.getUsername()).isEqualTo("cos");
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void boardDetail_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(
                get("/board/" + id));

        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        BoardDetailRespDto board = (BoardDetailRespDto) map.get("board");

        // String model = om.writeValueAsString(board);
        // System.out.println("테스트 : " + model);

        // then
        resultActions.andExpect(status().isOk());
        assertThat(board.getCompanyScale()).isEqualTo("대기업");
        assertThat(board.getCompanyField()).isEqualTo(" IT업");
    }

    @Test
    public void boardList_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(
                get("/board/list"));

        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        List<BoardListRespDto> boardList = (List<BoardListRespDto>) map.get("boardList");

        // String model = om.writeValueAsString(boardList);
        // System.out.println("테스트 : " + model);

        // then
        resultActions.andExpect(status().isOk());
        assertThat(boardList.get(1).getTitle()).isEqualTo("공고제목2");
        assertThat(boardList.get(2).getTitle()).isEqualTo("공고제목3");
    }

    @Test
    public void boardMainList_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(
                get("/"));

        Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
        List<BoardMainRespDto> boardList = (List<BoardMainRespDto>) map.get("boardMainList");

        // String model = om.writeValueAsString(boardList);
        // System.out.println("테스트 : " + model);

        // then
        resultActions.andExpect(status().isOk());
        assertThat(boardList.get(1).getTitle()).isEqualTo("공고제목2");
        assertThat(boardList.get(2).getTitle()).isEqualTo("공고제목3");
    }
}
