package br.com.webmail.enums;

public enum EnumFiltro {
	CAIXA_ENTRADA("Caixa de entrada",1),LIXO("Lixo",2),RASCUNHOS("Rascunhos",3),EXCLUIDOS("Excluídos",4);
	
	private Integer valor;
	private String descricao;
	
	private EnumFiltro(String descricao,Integer valor){
		this.valor = valor;
		this.descricao = descricao;
	}
	
	public Integer getValor() {
		return valor;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
