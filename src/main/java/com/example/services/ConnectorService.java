package com.example.services;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Connector;
import com.example.models.User;
import com.example.repository.ConnectorRepository;

import jakarta.transaction.Transactional;

@Service
public class ConnectorService {

    @Autowired
    private ConnectorRepository connectorRepository;

    private static final Logger logger = LoggerFactory.getLogger(ConnectorRepository.class);

    public Iterable<Connector> getAll() {
        return connectorRepository.findAll();
    }

    public Optional<Connector> getConnector(String id) {
        return connectorRepository.findById(id);
    }

    @Transactional
    public Boolean createConnector(String name, String database, String status, User user) {
        try {
            Connector connector = new Connector(name, database, status, new Date(), user);
            connectorRepository.save(connector);
            return true;
        } catch (Exception e) {
            logger.error("Error creating connector: {}", e.getMessage());
            return false;
        }
    }

    public boolean deleteConnector(String id) {
        try {
            if (connectorRepository.findById(id).isPresent()) {
                connectorRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error while deleting connector repository " + id);
            return false;
        }
    }
    
}