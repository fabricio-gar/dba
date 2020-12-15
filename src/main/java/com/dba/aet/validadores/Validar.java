package com.dba.aet.validadores;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dba.aet.DTO.ClienteDTO;
import com.dba.aet.DTO.DocumentacaoDTO;

public class Validar {
	// valida email
	public boolean isValidEmailAddressRegex(String email) {
		boolean isEmailIdValid = false;
		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isEmailIdValid = true;
			}
		}
		return isEmailIdValid;
	}

	// valida cpf
	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	public boolean isValidCpfCnpj(String cpfCnpj) {
		return (isValidCPF(cpfCnpj) || isValidCNPJ(cpfCnpj));
	}

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	private static String padLeft(String text, char character) {
		return String.format("%11s", text).replace(' ', character);
	}

	private static boolean isValidCPF(String cpf) {
		cpf = cpf.trim().replace(".", "").replace("-", "");
		if ((cpf == null) || (cpf.length() != 11))
			return false;

		for (int j = 0; j < 10; j++)
			if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf))
				return false;

		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
	}

	private static boolean isValidCNPJ(String cnpj) {
		cnpj = cnpj.trim().replace(".", "").replace("-", "");
		if ((cnpj == null) || (cnpj.length() != 14))
			return false;

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}

	// validar Cliente
	public boolean isNull(ClienteDTO clienteDTO) {
		if (clienteDTO.getCpfCnpj() == null || clienteDTO.getCpfCnpj().isBlank())
			return true;

		if (clienteDTO.getEmail() == null || clienteDTO.getEmail().isBlank())
			return true;
		if (clienteDTO.getNome() == null || clienteDTO.getNome().isBlank())
			return true;
		if (clienteDTO.getTelefone() == null || clienteDTO.getTelefone().isBlank())
			return true;

		return false;

	}

	// validar Documento
	public boolean isNull(DocumentacaoDTO documentacaoDTO) {

		if (documentacaoDTO.getDocumento().isEmpty())
			return true;

		if (documentacaoDTO.getTipoDocumento() == null || documentacaoDTO.getTipoDocumento().isBlank())
			return true;

		if (documentacaoDTO.getCpfCnpjCliente() == null || documentacaoDTO.getCpfCnpjCliente().isBlank())
			return true;

		return false;

	}

	// validar telefone
	public boolean isPhone(String numero) {
		boolean isPhone = false;
		if (numero != null && numero.length() > 0) {
			String expression = "^[+][5][5][1-9]{2}[2-9]([0-9]{7}|[0-9]{8})$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(numero);
			if (matcher.matches()) {
				isPhone = true;
			}
		}
		return isPhone;
	}

}
