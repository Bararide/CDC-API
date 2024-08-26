package com.example.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

// @Data
// @Entity
// @Table(name="request")
// @AllArgsConstructor
// @NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
// @Getter
// @Setter
// @ToString(includeFieldNames=true)
// public class Request implements Serializable {
    
//     @Id
//     private @NonNull String id;

//     private @NonNull String title;
//     private @NonNull String operation;
//     private @NonNull String status;
//     private @NonNull String time;
//     private @NonNull String lastUpdated;

//     @ManyToOne
//     @JoinColumn(name="connector_id", referencedColumnName="id")
//     private @NonNull Connector connector;

//     public Request(String title, String operation, String status, String time, String lastUpdated, Connector connector) {
//         this.id = UUID.randomUUID().toString();
//         this.title = title;
//         this.operation = operation;
//         this.status = status;
//         this.time = time;
//         this.lastUpdated = lastUpdated;
//         this.connector = connector;
//     }
// }
