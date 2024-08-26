package com.example.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.components.KafkaSender;
import com.example.models.Connector;
import com.example.models.User;
import com.example.services.ConnectorService;
import com.example.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@RestController
public class ConnectorPresentationController {
    
    @Autowired
    private ConnectorService connectorService;

    @Autowired
    private UserService userService;

    @Autowired
    private KafkaSender kafkaSender;

    private static final Logger logger = LoggerFactory.getLogger(ConnectorPresentationController.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/api/connector")
    public ResponseEntity<ObjectNode> getConnector(String id) {
        try {
            ObjectNode response = mapper.createObjectNode();

            Optional<Connector> connector = connectorService.getConnector(id);

            if(connector.isPresent()) {
                response.put("id", connector.get().getId());
                response.put("name", connector.get().getName());
                response.put("databaseName", connector.get().getDatabaseName());
                response.put("status", connector.get().getStatus());
                response.put("lastUpdated", connector.get().getLastUpdated().toString());
    
                logger.info("get connection with id: " + id);
                return ResponseEntity.ok(response);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("api/connectors/create")
    public ResponseEntity<ObjectNode> createConnector(String name, String database, String status, String id) {
        try {
            ObjectNode response      = mapper.createObjectNode();
            ObjectNode kafkaResponse = mapper.createObjectNode();

            logger.info("Add new connector to user with id: {}", id);
            Optional<User> user = userService.findUserById(id);
    
            if (user.isPresent()) {
                if (connectorService.createConnector(name, database, status, user.get())) {
                    response.put("message", "Connector created successfully");
                    response.put("status", true);
                    response.put("id", id);
                    
                    kafkaResponse.put("user_id",   id);
                    kafkaResponse.put("operation", "create");
                    kafkaResponse.put("name",      name);
                    kafkaResponse.put("database",  database);
                    kafkaResponse.put("name",      name);

                    kafkaSender.sendJsonMessage(kafkaResponse, "test");
                    return ResponseEntity.ok(response);
                } else {
                    response.put("error", "Failed to create connector");
                    response.put("status", false);
                    return ResponseEntity.badRequest().body(response);
                }
            } else {
                response.put("error", "User with id " + id + " not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error creating connector: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("api/connecctors/delete")
    public ResponseEntity<ObjectNode> deleteConnector(String id) {
        try {
            ObjectNode response = mapper.createObjectNode();
            logger.info("Delete connector with id " + id);

            if(connectorService.deleteConnector(id)) {
                response.put("message", "Connector deleted successfully");
                response.put("status", true);
                response.put("id", id);
                response.put("operation", "delete");

                kafkaSender.sendJsonMessage(response, "test");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Connector deleted failed");
                response.put("status", false);
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            logger.error("Error deleting connector: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
