package com.gft.gerenciadoreventos.entities.enums;

public enum GeneroMusical {

	ROCK("Rock"), TRAP("Trap"), ELETRÔNICA("Eletrônica"), JAZZ("Jazz"), BLUES("Blues"), FUNK("Funk");

	public String descricao;

	GeneroMusical(String descricao) {
		this.descricao = descricao;
	}

	public String obterDescricao() {
		return descricao;
	}
}
