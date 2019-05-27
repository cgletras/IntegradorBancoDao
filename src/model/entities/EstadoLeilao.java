package model.entities;

import java.io.Serializable;

public class EstadoLeilao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idEstadoLeilao;
	private String estado;
	
	public EstadoLeilao() {
		
	}

	public EstadoLeilao(Integer idEstadoLeilao, String estado) {
		super();
		this.idEstadoLeilao = idEstadoLeilao;
		this.estado = estado;
	}

	public Integer getIdEstadoLeilao() {
		return idEstadoLeilao;
	}

	public void setIdEstadoLeilao(Integer idEstadoLeilao) {
		this.idEstadoLeilao = idEstadoLeilao;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEstadoLeilao == null) ? 0 : idEstadoLeilao.hashCode());
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
		EstadoLeilao other = (EstadoLeilao) obj;
		if (idEstadoLeilao == null) {
			if (other.idEstadoLeilao != null)
				return false;
		} else if (!idEstadoLeilao.equals(other.idEstadoLeilao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstadoLeilao [idEstadoLeilao=" + idEstadoLeilao + ", estado=" + estado + "]";
	}
	
}
