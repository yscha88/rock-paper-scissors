package org.yscha88.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HandParserTest {

    @ParameterizedTest
    @CsvSource({
            // 영어
            "rock, ROCK", "ROCK, ROCK", "r, ROCK",
            "paper, PAPER", "p, PAPER",
            "scissors, SCISSORS", "s, SCISSORS",
            // 한국어 (가위/바위/보, 묵/찌/빠)
            "바위, ROCK", "묵, ROCK",
            "보, PAPER", "빠, PAPER",
            "가위, SCISSORS", "찌, SCISSORS",
            // 일본어
            "グー, ROCK", "パー, PAPER", "チョキ, SCISSORS",
            // 중국어
            "石头, ROCK", "布, PAPER", "剪刀, SCISSORS"
    })
    void 다국어_입력을_손으로_변환한다(String input, Hand expected) {
        assertEquals(expected, HandParser.parse(input));
    }

    @Test
    void 앞뒤_공백과_대소문자를_무시한다() {
        assertEquals(Hand.ROCK, HandParser.parse("  Rock  "));
    }

    @Test
    void 알_수_없는_입력은_예외() {
        assertThrows(IllegalArgumentException.class, () -> HandParser.parse("lizard"));
    }

    @Test
    void null_입력은_예외() {
        assertThrows(IllegalArgumentException.class, () -> HandParser.parse(null));
    }
}
