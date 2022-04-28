package com.hardware.api.Mapper;

import java.util.ArrayList;
import java.util.List;

import com.hardware.api.DTO.BrandDTO;
import com.hardware.api.Model.Brand;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class BrandMapper 
{
    public Brand toEntity(BrandDTO brandDTO)
    {
        Brand brand = new Brand();
        brand.setId(brandDTO.getId());
        brand.setName(brandDTO.getName());

        return brand;
    }

    public BrandDTO toDTO(Brand brand)
    {
        BrandDTO BrandDTO = new BrandDTO();
        BrandDTO.setId(brand.getId());
        BrandDTO.setName(brand.getName());

        return BrandDTO;
    }

    public List<BrandDTO> toDTO(List<Brand> list)
    {
		List<BrandDTO> lista = new ArrayList<>();

		for (Brand p : list) {
			lista.add(this.toDTO(p));
		}

		return lista;
	}
}
