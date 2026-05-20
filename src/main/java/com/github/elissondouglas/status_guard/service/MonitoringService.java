package com.github.elissondouglas.status_guard.service;

import com.github.elissondouglas.status_guard.entities.Website;
import com.github.elissondouglas.status_guard.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitoringService {

    @Autowired
    private WebsiteRepository repository;

    public List<Website> findAll() {
        return repository.findAll();
    }

    @Scheduled(fixedRate = 68000)
    public void verifyWebsite() {
        List<Website> websites = findAll();

        for (Website website : websites) {
            System.out.println(website.getUrl());
        }
    }
}
