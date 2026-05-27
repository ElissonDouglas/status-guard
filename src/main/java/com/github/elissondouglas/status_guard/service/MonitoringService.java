package com.github.elissondouglas.status_guard.service;

import com.github.elissondouglas.status_guard.entities.Website;
import com.github.elissondouglas.status_guard.entities.enums.Status;
import com.github.elissondouglas.status_guard.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MonitoringService {

    @Value("${app.discord.webhook-url}")
    String discordWebhook;

    @Autowired
    private WebsiteRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Website> findAll() {
        return repository.findAll();
    }

    @Scheduled(fixedRate = 30000)
    public void verifyWebsite() {
        List<Website> websites = findAll();

        for (Website website : websites) {
            try {

                ResponseEntity<String> responseEntity = restTemplate.getForEntity(website.getUrl(), String.class);
                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    handleSuccess(website);
                } else if (responseEntity.getStatusCode().isError()) {
                    handleFailure(website);
                }
            } catch (HttpStatusCodeException e) {
                System.out.println("Website return with error: " + e.getStatusCode());
                handleFailure(website);
            }
        }
    }

    public void handleSuccess(Website website) {
        website.setTotalAttempts(0);
        website.setLastStatus(Status.ONLINE);
        website.setLastVerification(LocalDateTime.now());
        repository.save(website);
    }

    public void handleFailure(Website website) {
        website.setTotalAttempts(website.getTotalAttempts() + 1);
        website.setLastVerification(LocalDateTime.now());

        if (website.getTotalAttempts() >= 3) {
            if (website.getLastStatus() == Status.ONLINE) {
                sendDiscordAlert(website);
            }
            website.setLastStatus(Status.OFFLINE);
        }
        repository.save(website);
    }

    public void sendDiscordAlert(Website website) {
        try {
            Map<String, String> mp = new HashMap<>();
            RestTemplate restTemplate = new RestTemplate();
            mp.put("content", "\uD83D\uDEA8 ATENÇÃO: O serviço " + website.getName() + " está OFFLINE!");
            ResponseEntity<Void> responseEntity = restTemplate.postForEntity(discordWebhook, mp, Void.class);
        } catch (Exception e) {
            System.err.println("Error: Can't send message to Discord. " + e.getMessage());
        }

    }
}
