package shop.mtcoding.jobara.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.jobara.model.Company;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mvc;

    private MockHttpSession mockSession;

    @BeforeEach
    public void setUp() {
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
    public void login_test() throws Exception {
        // given
        String usernameVal = "cos";
        String passwordVal = "1234";
        StringBuffer sb = new StringBuffer();
        sb.append("username=");
        sb.append(usernameVal);
        sb.append("&password=");
        sb.append(passwordVal);
        String requestBody = sb.toString();

        // when
        ResultActions resultActions = mvc.perform(post("/company/login")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        HttpSession session = resultActions.andReturn().getRequest().getSession();
        Company coPrincipal = (Company) session.getAttribute("coPrincipal");

        // verify
        resultActions.andExpect(status().is3xxRedirection());
        assertThat(coPrincipal.getUsername()).isEqualTo("cos");
    }

    @Test
    public void join_test() throws Exception {
        // given
        String usernameVal = "love";
        String passwordVal = "1234";
        String emailVal = "love@naver.com";
        String companyNumbVal = "1234123333";
        String companyNameVal = "(주)야호";
        String addressVal = "부산광역시 야구 김동";
        String detailAddressVal = "석진2길";
        StringBuffer sb = new StringBuffer();
        sb.append("username=");
        sb.append(usernameVal);
        sb.append("&password=");
        sb.append(passwordVal);
        sb.append("&email=");
        sb.append(emailVal);
        sb.append("&companyNumb=");
        sb.append(companyNumbVal);
        sb.append("&companyName=");
        sb.append(companyNameVal);
        sb.append("&address=");
        sb.append(addressVal);
        sb.append("&detailAddress=");
        sb.append(detailAddressVal);
        String requestBody = sb.toString();

        // when
        ResultActions resultActions = mvc.perform(post("/company/join")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        // verify
        resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    public void update_test() throws Exception {
        // given
        String passwordVal = "1234";
        String emailVal = "love@naver.com";
        String companyNameVal = "(주)야호";
        String addressVal = "부산광역시 야구 김동";
        String detailAddressVal = "석진2길";
        String companyScaleVal = "대기업";
        String companyFieldVal = "SW 개발";
        String telVal = "01020340234";
        StringBuffer sb = new StringBuffer();
        sb.append("password=");
        sb.append(passwordVal);
        sb.append("&email=");
        sb.append(emailVal);
        sb.append("&companyName=");
        sb.append(companyNameVal);
        sb.append("&address=");
        sb.append(addressVal);
        sb.append("&detailAddress=");
        sb.append(detailAddressVal);
        sb.append("&companyScale=");
        sb.append(companyScaleVal);
        sb.append("&companyField=");
        sb.append(companyFieldVal);
        sb.append("&tel=");
        sb.append(telVal);
        String requestBody = sb.toString();

        // when
        ResultActions resultActions = mvc.perform(post("/company/update")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .session(mockSession));
        HttpSession session = resultActions.andReturn().getRequest().getSession();
        Company coPrincipal = (Company) session.getAttribute("coPrincipal");

        // verify
        resultActions.andExpect(status().is3xxRedirection());
        assertThat(coPrincipal.getPassword()).isEqualTo(passwordVal);
    }
}
