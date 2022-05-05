package com.hardware.api.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.hardware.api.DTO.UserDTO;
import com.hardware.api.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/users")
public class UserController implements ControllerInterface<UserDTO>
{

    @Autowired
    private UserService userService; 

    @Override
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        UserDTO userDTO = userService.findById(id);
        
        if(userDTO != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    @PostMapping
    public ResponseEntity<UserDTO> post(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        
        UserDTO dto = userService.create(userDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@Valid @RequestBody UserDTO dto, @PathVariable("id") Long id)
    {
        UserDTO userDTO = userService.findById(id);

        userDTO.setName(dto.getName());
        userDTO.setEmail(dto.getEmail());
        userDTO.setPassword(dto.getPassword());
        userDTO.setPhone(dto.getPhone());
        userDTO.setUrl(dto.getUrl());

        if(userService.update(userDTO))
        {
            return ResponseEntity.ok(userDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)
    {
        if(userService.delete(id))
        {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
}
