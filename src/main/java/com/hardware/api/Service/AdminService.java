package com.hardware.api.Service;

import java.util.List;
import java.util.Optional;

import com.hardware.api.DTO.AdminDTO;
import com.hardware.api.Mapper.AdminMapper;
import com.hardware.api.Model.Admin;
import com.hardware.api.Repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements ServiceInterface<AdminDTO>
{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    // @Override
    public List<AdminDTO> findAll()
    {
        return adminMapper.toDTO(adminRepository.findAll());
    }

    // @Override
    public AdminDTO findById(Long id)
    {
        Optional<Admin> OptionalAdmin = adminRepository.findById(id);

        if(OptionalAdmin.isPresent())
        {
            return adminMapper.toDTO(OptionalAdmin.get());
        }

        return null;
    }

    // @Override
    public AdminDTO create(AdminDTO adminDTO)
    {
        Admin newAdmin = adminRepository.save(adminMapper.toEntity(adminDTO));

        return adminMapper.toDTO(newAdmin);
    }

    // @Override
    public boolean update(AdminDTO adminDTO) 
    {
        if(adminRepository.existsById(adminDTO.getId()))
        {
            adminRepository.save(adminMapper.toEntity(adminDTO));

            return true;
        }

        return false;
    }

    // @Override
    public boolean delete(Long id)
    {
        if(adminRepository.existsById(id))
        {
            adminRepository.deleteById(id);
            
            return true;
        }

        return false;
    }
    
}
