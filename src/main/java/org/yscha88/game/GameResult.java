package org.yscha88.game;

/**
 * 한 판의 결과: 플레이어 손, 컴퓨터 손, 플레이어 기준 결과.
 */
public record GameResult(Hand player, Hand computer, Outcome outcome) {
}
