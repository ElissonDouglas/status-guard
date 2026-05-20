package com.github.elissondouglas.status_guard.service;

import com.github.elissondouglas.status_guard.entities.Website;
import com.github.elissondouglas.status_guard.entities.enums.Status;
import com.github.elissondouglas.status_guard.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MonitoringService {

    @Autowired
    private WebsiteRepository repository;

    public List<Website> findAll() {
        return repository.findAll();
    }

    @Scheduled(fixedRate = 34000)
    public void verifyWebsite() {
        List<Website> websites = findAll();

        for (Website website : websites) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity responseEntity = restTemplate.getForEntity(website.getUrl(), String.class);
                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    website.setLastStatus(Status.ONLINE);
                    website.setTotalAttempts(0);
                    website.setLastVerification(LocalDateTime.now());
                    repository.save(website);
                }
            } catch (RuntimeException e) {
                website.setTotalAttempts(website.getTotalAttempts() + 1);

                if (website.getTotalAttempts() >= 3) {
                    website.setLastStatus(Status.OFFLINE);
                    website.setLastVerification(LocalDateTime.now());
                }

                repository.save(website);

            }
        }
    }
}
