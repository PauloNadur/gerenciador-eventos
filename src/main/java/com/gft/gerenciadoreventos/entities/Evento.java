package com.gft.gerenciadoreventos.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.gft.gerenciadoreventos.entities.enums.GeneroMusical;

@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome não pode ser vazio.")
	private String nome;

	@Min(value = 20L, message = "A capacidade mínima é para 20 pessoas.")
	private Integer capacidade;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;

	@Digits(fraction = 2, integer = 10)
	private BigDecimal preco;

	@ManyToOne
	@NotNull
	private Local local;

	@Enumerated
	@NotNull
	private GeneroMusical generoMusical;

	public Evento() {
	};

	public Evento(String nome, Integer capacidade, Date data, BigDecimal preco, Local local,
			GeneroMusical generoMusical) {
		this.nome = nome;
		this.capacidade = capacidade;
		this.data = data;
		this.preco = preco;
		this.local = local;
		this.generoMusical = generoMusical;
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

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public GeneroMusical getGeneroMusical() {
		return generoMusical;
	}

	public void setGeneroMusical(GeneroMusical generoMusical) {
		this.generoMusical = generoMusical;
	}

}
