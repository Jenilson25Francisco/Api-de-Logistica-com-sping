package com.zanguetsuinc.logistica.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zanguetsuinc.logistica.domain.exception.NegocioException;
import com.zanguetsuinc.logistica.domain.model.Cliente;
import com.zanguetsuinc.logistica.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {
	
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente nao encontrado"));
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente com este email!!");
		}
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void Excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}

}
