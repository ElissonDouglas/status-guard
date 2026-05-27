package com.github.elissondouglas.status_guard.config;

import com.github.elissondouglas.status_guard.entities.Website;
import com.github.elissondouglas.status_guard.entities.enums.Status;
import com.github.elissondouglas.status_guard.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private WebsiteRepository websiteRepository;


    @Override
    public void run(String... args) throws Exception {
        Website w1 = new Website(null, "https://httpbin.org/status/500", "Httpbin-status-500", Status.UNKNOWN, LocalDateTime.now());

        Website w2 = new Website(null, "https://httpbin.org/status/200", "Httptest-status-200", Status.UNKNOWN, LocalDateTime.now());


        websiteRepository.saveAll(List.of(w1, w2));


    }
}
