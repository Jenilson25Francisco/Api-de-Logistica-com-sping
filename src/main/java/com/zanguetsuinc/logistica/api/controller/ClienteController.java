package com.zanguetsuinc.logistica.api.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zanguetsuinc.logistica.api.assembler.ClienteAssembler;
import com.zanguetsuinc.logistica.api.model.ClienteModel;
import com.zanguetsuinc.logistica.api.model.input.ClienteInput;
import com.zanguetsuinc.logistica.domain.model.Cliente;
import com.zanguetsuinc.logistica.domain.repository.ClienteRepository;
import com.zanguetsuinc.logistica.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;



@AllArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;
	private ClienteAssembler clienteAssembler;
	
	@GetMapping
	public List<ClienteModel> listar() {
		
		return clienteAssembler.toCollectionModel(clienteRepository.findAll());
		
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteModel> buscar(@PathVariable Long clienteId){
		
		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(clienteAssembler.toModel(cliente)))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel adicionar(@Valid @RequestBody ClienteInput clienteInput) {
		
		Cliente novoCliente = clienteAssembler.toEntity(clienteInput);
		Cliente guardarCliente = catalogoClienteService.salvar(novoCliente);
		
		return clienteAssembler.toModel(guardarCliente);
		
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<ClienteModel> atualizar(@PathVariable Long clienteId, @Valid @RequestBody ClienteInput clienteInput){
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		Cliente atualizandoCliente = clienteAssembler.toEntity(clienteInput);
		atualizandoCliente.setId(clienteId);
		Cliente clienteAtualizado = catalogoClienteService.salvar(atualizandoCliente);
		
		return ResponseEntity.ok(clienteAssembler.toModel(clienteAtualizado));
		
	}
	
	/*@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente){
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = catalogoClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
		
	}*/
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		catalogoClienteService.Excluir(clienteId);
		return ResponseEntity.noContent().build();
		
	}

}
