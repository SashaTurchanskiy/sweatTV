package com.sweatTV.controller;

import com.sweatTV.dto.UserDTO;
import com.sweatTV.dto.request.RegisterUserRequest;
import com.sweatTV.dto.response.MessageResponse;
import com.sweatTV.entity.User;
import com.sweatTV.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id){
         UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> user = userService.getAllUsers();
        return ResponseEntity.ok(user);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<MessageResponse> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        MessageResponse response = userService.updateUserProfile(id, userDTO);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable Long id){
        MessageResponse response = userService.deleteUser(id);
        return ResponseEntity.ok(response);
    }
}
