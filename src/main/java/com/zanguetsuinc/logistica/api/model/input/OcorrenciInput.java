package com.zanguetsuinc.logistica.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciInput {
	
	@NotBlank
	private String descricao;

}
