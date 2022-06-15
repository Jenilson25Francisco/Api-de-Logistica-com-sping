package com.zanguetsuinc.logistica.domain.service;

import org.springframework.stereotype.Service;

import com.zanguetsuinc.logistica.domain.exception.EntidadeNaoEncontradaException;
import com.zanguetsuinc.logistica.domain.model.Entrega;
import com.zanguetsuinc.logistica.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega nao encontrada"));
	}
}
