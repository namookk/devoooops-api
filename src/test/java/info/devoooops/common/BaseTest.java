package info.devoooops.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@WebAppConfiguration
@TestPropertySource(properties = {"spring.config.location=classpath:application.yml"})
public class BaseTest {

    protected MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                // 적용할 필터를 순서대로 추가해야 한다.
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }
}
