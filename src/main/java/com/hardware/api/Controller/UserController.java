package com.hardware.api.Controller;

import java.net.URISyntaxException;
import java.util.List;

import com.hardware.api.DTO.UserDTO;
import com.hardware.api.Model.User;
import com.hardware.api.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController implements ControllerInterface<User>
{

    @Autowired
    private UserService userService; 

    // @Override
    @GetMapping
    public ResponseEntity<List<User>> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    // @Override
    // public ResponseEntity<?> get(Long id) {
        
    //     return null;
    // }

    // @Override
    // public ResponseEntity<User> post(User obj) throws URISyntaxException {
        
    //     return null;
    // }

    // @Override
    // public ResponseEntity<?> put(User obj) {
        
    //     return null;
    // }

    // @Override
    // public ResponseEntity<?> delete(Long id) {
        
    //     return null;
    // }
    
}
