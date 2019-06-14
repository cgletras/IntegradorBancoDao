package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Leilao  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int idLeilao, duracao;
	private Date dataInicio;
	private double valorInicial, valorAtual, lancePadrao;
	
	private EstadoLeilao estado;
	private Produto produto;
	private User user;
	
	public Leilao() {
	}

	public Leilao(int idLeilao, int duracao, Date dataInicio, double valorInicial, double valorAtual,
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

	public int getIdLeilao() {
		return idLeilao;
	}

	public void setIdLeilao(int idLeilao) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idLeilao;
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
		Leilao other = (Leilao) obj;
		if (idLeilao != other.idLeilao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Leilao [idLeilao=" + idLeilao + ", duracao=" + duracao + ", dataInicio=" + dataInicio
				+ ", valorInicial=" + valorInicial + ", valorAtual=" + valorAtual + ", lancePadrao=" + lancePadrao
				+ ", produto=" + produto + ", user=" + user + "]";
	}

	
}
