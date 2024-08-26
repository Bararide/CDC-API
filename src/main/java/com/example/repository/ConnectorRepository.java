package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.Connector;

public interface ConnectorRepository extends JpaRepository<Connector, String> {

}
