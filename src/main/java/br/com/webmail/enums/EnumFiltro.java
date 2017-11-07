package br.com.webmail.enums;

public enum EnumFiltro {
	CAIXA_ENTRADA("Caixa de entrada",1l),LIXO("Lixo",2l),RASCUNHOS("Rascunhos",3l);
	
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
	
	public static EnumFiltro obtemFiltroPorId(Long id){
		for(EnumFiltro filtro:values()){
			if(filtro.getValor().equals(id)){
				return filtro;
			}
		}
		throw new IllegalArgumentException("Nenhum enum recuperado, id "+id+" inválido.");
	}
}
