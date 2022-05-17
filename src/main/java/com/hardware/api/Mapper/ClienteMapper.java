package com.hardware.api.Mapper;

import java.util.ArrayList;
import java.util.List;
import com.hardware.api.DTO.ClienteDTO;
import com.hardware.api.Model.Clientes;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class ClienteMapper 
{
    public Clientes toEntity(ClienteDTO clienteDTO)
    {
        Clientes cliente = new Clientes();
        cliente.setId(clienteDTO.getId());
        cliente.setName(clienteDTO.getName());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setPassword(clienteDTO.getPassword());
        cliente.setPhone(clienteDTO.getPhone());
        cliente.setUrl(clienteDTO.getUrl());

        return cliente;
    }

    public ClienteDTO toDTO(Clientes cliente)
    {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setName(cliente.getName());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setPassword(cliente.getPassword());
        clienteDTO.setPhone(cliente.getPhone());
        clienteDTO.setUrl(cliente.getUrl());

        return clienteDTO;
    }

    public List<ClienteDTO> toDTO(List<Clientes> list)
    {
		List<ClienteDTO> lista = new ArrayList<>();

		for (Clientes p : list) {
			lista.add(this.toDTO(p));
		}

		return lista;
	}
}
