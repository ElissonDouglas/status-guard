package com.github.elissondouglas.status_guard.entities;


import com.github.elissondouglas.status_guard.entities.enums.Status;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_website")
public class Website implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String name;

    @Enumerated(EnumType.STRING)
    private Status lastStatus;

    private LocalDateTime lastVerification;

    public Website() {
    }

    public Website(Long id, String url, String name, Status lastStatus, LocalDateTime lastVerification) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.lastStatus = lastStatus;
        this.lastVerification = lastVerification;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(Status lastStatus) {
        this.lastStatus = lastStatus;
    }

    public LocalDateTime getLastVerification() {
        return lastVerification;
    }

    public void setLastVerification(LocalDateTime lastVerification) {
        this.lastVerification = lastVerification;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Website website)) return false;
        return Objects.equals(id, website.id) && Objects.equals(url, website.url) && Objects.equals(name, website.name) && lastStatus == website.lastStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, name, lastStatus);
    }
}
