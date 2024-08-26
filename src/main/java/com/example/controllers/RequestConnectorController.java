// package com.example.controllers;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.models.Request;
// import com.example.services.RequestConnectorService;

// @RestController
// public class RequestConnectorController {

//     @Autowired
//     private RequestConnectorService requestConnectorService;

//     private static final Logger logger = LoggerFactory.getLogger(RequestConnectorService.class);

//     @GetMapping("api/connector/requests")
//     public Iterable<Request> getRequests(String id) {
//         try {
//             logger.info("get all requests by id: {}", id);
//             return requestConnectorService.getAllRequests(id);
//         }
//         catch (Exception e) {
//             logger.error(id, e);
//             return null;
//         }
//     }
// }
