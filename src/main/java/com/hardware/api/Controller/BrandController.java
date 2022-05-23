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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController implements ControllerInterface<BrandDTO>
{

    @Autowired
    private BrandService brandService; 

    @Override
    @GetMapping(produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Retorna uma lista com todas as marcas")
    @Operation(summary = "Retorna uma lista de marcas")
    public ResponseEntity<List<BrandDTO>> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.findAll());
    }

    @Override
    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna uma marca única pelo id"),
        @ApiResponse(responseCode = "401", description = "Você não está autenticado"),
        @ApiResponse(responseCode = "403", description = "Você não tem permissão para executar essa ação"),
        @ApiResponse(responseCode = "404", description = "Id informado não encontrado")
    })
    @Operation(summary = "Retorna uma marca única")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        BrandDTO brandDTO = brandService.findById(id);
        
        if(brandDTO != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(brandDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cria uma marca"),
        @ApiResponse(responseCode = "401", description = "Você não está autenticado"),
        @ApiResponse(responseCode = "403", description = "Você não tem permissão para executar essa ação")
    })
    @Operation(summary = "Cria uma marca")
    public ResponseEntity<BrandDTO> post(@Valid @RequestBody BrandDTO brandDTO) throws URISyntaxException {
        
        BrandDTO dto = brandService.create(brandDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(dto);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Atualiza e retorna a marca atualizada"),
        @ApiResponse(responseCode = "401", description = "Você não está autenticado"),
        @ApiResponse(responseCode = "403", description = "Você não tem permissão para executar essa ação"),
        @ApiResponse(responseCode = "404", description = "Id informado não encontrado")
    })
    @Operation(summary = "Atualiza uma marca única")
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

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Deleta uma marca única pelo id"),
        @ApiResponse(responseCode = "401", description = "Você não está autenticado"),
        @ApiResponse(responseCode = "403", description = "Você não tem permissão para executar essa ação"),
        @ApiResponse(responseCode = "404", description = "Id informado não encontrado")
    })
    @Operation(summary = "Deleta uma marca única")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)
    {
        if(brandService.delete(id))
        {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
}