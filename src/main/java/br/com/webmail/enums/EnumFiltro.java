package br.com.webmail.enums;

public enum EnumFiltro {
	CAIXA_ENTRADA("Caixa de entrada",1l),LIXO("Lixo",2l),RASCUNHOS("Rascunhos",3l),EXCLUIDOS("Excluídos",4l);
	
	private Long valor;
	private String descricao;
	
	private EnumFiltro(String descricao,Long valor){
		this.valor = valor;
		this.descricao = descricao;
	}
	
	public Long getValor() {
		return valor;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
