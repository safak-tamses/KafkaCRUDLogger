package com.api.controller;

import com.api.common.response.GenericResponse;
import com.api.model.User;
import com.api.model.UserCreateRequest;
import com.api.model.UserUpdateRequest;
import com.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public UserController(UserService userService, KafkaTemplate<String, String> kafkaTemplate) {
        this.userService = userService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public ResponseEntity<GenericResponse<User>> userCreate(@RequestBody UserCreateRequest createRequest) {
        kafkaTemplate.send("dbCreate", "The user named " + createRequest.name() + " sent a registration request to the database");
        return new ResponseEntity<>(userService.userCreate(createRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<User>> userRead(@PathVariable Long id) {
        kafkaTemplate.send("dbRead", "A data import request was sent to the database");
        return new ResponseEntity<>(userService.userRead(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GenericResponse<User>> userUpdate(@RequestBody UserUpdateRequest updateRequest) {
        kafkaTemplate.send("dbUpdate", "The user named " + updateRequest.name() + " sent a update request to the database");
        return new ResponseEntity<>(userService.userUpdate(updateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<String>> userDelete(@PathVariable Long id) {
        kafkaTemplate.send("dbDelete", "Delete request sent to database");
        return new ResponseEntity<>(userService.userDelete(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<User>>> userGetAll() {
        kafkaTemplate.send("dbRead", "A request was sent to retrieve all data in the database");
        return new ResponseEntity<>(userService.userGetAll(), HttpStatus.OK);
    }

}
