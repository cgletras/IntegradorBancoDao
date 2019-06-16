package com.leilaodequadrinhos.api.model.entities;

import java.io.Serializable;

public class EstadoProduto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idEstadoProduto;
	private String estado;
	
	public EstadoProduto() {
		
	}

	public EstadoProduto(Integer idEstadoProduto, String estado) {
		super();
		this.idEstadoProduto = idEstadoProduto;
		this.estado = estado;
	}

	public Integer getIdEstadoProduto() {
		return idEstadoProduto;
	}

	public void setIdEstadoProduto(Integer idEstadoProduto) {
		this.idEstadoProduto = idEstadoProduto;
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
		result = prime * result + idEstadoProduto;
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
		EstadoProduto other = (EstadoProduto) obj;
		if (idEstadoProduto != other.idEstadoProduto)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstadoProduto [idEstadoProduto=" + idEstadoProduto + ", estado=" + estado + "]";
	}
	
	
}
