package com.zanguetsuinc.logistica.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteModel {
	
	private Long id;
	private String nomeDoCliente;
	private String email;
	private String telefone;

}
