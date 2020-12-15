package com.dba.aet.DTO;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.dba.aet.models.Documentacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class DocumentacaoDTO {

	private String cpfCnpjCliente;

	private String tipoDocumento;

	private MultipartFile documento;

	public Documentacao toObject() throws IOException {

		Documentacao documentacao = new Documentacao();
		documentacao.setTipoDocumento(tipoDocumento);

		documentacao.setDocumento(toObjects(documento.getBytes()));
		return documentacao;
	}

	Byte[] toObjects(byte[] bytesPrim) {

		Byte[] bytes = new Byte[bytesPrim.length];
		int i = 0;
		for (byte b : bytesPrim)
			bytes[i++] = b;
		return bytes;

	}

}
