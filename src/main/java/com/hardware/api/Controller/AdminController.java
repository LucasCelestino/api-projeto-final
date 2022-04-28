package com.hardware.api.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.hardware.api.DTO.AdminDTO;
import com.hardware.api.Service.AdminService;

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
@RequestMapping("/api/v1/admins")
public class AdminController implements ControllerInterface<AdminDTO>
{

    @Autowired
    private AdminService adminService; 

    // @Override
    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll());
    }

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
    @PostMapping
    public ResponseEntity<AdminDTO> post(@Valid @RequestBody AdminDTO adminDTO) throws URISyntaxException {
        
        AdminDTO dto = adminService.create(adminDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(dto);
    }

    // @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@Valid @RequestBody AdminDTO dto, @PathVariable("id") Long id)
    {
        AdminDTO adminDTO = adminService.findById(id);

        adminDTO.setName(dto.getName());
        adminDTO.setEmail(dto.getEmail());
        adminDTO.setPassword(dto.getPassword());


        if(adminService.update(adminDTO))
        {
            return ResponseEntity.ok(adminDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)
    {
        if(adminService.delete(id))
        {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
}