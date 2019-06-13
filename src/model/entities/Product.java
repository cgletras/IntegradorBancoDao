package model.entities;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idProduto, numeroPaginas, peso;
	private String editora,	titulo,	formatoDoQuadrinho, capaImagem;
	private EstadoProduto estado;
	
	private User user;
	
	public Product() {
		super();
	}

	public Product(int idProduto, int numeroPaginas, int peso, String editora, String titulo, String formatoDoQuadrinho,
				   String capaImagem, EstadoProduto estado, User user) {
		super();
		this.idProduto = idProduto;
		this.numeroPaginas = numeroPaginas;
		this.peso = peso;
		this.editora = editora;
		this.titulo = titulo;
		this.formatoDoQuadrinho = formatoDoQuadrinho;
		this.capaImagem = capaImagem;
		this.estado = estado;
		this.user = user;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFormatoDoQuadrinho() {
		return formatoDoQuadrinho;
	}

	public void setFormatoDoQuadrinho(String formatoDoQuadrinho) {
		this.formatoDoQuadrinho = formatoDoQuadrinho;
	}

	public String getCapaImagem() {
		return capaImagem;
	}

	public void setCapaImagem(String capaImagem) {
		this.capaImagem = capaImagem;
	}

	public EstadoProduto getEstado() {
		return estado;
	}

	public void setEstado(EstadoProduto estado) {
		this.estado = estado;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Product [idProduto=" + idProduto + ", numeroPaginas=" + numeroPaginas + ", peso=" + peso + ", editora="
				+ editora + ", titulo=" + titulo + ", formatoDoQuadrinho=" + formatoDoQuadrinho + ", capaImagem="
				+ capaImagem + ", estado=" + estado + ", user=" + user + "]";
	}

}
