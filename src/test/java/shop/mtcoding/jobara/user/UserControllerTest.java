package shop.mtcoding.jobara.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.jobara.user.dto.UserReq.UserResumeFormReqDto;
import shop.mtcoding.jobara.user.model.User;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

      @Autowired
      private MockMvc mvc;

      @Autowired
      private MockHttpSession session;

      @Autowired
      ObjectMapper om;

      @BeforeEach
      public void setUp() {
            User user = new User();
            user.setId(1);
            user.setUsername("ssar");
            user.setPassword("1234");
            user.setEmail("ssar@nate.com");
            user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

            session = new MockHttpSession();
            session.setAttribute("usPrincipal", user);
      }

      @Test
      public void login_test() throws Exception {
            // given
            String requestBody = "username=ssar&password=1234";
            // when
            ResultActions resultActions = mvc.perform(post("/user/login").content(requestBody)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
            HttpSession session = resultActions.andReturn().getRequest().getSession();
            User usPrincipal = (User) session.getAttribute("usPrincipal");

            // then
            assertThat(usPrincipal.getUsername()).isEqualTo("ssar");
            resultActions.andExpect(status().is3xxRedirection());
      }

      @Test
      public void join_test() throws Exception {
            // given
            String requestBody = "username=love&password=1234&email=love@nate.com";
            // when
            ResultActions resultActions = mvc.perform(post("/user/join").content(requestBody)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

            // then
            resultActions.andExpect(status().is3xxRedirection());
      }

      @Test
      public void update_test() throws Exception {
            // given
            String requestBody = "password=1234&email=ssar@nate.com&tel=01099876554&career=2";
            String requestBody2 = "password=1234&email=&tel=01099876554&career=2"; // email null 테스트

            // when
            ResultActions resultActions = mvc.perform(post("/user/update").content(requestBody)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).session(session));
            ResultActions resultActions2 = mvc.perform(post("/user/update").content(requestBody2)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).session(session));

            // then
            resultActions.andExpect(status().is3xxRedirection());
            resultActions2.andExpect(status().is4xxClientError());

      }

      @Test
      public void list_test() throws Exception {
            // given

            // when
            ResultActions resultActions = mvc.perform(get("/user/list"));
            Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();

            // then
            List<User> user = (List<User>) map.get("userList");
            assertThat(user.size()).isEqualTo(4);
            assertThat(user.get(0).getRealName()).isEqualTo("김살");
            resultActions.andExpect(status().is2xxSuccessful());

      }

      @Test
      public void detail_test() throws Exception {
            // given
            int id = 1;

            // when
            ResultActions resultActions = mvc.perform(get("/user/id"));
            Map<String, Object> map = resultActions.andReturn().getModelAndView().getModel();
            User user = (User) map.get("user");
            // then
            assertThat(user.getRealName()).isEqualTo("김살");
            resultActions.andExpect(status().is2xxSuccessful());

      }

      @Test
      public void resume_test() throws Exception {
            // given
            int id = 1;
            UserResumeFormReqDto resumeForm = new UserResumeFormReqDto();
            resumeForm.setResumeTitle("이력서 이름 1");
            resumeForm.setResumeContent("이력서 내용 1");

            String reqBody = om.writeValueAsString(resumeForm);

            // when
            ResultActions resultActions = mvc.perform(post("/user/" + id + "/resume").content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).session(session));

            User user = (User) session.getAttribute("usPrincipal");

            // then
            resultActions.andExpect(jsonPath("$.code").value(1));
            assertThat(user.getResumeTitle()).isEqualTo("이력서 이름 1");

      }

      @Test
      public void checkUsername_test() throws Exception {
            // given
            String username = "ssar";

            // when
            ResultActions resultActions = mvc.perform(get("/user/checkUsername?username=" + username));

            // then
            resultActions.andExpect(jsonPath("$.code").value(-1));
            resultActions.andExpect(jsonPath("$.msg").value("이미 존재하는 유저네임 입니다."));

      }

}
