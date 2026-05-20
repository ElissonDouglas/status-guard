package com.github.elissondouglas.status_guard.config;

import com.github.elissondouglas.status_guard.entities.Website;
import com.github.elissondouglas.status_guard.entities.enums.Status;
import com.github.elissondouglas.status_guard.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private WebsiteRepository websiteRepository;


    @Override
    public void run(String... args) throws Exception {
        Website w1 = new Website(null, "https://www.google.com/", "Google", Status.OFFLINE, LocalDateTime.now());


        websiteRepository.save(w1);


    }
}
