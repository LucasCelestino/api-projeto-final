package com.hardware.api.Service;

import java.util.List;
import java.util.Optional;

import com.hardware.api.DTO.BrandDTO;
import com.hardware.api.DTO.PartDTO;
import com.hardware.api.Mapper.PartMapper;
import com.hardware.api.Model.Part;
import com.hardware.api.Repository.PartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartService implements ServiceInterface<BrandDTO>
{

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private PartMapper partMapper;

    // @Override
    public List<PartDTO> findAll()
    {
        return partMapper.toDTO(partRepository.findAll());
    }

    // @Override
    public PartDTO findById(Long id)
    {
        Optional<Part> optionalBrand = partRepository.findById(id);

        if(optionalBrand.isPresent())
        {
            return partMapper.toDTO(optionalBrand.get());
        }

        return null;
    }

    // @Override
    public PartDTO create(PartDTO partDTO)
    {
        Part newBrand = partRepository.save(partMapper.toEntity(partDTO));

        return partMapper.toDTO(newBrand);
    }

    // @Override
    public boolean update(PartDTO partDTO) 
    {
        if(partRepository.existsById(partDTO.getId()))
        {
            partRepository.save(partMapper.toEntity(partDTO));

            return true;
        }

        return false;
    }

    // @Override
    public boolean delete(Long id)
    {
        if(partRepository.existsById(id))
        {
            partRepository.deleteById(id);
            
            return true;
        }

        return false;
    }
    
}
