package com.dba.aet.excecoes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
public class Aviso {

	private Integer codigoRetorno;
	private String mensagem;

	public Aviso(Integer codigoRetorno, String mensagem) {
		this.codigoRetorno = codigoRetorno;
		this.mensagem = mensagem;
	}
}
