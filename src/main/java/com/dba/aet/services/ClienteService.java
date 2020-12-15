package com.dba.aet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dba.aet.DTO.ClienteDTO;
import com.dba.aet.excecoes.Aviso;
import com.dba.aet.repository.ClienteRepository;
import com.dba.aet.repository.DocumentacaoRepository;
import com.dba.aet.validadores.Validar;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	DocumentacaoRepository documentacaoRepository;
	
	@Autowired
	Validar validar;

	public Aviso adicionaCliente(ClienteDTO clienteDTO) {

		try {
			// verificacao
			if (validar.isNull(clienteDTO))
				return new Aviso(400, "Um ou mais campos em branco");

			if (!validar.isPhone(clienteDTO.getTelefone()))
				return new Aviso(400, "Telenfone inválido");
			if (!validar.isValidCpfCnpj(clienteDTO.getCpfCnpj()))
				return new Aviso(400, "Cpf ou Cnpj inválido");
			if (!validar.isValidEmailAddressRegex(clienteDTO.getEmail()))
				return new Aviso(400, "E-mail inválido");
			if (clienteRepository.findByCpfCnpj(clienteDTO.getCpfCnpj()) != null)
				return new Aviso(400, "Cliente já existe");

			clienteRepository.save(clienteDTO.toObject());
			clienteRepository.flush();

			return new Aviso(200, "Ok");
		} catch (Exception e) {
			System.out.println(e);
			return new Aviso(500, "Erro interno do servidor");
		}
	}

}
