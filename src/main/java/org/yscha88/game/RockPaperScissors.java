package org.yscha88.game;

import java.security.SecureRandom;

/**
 * 가위바위보 게임 로직.
 * 컴퓨터 손은 보안 난수(SecureRandom)로 산출하며, 플레이어 입력과 비교해 결과를 돌려준다.
 */
public class RockPaperScissors {

    private final SecureRandom random = new SecureRandom();

    /**
     * 다국어 입력을 받아 한 판을 진행한다.
     *
     * @param userInput 예: "rock", "바위", "묵", "グー", "石头"
     */
    public GameResult play(String userInput) {
        return play(HandParser.parse(userInput));
    }

    /**
     * 플레이어 손이 정해진 상태로 한 판을 진행한다.
     */
    public GameResult play(Hand player) {
        Hand computer = randomHand();
        return new GameResult(player, computer, judge(player, computer));
    }

    /**
     * 보안 난수로 컴퓨터 손을 산출한다. (요구사항: 일반 Random 이 아닌 SecureRandom)
     */
    private Hand randomHand() {
        return Hand.fromCode(random.nextInt(3));
    }

    /**
     * 플레이어 기준 승패 판정. int 코드 모듈러 연산으로 계산한다.
     * <pre>
     *   diff = (player - computer + 3) % 3
     *   0 → DRAW, 1 → WIN, 2 → LOSE
     * </pre>
     */
    static Outcome judge(Hand player, Hand computer) {
        int diff = (player.code() - computer.code() + 3) % 3;
        return switch (diff) {
            case 0 -> Outcome.DRAW;
            case 1 -> Outcome.WIN;
            default -> Outcome.LOSE;
        };
    }
}
