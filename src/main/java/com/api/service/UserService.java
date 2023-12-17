package com.api.service;

import com.api.common.response.GenericResponse;
import com.api.error.UserCreationException;
import com.api.error.UserReadException;
import com.api.error.UserUpdateException;
import com.api.model.User;
import com.api.model.UserCreateRequest;
import com.api.model.UserUpdateRequest;
import com.api.repository.jpa.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;


    public UserService(UserRepository userRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public GenericResponse<User> userCreate(UserCreateRequest request) {
        try {
            User user = new User(request.name(), request.surname(), request.email());
            userRepository.save(user);
            kafkaTemplate.send("dbCreate", "The user named " + request.name() + " has been successfully registered in the database");
            return new GenericResponse<>(user, Boolean.TRUE);
        } catch (Exception e) {
            kafkaTemplate.send("dbCreate", "Failed to register the user named " + request.name() + " in the database. Reason: " + e.getMessage());
            throw new UserCreationException();
        }
    }

    public GenericResponse<User> userRead(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(UserReadException::new);
            kafkaTemplate.send("dbRead", "The user named " + user.getName() + " has been successfully read in the database");
            return new GenericResponse<>(user, Boolean.TRUE);
        } catch (Exception e) {
            kafkaTemplate.send("dbCreate", "Failed to read in the database. Reason: " + e.getMessage());
            throw new UserReadException();
        }
    }

    public GenericResponse<User> userUpdate(UserUpdateRequest request) {
        try {
            User user = userRepository.findById(request.id()).orElseThrow(UserUpdateException::new);
            user.setName(request.name());
            user.setSurname(request.surname());
            user.setEmail(request.email());
            userRepository.save(user);
            kafkaTemplate.send("dbUpdate", "The user named " + request.name() + " has been successfully updated in the database");
            return new GenericResponse<>(user, Boolean.TRUE);
        } catch (Exception e) {
            kafkaTemplate.send("dbUpdate", "Failed to update the user named " + request.name() + " in the database. Reason: " + e.getMessage());
            throw new UserUpdateException();
        }
    }

    public GenericResponse<String> userDelete(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(UserReadException::new);
            userRepository.delete(user);
            kafkaTemplate.send("dbDelete", "The user named " + user.getName() + " has been successfully deleted in the database");
            return new GenericResponse<>("Successfull", Boolean.TRUE);
        } catch (Exception e) {
            kafkaTemplate.send("dbDelete", "Failed to delete in the database. Reason: " + e.getMessage());
            throw new UserReadException();
        }
    }

    public GenericResponse<List<User>> userGetAll() {
        try {
            List<User> userList = userRepository.findAll();
            kafkaTemplate.send("dbRead", "All data in the database was retrieved successfully");
            return new GenericResponse<>(userList, Boolean.TRUE);
        } catch (Exception e) {
            throw new RuntimeException("Database error" + e.getMessage());
        }
    }

}
