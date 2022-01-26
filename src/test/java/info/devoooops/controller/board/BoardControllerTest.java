package info.devoooops.controller.board;


import info.devoooops.common.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class BoardControllerTest extends BaseTest {

    @Test
    void 게시판_전체_조회() throws Exception{

        super.mockMvc.perform(get("/boards")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print()).andExpect(MockMvcResultMatchers.status().isOk());
    }
}