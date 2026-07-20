package org.yscha88.game;

import java.util.Locale;
import java.util.Map;

/**
 * 다국어 입력 문자열을 {@link Hand} 로 변환한다.
 * 한국어(가위/바위/보, 묵/찌/빠), 영어, 일본어, 중국어 및 축약(r/p/s)을 지원한다.
 * 별칭 맵에 항목을 추가하면 언어를 쉽게 확장할 수 있다.
 */
public final class HandParser {

    private static final Map<String, Hand> ALIASES = Map.ofEntries(
            // ROCK
            Map.entry("rock", Hand.ROCK),
            Map.entry("r", Hand.ROCK),
            Map.entry("바위", Hand.ROCK),
            Map.entry("묵", Hand.ROCK),
            Map.entry("グー", Hand.ROCK),
            Map.entry("石头", Hand.ROCK),
            // PAPER
            Map.entry("paper", Hand.PAPER),
            Map.entry("p", Hand.PAPER),
            Map.entry("보", Hand.PAPER),
            Map.entry("빠", Hand.PAPER),
            Map.entry("パー", Hand.PAPER),
            Map.entry("布", Hand.PAPER),
            // SCISSORS
            Map.entry("scissors", Hand.SCISSORS),
            Map.entry("scissor", Hand.SCISSORS),
            Map.entry("s", Hand.SCISSORS),
            Map.entry("가위", Hand.SCISSORS),
            Map.entry("찌", Hand.SCISSORS),
            Map.entry("チョキ", Hand.SCISSORS),
            Map.entry("剪刀", Hand.SCISSORS)
    );

    private HandParser() {
    }

    /**
     * 입력 문자열을 손으로 변환한다. 앞뒤 공백과 영문 대소문자를 무시한다.
     *
     * @throws IllegalArgumentException 입력이 null 이거나 알 수 없는 값일 때
     */
    public static Hand parse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력이 null 입니다.");
        }
        String key = input.strip().toLowerCase(Locale.ROOT);
        Hand hand = ALIASES.get(key);
        if (hand == null) {
            throw new IllegalArgumentException("알 수 없는 손 입력: " + input);
        }
        return hand;
    }
}
