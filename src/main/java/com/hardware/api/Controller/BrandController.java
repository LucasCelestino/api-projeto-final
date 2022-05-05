package com.hardware.api.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.hardware.api.DTO.BrandDTO;
import com.hardware.api.Service.BrandService;

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
@RequestMapping("/api/v1/brands")
public class BrandController implements ControllerInterface<BrandDTO>
{

    @Autowired
    private BrandService brandService; 

    // @Override
    @GetMapping
    public ResponseEntity<List<BrandDTO>> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findAll());
    }

    // @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        BrandDTO brandDTO = brandService.findById(id);
        
        if(brandDTO != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(brandDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // @Override
    @PostMapping
    public ResponseEntity<BrandDTO> post(@Valid @RequestBody BrandDTO brandDTO) throws URISyntaxException {
        
        BrandDTO dto = brandService.create(brandDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(dto);
    }

    // @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@Valid @RequestBody BrandDTO dto, @PathVariable("id") Long id)
    {
        BrandDTO brandDTO = brandService.findById(id);

        brandDTO.setName(dto.getName());

        if(brandService.update(brandDTO))
        {
            return ResponseEntity.ok(brandDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)
    {
        if(brandService.delete(id))
        {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
}