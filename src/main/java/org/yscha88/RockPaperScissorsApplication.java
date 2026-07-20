package org.yscha88;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.yscha88.game.RockPaperScissors;

@SpringBootApplication
public class RockPaperScissorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RockPaperScissorsApplication.class, args);
    }

    /**
     * 게임 로직을 스프링 빈으로 등록한다. 도메인 클래스는 프레임워크에 의존하지 않도록
     * 애너테이션 대신 @Bean 으로 노출한다.
     */
    @Bean
    public RockPaperScissors rockPaperScissors() {
        return new RockPaperScissors();
    }
}
