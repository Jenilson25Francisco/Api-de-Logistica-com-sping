package com.zanguetsuinc.logistica.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.zanguetsuinc.logistica.api.model.ClienteModel;
import com.zanguetsuinc.logistica.api.model.input.ClienteIdInput;
import com.zanguetsuinc.logistica.api.model.input.ClienteInput;
import com.zanguetsuinc.logistica.domain.model.Cliente;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClienteAssembler {

	private ModelMapper modelMapper;
	
	public ClienteModel toModel(Cliente cliente) {
		
		return modelMapper.map(cliente, ClienteModel.class);
		
	}
	
	public List<ClienteModel> toCollectionModel(List<Cliente> clientes){
		
		return clientes.stream().map(this::toModel)
				.collect(Collectors.toList());
		
	}
	
	public Cliente toEntity(ClienteInput clienteInput) {
		return modelMapper.map(clienteInput, Cliente.class);
	}
	
	public Cliente toEntity(ClienteIdInput clienteIdInput) {
		
		return modelMapper.map(clienteIdInput, Cliente.class);
		
	}
	
}
