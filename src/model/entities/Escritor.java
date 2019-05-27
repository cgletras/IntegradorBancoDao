package model.entities;

import java.io.Serializable;

public class Escritor implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer idEscritor;
	private String nome;
	
	public Escritor() {
		super();
	}

	public Escritor(Integer idEscritor, String nome) {
		super();
		this.idEscritor = idEscritor;
		this.nome = nome;
	}

	public Integer getIdEscritor() {
		return idEscritor;
	}

	public void setIdEscritor(Integer idEscritor) {
		this.idEscritor = idEscritor;
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
		result = prime * result + ((idEscritor == null) ? 0 : idEscritor.hashCode());
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
		Escritor other = (Escritor) obj;
		if (idEscritor == null) {
			if (other.idEscritor != null)
				return false;
		} else if (!idEscritor.equals(other.idEscritor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Escritor [idEscritor=" + idEscritor + ", nome=" + nome + "]";
	}


}
