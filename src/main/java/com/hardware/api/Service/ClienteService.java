package com.hardware.api.Service;

import java.util.List;
import java.util.Optional;

import com.hardware.api.DTO.ClienteDTO;
import com.hardware.api.Mapper.ClienteMapper;
import com.hardware.api.Model.Clientes;
import com.hardware.api.Repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements ServiceInterface<ClienteDTO>
{

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    // @Override
    public List<ClienteDTO> findAll()
    {
        return clienteMapper.toDTO(clienteRepository.findAll());
    }

    // @Override
    public ClienteDTO findById(Long id)
    {
        Optional<Clientes> OptionalAdmin = clienteRepository.findById(id);

        if(OptionalAdmin.isPresent())
        {
            return clienteMapper.toDTO(OptionalAdmin.get());
        }

        return null;
    }

    // @Override
    public ClienteDTO create(ClienteDTO clienteDTO)
    {
        Clientes newCliente = clienteRepository.save(clienteMapper.toEntity(clienteDTO));

        return clienteMapper.toDTO(newCliente);
    }

    // @Override
    public boolean update(ClienteDTO clienteDTO) 
    {
        if(clienteRepository.existsById(clienteDTO.getId()))
        {
            clienteRepository.save(clienteMapper.toEntity(clienteDTO));

            return true;
        }

        return false;
    }

    // @Override
    public boolean delete(Long id)
    {
        if(clienteRepository.existsById(id))
        {
            clienteRepository.deleteById(id);
            
            return true;
        }

        return false;
    }
    
}
