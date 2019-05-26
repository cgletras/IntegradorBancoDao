package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Leilao  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int idLeilao, duracao;
	private Date dataInicio, dataFim;
	private double valorInicial, valorAtual, lancePadrao;
	
	private Produto produto;
	private Usuario usuario;
	
	public Leilao() {
	}

	public Leilao(int idLeilao, int duracao, Date dataInicio, Date dataFim, double valorInicial, double valorAtual,
			double lancePadrao, Produto produto, Usuario usuario) {
		super();
		this.idLeilao = idLeilao;
		this.duracao = duracao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.valorInicial = valorInicial;
		this.valorAtual = valorAtual;
		this.lancePadrao = lancePadrao;
		this.produto = produto;
		this.usuario = usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
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
		return "Leilao [idLeilao=" + idLeilao + ", duracao=" + duracao + ", dataInicio=" + dataInicio + ", dataFim="
				+ dataFim + ", valorInicial=" + valorInicial + ", valorAtual=" + valorAtual + ", lancePadrao="
				+ lancePadrao + ", produto=" + produto + ", usuario=" + usuario + "]";
	}
}
