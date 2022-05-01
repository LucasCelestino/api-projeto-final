package com.hardware.api.Mapper;

import java.util.ArrayList;
import java.util.List;

import com.hardware.api.DTO.PartDTO;
import com.hardware.api.Model.Part;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class PartMapper 
{
    public Part toEntity(PartDTO partDTO)
    {
        Part part = new Part();
        part.setId(partDTO.getId());
        part.setBrand(partDTO.getBrand());
        part.setName(partDTO.getName());
        part.setPrice(partDTO.getPrice());
        part.setUrl(partDTO.getUrl());

        return part;
    }

    public PartDTO toDTO(Part part)
    {
        PartDTO partDTO = new PartDTO();
        partDTO.setId(part.getId());
        partDTO.setBrand(part.getBrand());
        partDTO.setName(part.getName());
        partDTO.setPrice(part.getPrice());
        partDTO.setUrl(part.getUrl());

        return partDTO;
    }

    public List<PartDTO> toDTO(List<Part> list)
    {
		List<PartDTO> lista = new ArrayList<>();

		for (Part p : list) {
			lista.add(this.toDTO(p));
		}

		return lista;
	}
}
