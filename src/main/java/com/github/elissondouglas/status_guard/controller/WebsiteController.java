package com.github.elissondouglas.status_guard.controller;

import com.github.elissondouglas.status_guard.entities.Website;
import com.github.elissondouglas.status_guard.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/websites")
public class WebsiteController {

    @Autowired
    private MonitoringService service;

    @GetMapping
    public ResponseEntity<List<Website>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }


}
