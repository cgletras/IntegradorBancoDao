package com.leilaodequadrinhos.api.model.entities;

import java.io.Serializable;

public class Writer implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer writerID;
	private String name;
	
	public Writer() {
		super();
	}

	public Writer(Integer writerID, String name) {
		super();
		this.writerID = writerID;
		this.name = name;
	}

	public Integer getWriterID() {
		return writerID;
	}

	public void setWriterID(Integer writerID) {
		this.writerID = writerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((writerID == null) ? 0 : writerID.hashCode());
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
		Writer other = (Writer) obj;
		if (writerID == null) {
			if (other.writerID != null)
				return false;
		} else if (!writerID.equals(other.writerID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Writer [writerID=" + writerID + ", name=" + name + "]";
	}


}
