package com.example.springbootpostgres.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
public class TokenCleanUpScheduler {

    @Autowired
    private TokenRepository tokenRepository;

    @Scheduled(fixedRate = 3600000) // run once per hour
    public void deleteExpiredTokens() {

        for (Token token : tokenRepository.findAll()) {
            if (token.isExpired() == true) {
                tokenRepository.delete(token);
            }
        }
    }
}
