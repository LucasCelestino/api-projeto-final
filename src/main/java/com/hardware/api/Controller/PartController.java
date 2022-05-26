package com.hardware.api.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import com.hardware.api.DTO.BrandDTO;
import com.hardware.api.DTO.PartDTO;
import com.hardware.api.Model.Part;
import com.hardware.api.Service.BrandService;
import com.hardware.api.Service.PartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/api/v1/parts")
public class PartController implements ControllerInterface<PartDTO>
{

    @Autowired
    private PartService partService;

    @Autowired
    private BrandService brandService;

    @Override
    @GetMapping(produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Retorna uma lista com todos as peças")
    @Operation(summary = "Retorna uma lista de peças")
    public ResponseEntity<List<PartDTO>> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(partService.findAll());
    }

    @GetMapping(value = "/page", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "Retorna uma lista com todos as peças paginadas")
    @Operation(summary = "Retorna uma lista de peças paginadas")
	public ResponseEntity<Page<PartDTO>> getAll(Pageable pageable)
    {
		return ResponseEntity.ok(partService.findAll(pageable));
	}

    @Override
    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna uma peça única pelo id"),
        @ApiResponse(responseCode = "401", description = "Você não está autenticado"),
        @ApiResponse(responseCode = "403", description = "Você não tem permissão para executar essa ação"),
        @ApiResponse(responseCode = "404", description = "Id informado não encontrado")
    })
    @Operation(summary = "Retorna uma peça única")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        PartDTO partDTO = partService.findById(id);
        
        if(partDTO != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(partDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = "/brands/{brand}", produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna todas as peças de uma marca específica"),
        @ApiResponse(responseCode = "401", description = "Você não está autenticado"),
        @ApiResponse(responseCode = "403", description = "Você não tem permissão para executar essa ação"),
        @ApiResponse(responseCode = "404", description = "Marca informada não encontrada")
    })
    @Operation(summary = "Retorna todas as peças de uma marca específica")
    public ResponseEntity<?> getByBrand(@PathVariable("brand") String brand) {
        
        BrandDTO brandDTO = brandService.findByName(brand);

        if(brandDTO != null)
        {
            Stream<Part> parts = partService.findByBrandName(brandDTO.getId());

            if(parts != null)
            {
                return ResponseEntity.ok(parts);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cria uma peça"),
        @ApiResponse(responseCode = "401", description = "Você não está autenticado"),
        @ApiResponse(responseCode = "403", description = "Você não tem permissão para executar essa ação")
    })
    @Operation(summary = "Cria uma peça")
    public ResponseEntity<PartDTO> post(@Valid @RequestBody PartDTO partDTO) throws URISyntaxException {
        
        PartDTO dto = partService.create(partDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(dto);
    }


    @PutMapping(value = "/{id}", produces = "application/json")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Atualiza e retorna a peça atualizada"),
        @ApiResponse(responseCode = "401", description = "Você não está autenticado"),
        @ApiResponse(responseCode = "403", description = "Você não tem permissão para executar essa ação"),
        @ApiResponse(responseCode = "404", description = "Id informado não encontrado")
    })
    @Operation(summary = "Atualiza uma peça única")
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

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Deleta uma peça única pelo id"),
        @ApiResponse(responseCode = "401", description = "Você não está autenticado"),
        @ApiResponse(responseCode = "403", description = "Você não tem permissão para executar essa ação"),
        @ApiResponse(responseCode = "404", description = "Id informado não encontrado")
    })
    @Operation(summary = "Deleta uma peça única")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)
    {
        if(partService.delete(id))
        {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
}
