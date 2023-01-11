package com.gabriel.aula.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.gabriel.aula.model.MultaModel;

public class MultaDto {
	
	private Integer id;
	private String ano;
	private String cidade;
	private String carro;
	private String infracao;
	
	
	public MultaDto(MultaModel multaModel) {
	
		this.id = multaModel.getId();
		this.ano = multaModel.getAno();
		this.cidade = multaModel.getCidade();
		this.carro = multaModel.getCarro().getPlaca();
		this.infracao = multaModel.getInfracao().getDescricao();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getCarro() {
		return carro;
	}

	public void setCarro(String carro) {
		this.carro = carro;
	}

	public String getInfracao() {
		return infracao;
	}

	public void setInfracao(String infracao) {
		this.infracao = infracao;
	}

	public static List<MultaDto> converter(List<MultaModel> multas){
		
		return multas.stream().map(MultaDto::new).collect(Collectors.toList());
		
	}
	public static MultaDto converter2(MultaModel multa) {
		MultaDto m = new MultaDto(multa);
		return m;
		
	}
}
