package com.hardware.api.Controller;

import java.net.URISyntaxException;
import java.util.List;

import com.hardware.api.DTO.AdminDTO;
import com.hardware.api.Service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminController implements ControllerInterface<AdminDTO>
{

    @Autowired
    private AdminService adminService; 

    // @Override
    // @GetMapping
    // public ResponseEntity<List<User>> getAll()
    // {
    //     return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    // }

    // @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        AdminDTO adminDTO = adminService.findById(id);
        
        if(adminDTO != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(adminDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

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
