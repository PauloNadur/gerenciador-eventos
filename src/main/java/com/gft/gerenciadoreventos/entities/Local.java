package com.gft.gerenciadoreventos.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Local {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome não pode ser vazio.")
	private String nome;

	@NotBlank(message = "Nome não pode ser vazio.")
	private String endereco;

	@OneToMany(mappedBy = "local")
	private List<Evento> eventos;

	// TODO create constructor
	public Local() {
	};

	public Local(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Evento> getEvento() {
		return eventos;
	}

	public void setEvento(List<Evento> eventos) {
		this.eventos = eventos;
	}

}
