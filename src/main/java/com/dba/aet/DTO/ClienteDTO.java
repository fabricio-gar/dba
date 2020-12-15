package com.dba.aet.DTO;

import com.dba.aet.models.Cliente;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ClienteDTO {

	private String nome;
	

	private String email;

	private String telefone;

	private String cpfCnpj;
	
	public Cliente toObject() {
		Cliente cliente = new Cliente();
		cliente.setCpfCnpj(cpfCnpj);
		cliente.setEmail(email);
		cliente.setNome(nome);
		cliente.setTelefone(telefone);
		return cliente;
	}
	
}

