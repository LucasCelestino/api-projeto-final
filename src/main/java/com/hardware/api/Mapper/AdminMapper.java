package com.hardware.api.Mapper;

import java.util.ArrayList;
import java.util.List;

import com.hardware.api.DTO.AdminDTO;
import com.hardware.api.Model.Admin;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class AdminMapper 
{
    public Admin toEntity(AdminDTO adminDTO)
    {
        Admin admin = new Admin();
        admin.setId(adminDTO.getId());
        admin.setName(adminDTO.getName());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());

        return admin;
    }

    public AdminDTO toDTO(Admin admin)
    {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setName(admin.getName());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setPassword(admin.getPassword());

        return adminDTO;
    }

    public List<AdminDTO> toDTO(List<Admin> list)
    {
		List<AdminDTO> lista = new ArrayList<>();

		for (Admin p : list) {
			lista.add(this.toDTO(p));
		}

		return lista;
	}
}
