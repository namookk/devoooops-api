package info.devoooops.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.devoooops.config.EnableMockMvc;
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
}