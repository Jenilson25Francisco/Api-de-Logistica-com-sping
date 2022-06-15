package com.zanguetsuinc.logistica.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.zanguetsuinc.logistica.api.model.ClienteModel;
import com.zanguetsuinc.logistica.api.model.input.ClienteIdInput;
import com.zanguetsuinc.logistica.domain.model.Cliente;

public class ClienteAssembler {

	private ModelMapper modelMapper;
	
	public ClienteModel toModel(Cliente cliente) {
		
		return modelMapper.map(cliente, ClienteModel.class);
		
	}
	
	public List<ClienteModel> toCollectionModel(List<Cliente> clientes){
		
		return clientes.stream().map(this::toModel)
				.collect(Collectors.toList());
		
	}
	
	public Cliente toEntity(ClienteIdInput clienteIdInput) {
		
		return modelMapper.map(clienteIdInput, Cliente.class);
		
	}
	
}
