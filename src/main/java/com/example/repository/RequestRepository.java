// package com.example.repository;

// import java.text.DateFormat;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.example.models.Connector;
// import com.example.models.Request;

// public interface RequestRepository extends JpaRepository<Request, String> {
//     Iterable<Request> findAllByConnectorId(String id);

//     Boolean create(String title, String operation, String status, DateFormat time, String lastUpdated, Connector connector);
// }
