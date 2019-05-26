package model.entities;

import java.io.Serializable;

public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idProduto, numeroPaginas, peso;
	private String editora,	titulo,	formatoDoQuadrinho, capaImagem;
	private EstadoProduto estado;
	
	private Usuario usuario;
	
	public Produto() {
		super();
	}

	public Produto(int idProduto, int numeroPaginas, int peso, String editora, String titulo, String formatoDoQuadrinho,
			String capaImagem, EstadoProduto estado, Usuario usuario) {
		super();
		this.idProduto = idProduto;
		this.numeroPaginas = numeroPaginas;
		this.peso = peso;
		this.editora = editora;
		this.titulo = titulo;
		this.formatoDoQuadrinho = formatoDoQuadrinho;
		this.capaImagem = capaImagem;
		this.estado = estado;
		this.usuario = usuario;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", numeroPaginas=" + numeroPaginas + ", peso=" + peso + ", editora="
				+ editora + ", titulo=" + titulo + ", formatoDoQuadrinho=" + formatoDoQuadrinho + ", capaImagem="
				+ capaImagem + ", estado=" + estado + ", usuario=" + usuario + "]";
	}

}
