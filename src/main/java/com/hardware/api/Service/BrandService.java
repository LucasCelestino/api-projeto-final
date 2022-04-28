package com.hardware.api.Service;

import java.util.List;
import java.util.Optional;

import com.hardware.api.DTO.AdminDTO;
import com.hardware.api.DTO.BrandDTO;
import com.hardware.api.Mapper.AdminMapper;
import com.hardware.api.Mapper.BrandMapper;
import com.hardware.api.Model.Admin;
import com.hardware.api.Model.Brand;
import com.hardware.api.Repository.AdminRepository;
import com.hardware.api.Repository.BrandRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService implements ServiceInterface<BrandDTO>
{

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandMapper brandMapper;

    // @Override
    public List<BrandDTO> findAll()
    {
        return brandMapper.toDTO(brandRepository.findAll());
    }

    // @Override
    public BrandDTO findById(Long id)
    {
        Optional<Brand> optionalBrand = brandRepository.findById(id);

        if(optionalBrand.isPresent())
        {
            return brandMapper.toDTO(optionalBrand.get());
        }

        return null;
    }

    // @Override
    public BrandDTO create(BrandDTO brandDTO)
    {
        Brand newBrand = brandRepository.save(brandMapper.toEntity(brandDTO));

        return brandMapper.toDTO(newBrand);
    }

    // @Override
    public boolean update(BrandDTO brandDTO) 
    {
        if(brandRepository.existsById(brandDTO.getId()))
        {
            brandRepository.save(brandMapper.toEntity(brandDTO));

            return true;
        }

        return false;
    }

    // @Override
    public boolean delete(Long id)
    {
        if(brandRepository.existsById(id))
        {
            brandRepository.deleteById(id);
            
            return true;
        }

        return false;
    }
    
}
