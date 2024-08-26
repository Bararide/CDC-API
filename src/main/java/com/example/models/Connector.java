package com.example.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "connector")
@NoArgsConstructor
public class Connector implements Serializable {

    @Id
    private String id;

    private String name;
    private String databaseName;
    private String status;
    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Connector(String name, String databaseName, String status, Date lastUpdated, User user) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.databaseName = databaseName;
        this.status = status;
        this.lastUpdated = lastUpdated;
        this.user = user;
    }
}