package com.hardware.api.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.hardware.api.DTO.BrandDTO;
import com.hardware.api.DTO.PartDTO;
import com.hardware.api.Mapper.PartMapper;
import com.hardware.api.Model.Part;
import com.hardware.api.Repository.PartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<PartDTO> findAll(Pageable pageable)
    {
		Page<Part> parts = partRepository.findAll(pageable);
		Page<PartDTO> dto = parts.map(new Function<Part, PartDTO>() {
			
			@Override
			public PartDTO apply(Part conta) {
				return partMapper.toDTO(conta);
			}
		});
		return dto;
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
