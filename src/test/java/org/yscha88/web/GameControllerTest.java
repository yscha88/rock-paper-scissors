package org.yscha88.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {

    private static final List<String> HANDS = List.of("ROCK", "PAPER", "SCISSORS");
    private static final List<String> OUTCOMES = List.of("WIN", "LOSE", "DRAW");

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 영어_입력이면_player가_ROCK() throws Exception {
        mockMvc.perform(get("/api/play").param("h", "rock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.player", is("ROCK")))
                .andExpect(jsonPath("$.computer", is(in(HANDS))))
                .andExpect(jsonPath("$.outcome", is(in(OUTCOMES))));
    }

    @ParameterizedTest
    @ValueSource(strings = {"바위", "묵", "グー", "石头"})
    void 다국어_HTTP_입력도_ROCK으로_해석된다(String input) throws Exception {
        // CLI 인자와 달리 HTTP 쿼리는 UTF-8로 정상 디코딩됨을 검증
        mockMvc.perform(get("/api/play").param("h", input))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.player", is("ROCK")));
    }

    @Test
    void 알_수_없는_손은_400() throws Exception {
        mockMvc.perform(get("/api/play").param("h", "lizard"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void h_파라미터_누락은_400() throws Exception {
        mockMvc.perform(get("/api/play"))
                .andExpect(status().isBadRequest());
    }
}
