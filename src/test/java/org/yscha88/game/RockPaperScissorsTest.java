package org.yscha88.game;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RockPaperScissorsTest {

    private final RockPaperScissors game = new RockPaperScissors();

    @ParameterizedTest
    @CsvSource({
            // player, computer, expected
            "ROCK, SCISSORS, WIN",
            "PAPER, ROCK, WIN",
            "SCISSORS, PAPER, WIN",
            "ROCK, PAPER, LOSE",
            "PAPER, SCISSORS, LOSE",
            "SCISSORS, ROCK, LOSE",
            "ROCK, ROCK, DRAW",
            "PAPER, PAPER, DRAW",
            "SCISSORS, SCISSORS, DRAW"
    })
    void 아홉_가지_조합을_판정한다(Hand player, Hand computer, Outcome expected) {
        assertEquals(expected, RockPaperScissors.judge(player, computer));
    }

    @Test
    void 입력을_파싱하고_결과는_판정과_일치한다() {
        GameResult result = game.play("바위");
        assertEquals(Hand.ROCK, result.player());
        assertEquals(RockPaperScissors.judge(result.player(), result.computer()), result.outcome());
    }

    @RepeatedTest(50)
    void 컴퓨터_손은_항상_유효하다() {
        GameResult result = game.play(Hand.ROCK);
        assertNotNull(result.computer());
    }

    @Test
    void 보안난수는_세_손을_모두_산출한다() {
        EnumSet<Hand> seen = EnumSet.noneOf(Hand.class);
        for (int i = 0; i < 300 && seen.size() < 3; i++) {
            seen.add(game.play(Hand.ROCK).computer());
        }
        assertEquals(3, seen.size(), "SecureRandom 이 ROCK/PAPER/SCISSORS 를 모두 산출해야 함");
    }
}
