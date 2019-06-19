package com.leilaodequadrinhos.api.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Leilao  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int duracao;
	private Long idLeilao;
	private Date dataInicio;
	private double valorInicial, valorAtual, lancePadrao;
	
	private EstadoLeilao estado;
	private Produto produto;
	private User user;
	
	public Leilao() {
	}

	public Leilao(Long idLeilao, int duracao, Date dataInicio, double valorInicial, double valorAtual,
				  double lancePadrao, EstadoLeilao estado, Produto produto, User user) {
		super();
		this.idLeilao = idLeilao;
		this.duracao = duracao;
		this.dataInicio = dataInicio;
		this.valorInicial = valorInicial;
		this.valorAtual = valorAtual;
		this.lancePadrao = lancePadrao;
		this.estado = estado;
		this.produto = produto;
		this.user = user;
	}

	public EstadoLeilao getEstado() {
		return estado;
	}

	public void setEstado(EstadoLeilao estado) {
		this.estado = estado;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getIdLeilao() {
		return idLeilao;
	}

	public void setIdLeilao(Long idLeilao) {
		this.idLeilao = idLeilao;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public double getValorInicial() {
	return valorInicial;
	}

	public void setValorInicial(double valorInicial) {
		this.valorInicial = valorInicial;
	}

	public double getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(double valorAtual) {
		this.valorAtual = valorAtual;
	}

	public double getLancePadrao() {
		return lancePadrao;
	}

	public void setLancePadrao(double lancePadrao) {
		this.lancePadrao = lancePadrao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Leilao leilao = (Leilao) o;
		return idLeilao.equals(leilao.idLeilao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idLeilao);
	}

	@Override
	public String toString() {
		return "Leilao [idLeilao=" + idLeilao + ", duracao=" + duracao + ", dataInicio=" + dataInicio
				+ ", valorInicial=" + valorInicial + ", valorAtual=" + valorAtual + ", lancePadrao=" + lancePadrao
				+ ", produto=" + produto + ", user=" + user + "]";
	}

	
}
