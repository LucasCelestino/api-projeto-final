package com.hardware.api.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.hardware.api.DTO.ClienteDTO;
import com.hardware.api.Service.ClienteService;

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
@RequestMapping("/api/v1/clientes")
public class ClienteController implements ControllerInterface<ClienteDTO>
{

    @Autowired
    private ClienteService clienteService; 

    // @Override
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }

    // @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        ClienteDTO clienteDTO = clienteService.findById(id);
        
        if(clienteDTO != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(clienteDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // @Override
    @PostMapping
    public ResponseEntity<ClienteDTO> post(@Valid @RequestBody ClienteDTO clienteDTO) throws URISyntaxException {
        
        ClienteDTO dto = clienteService.create(clienteDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(dto);
    }

    // @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@Valid @RequestBody ClienteDTO dto, @PathVariable("id") Long id)
    {
        ClienteDTO clienteDTO = clienteService.findById(id);

        clienteDTO.setName(dto.getName());
        clienteDTO.setEmail(dto.getEmail());
        clienteDTO.setPassword(dto.getPassword());


        if(clienteService.update(clienteDTO))
        {
            return ResponseEntity.ok(clienteDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)
    {
        if(clienteService.delete(id))
        {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
}