package org.yscha88.game;

/**
 * 가위바위보의 손. 내부 로직은 int 코드(0/1/2)로 처리한다.
 * <pre>
 *   ROCK(0) 이 SCISSORS(2) 를 이기고,
 *   PAPER(1) 이 ROCK(0) 을 이기고,
 *   SCISSORS(2) 가 PAPER(1) 을 이긴다.
 * </pre>
 */
public enum Hand {
    ROCK(0),
    PAPER(1),
    SCISSORS(2);

    private final int code;

    Hand(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

    public static Hand fromCode(int code) {
        for (Hand hand : values()) {
            if (hand.code == code) {
                return hand;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 손 코드: " + code);
    }
}
