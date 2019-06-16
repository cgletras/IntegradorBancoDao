package com.leilaodequadrinhos.api.model.entities;

import java.io.Serializable;

public class Personagem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idPersonagem;
	private String nome;
	
	public Personagem() {
		super();
	}

	public Personagem(Integer idPersonagem, String nome) {
		super();
		this.idPersonagem = idPersonagem;
		this.nome = nome;
	}

	public Integer getIdPersonagem() {
		return idPersonagem;
	}

	public void setIdPersonagem(Integer idPersonagem) {
		this.idPersonagem = idPersonagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPersonagem == null) ? 0 : idPersonagem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personagem other = (Personagem) obj;
		if (idPersonagem == null) {
			if (other.idPersonagem != null)
				return false;
		} else if (!idPersonagem.equals(other.idPersonagem))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Personagem [idPersonagem=" + idPersonagem + ", nome=" + nome + "]";
	}
}
