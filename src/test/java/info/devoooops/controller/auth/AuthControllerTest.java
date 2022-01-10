package info.devoooops.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.devoooops.config.EnableMockMvc;
import info.devoooops.payload.user.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnableMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void beforeEach(){
        objectMapper = new ObjectMapper();
    }

    @DisplayName("로그인 성공 테스트")
    @Test
    void LoginSuccessTest() throws Exception{
        String userId = "test@test.com";
        String password = "test";

        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", userId);
        parameters.put("password", password);

        this.mvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parameters)))
                        .andExpect(status().isOk())
                        .andDo(print())
                        ;
    }

    @DisplayName("회원가입 validation 테스트")
    @Test
    void validationSignUp() throws Exception{
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "test@test.com");
        parameters.put("password", "test");
        parameters.put("name", "테스트");
        parameters.put("nickname", "테스트닉네임");
        parameters.put("birthDate","1994-01-01");
        parameters.put("gender","M");

        //userId 테스트
        parameters.put("userId", "");

        this.mvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parameters)))
                        .andExpect(status().is4xxClientError())
                        .andDo(print())
                        ;

        parameters.put("userId", "test");

        this.mvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parameters)))
                        .andExpect(status().is4xxClientError())
                        .andDo(print())
                        ;

        //password 테스트
        parameters.put("userId", "test@test.com1");
        parameters.put("password", "");

        this.mvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parameters)))
                .andExpect(status().is4xxClientError())
                .andDo(print())
        ;

        //name 테스트
        parameters.put("password", "test");
        parameters.put("name", "");

        this.mvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parameters)))
                .andExpect(status().is4xxClientError())
                .andDo(print())
        ;

        //nickname 테스트
        parameters.put("name", "홍길동");
        parameters.put("nickname", "");

        this.mvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parameters)))
                .andExpect(status().is4xxClientError())
                .andDo(print())
        ;

        //생년월일 테스트
        parameters.put("nickname", "홍길동닉네임");
        parameters.put("birthDate", "19940101");

        this.mvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parameters)))
                .andExpect(status().is4xxClientError())
                .andDo(print())
        ;

        //성별 테스트
        parameters.put("birthDate", "1994-01-01");
        parameters.put("gender", "남");

        this.mvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parameters)))
                .andExpect(status().is4xxClientError())
                .andDo(print())
        ;
    }

    @DisplayName("아이디 중복 체크")
    @Test
    void checkDuplicateUserId() throws Exception{
        this.mvc.perform(get("/auth/check/duplicate")
                        .param("userId", "papakang22@naver.com"))
                        .andExpect(status().isConflict())
                        .andDo(print())
                        ;
    }

    @DisplayName("회원가입 성공 테스트")
    @Test
    void successSignUp() throws Exception{
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "papakang22@naver.com");
        parameters.put("password", "q1w2e3r4");
        parameters.put("name", "황남욱");
        parameters.put("nickname", "나무");
        parameters.put("birthDate","1995-02-17");
        parameters.put("gender","M");

        this.mvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parameters)))
                        .andExpect(status().isOk())
                        .andDo(print())
                        ;
    }
}