package com.hardware.api.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.hardware.api.DTO.BrandDTO;
import com.hardware.api.DTO.PartDTO;
import com.hardware.api.Service.PartService;

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
@RequestMapping("/api/v1/parts")
public class PartController implements ControllerInterface<BrandDTO>
{

    @Autowired
    private PartService partService; 

    // @Override
    @GetMapping
    public ResponseEntity<List<PartDTO>> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(partService.findAll());
    }

    // @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        PartDTO partDTO = partService.findById(id);
        
        if(partDTO != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(partDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // @Override
    @PostMapping
    public ResponseEntity<PartDTO> post(@Valid @RequestBody PartDTO partDTO) throws URISyntaxException {
        
        PartDTO dto = partService.create(partDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(dto);
    }

    // @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@Valid @RequestBody PartDTO dto, @PathVariable("id") Long id)
    {
        PartDTO partDTO = partService.findById(id);

        partDTO.setName(dto.getName());
        partDTO.setPrice(dto.getPrice());
        partDTO.setUrl(dto.getUrl());
        partDTO.setBrand(dto.getBrand());

        if(partService.update(partDTO))
        {
            return ResponseEntity.ok(partDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)
    {
        if(partService.delete(id))
        {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
}
