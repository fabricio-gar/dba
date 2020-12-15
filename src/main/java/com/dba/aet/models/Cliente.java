package com.dba.aet.models;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	@NotNull
	private Long id;
	
	@Column(name = "nome_completo", length = 50)
	@NotNull
	private String nome;
	
	@Column(length = 500)
	@NotNull
	private String email;
	
	@Column(length = 13)
	@NotNull
	private String telefone;
	
	@Column(name = "cpf_cnpj", length = 14, unique = true)
	@NotNull
	private String cpfCnpj;
	
	@OneToMany(mappedBy = "cliente")
	private List<Documentacao> documentacao;
	
	
	
	
	

	
	
	
	
}
