package com.dba.aet.controllers;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dba.aet.DTO.ClienteDTO;
import com.dba.aet.DTO.DocumentacaoDTO;
import com.dba.aet.excecoes.Aviso;
import com.dba.aet.models.Documentacao;
import com.dba.aet.services.ClienteService;
import com.dba.aet.services.DocumentacaoService;

import jdk.jfr.ContentType;

@RestController
@RequestMapping(value = "/cadastro")
public class CadastroController {

	@Autowired
	ClienteService clienteService;

	@Autowired
	DocumentacaoService documentacaoService;

	@PostMapping("/addCliente")
	public Aviso adicionaCliente(@RequestBody ClienteDTO clienteDTO) {
		Aviso aviso = clienteService.adicionaCliente(clienteDTO);

		return aviso;
	}

	@PostMapping("/addDocumentacao")
	public Aviso adicionaDocumento(@RequestParam String cpfCnpj, @RequestParam String tipo,
			@RequestParam MultipartFile file) {

		Aviso aviso = documentacaoService.adicionaDocumento(new DocumentacaoDTO(cpfCnpj, tipo, file));

		return aviso;
	}

}
