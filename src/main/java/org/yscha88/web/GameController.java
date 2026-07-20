package org.yscha88.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yscha88.game.GameResult;
import org.yscha88.game.RockPaperScissors;

/**
 * 가위바위보 REST 컨트롤러.
 * <pre>
 *   GET /api/play?h=rock
 *   → {"player":"ROCK","computer":"SCISSORS","outcome":"WIN"}
 * </pre>
 * h 파라미터는 다국어를 허용한다(rock/바위/묵/グー/石头 ...).
 */
@RestController
public class GameController {

    private final RockPaperScissors game;

    public GameController(RockPaperScissors game) {
        this.game = game;
    }

    @GetMapping("/api/play")
    public GameResult play(@RequestParam("h") String hand) {
        return game.play(hand);
    }
}
