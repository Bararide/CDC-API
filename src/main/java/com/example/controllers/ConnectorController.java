package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Connector;
import com.example.services.ConnectorService;

@RestController
public class ConnectorController {

    @Autowired
    private ConnectorService connectorService;

    private static final Logger logger = LoggerFactory.getLogger(ConnectorController.class);

    @GetMapping("/api/connectors")
    public ResponseEntity<Iterable<Connector>> getAll() {
        try {
            logger.info("get all connectors");
            Iterable<Connector> conns = connectorService.getAll();

            for (Connector conn : conns) {
                System.out.println(conn);
            }
            return ResponseEntity.ok(conns);
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}