package com.dba.aet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dba.aet.DTO.DocumentacaoDTO;

import com.dba.aet.excecoes.Aviso;
import com.dba.aet.models.Documentacao;
import com.dba.aet.repository.ClienteRepository;
import com.dba.aet.repository.DocumentacaoRepository;
import com.dba.aet.validadores.Validar;

@Service
public class DocumentacaoService {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	DocumentacaoRepository documentacaoRepository;

	Validar validar = new Validar();

	public Aviso adicionaDocumento(DocumentacaoDTO documentacaoDTO) {
		try {

			if (validar.isNull(documentacaoDTO))
				return new Aviso(400, "Documento em branco");

			if (!validar.isValidCpfCnpj(documentacaoDTO.getCpfCnpjCliente()))
				return new Aviso(400, "Cpf ou Cnpj inválido");

			if (!documentacaoDTO.getDocumento().getContentType().equals("application/pdf"))
				return new Aviso(400, "Formato de documento inválido");

			if (clienteRepository.findByCpfCnpj(documentacaoDTO.getCpfCnpjCliente()) == null)
				return new Aviso(400, "Cliente inexistente");

			Documentacao documentacao = documentacaoDTO.toObject();
			documentacao.setCliente(clienteRepository.findByCpfCnpj(documentacaoDTO.getCpfCnpjCliente()));
			documentacaoRepository.save(documentacao);

			documentacaoRepository.flush();

			return new Aviso(200, "Ok");
		} catch (Exception e) {
			System.out.println(e);
			return new Aviso(500, "Erro interno do servidor");
		}
	}

}
