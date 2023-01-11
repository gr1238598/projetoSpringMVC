package com.gabriel.aula.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class MultaModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;
	private String ano;
	private String cidade;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "carro_id")
	private CarroModel carro;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "infracao_id")
	private InfracaoModel infracao;
	
	public MultaModel(){
		
	}
	
	public MultaModel(Integer id, String ano, String cidade, CarroModel carro, InfracaoModel infracao) {
		super();
		this.id = id;
		this.ano = ano;
		this.cidade = cidade;
		this.carro = carro;
		this.infracao = infracao;
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
	public CarroModel getCarro() {
		return carro;
	}
	public void setCarro(CarroModel carro) {
		this.carro = carro;
	}
	public InfracaoModel getInfracao() {
		return infracao;
	}
	public void setInfracao(InfracaoModel infracao) {
		this.infracao = infracao;
	}
	
	
}
