package com.gabriel.aula.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CarroModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;
	private String placa;
	
	@OneToMany(mappedBy="carro", cascade=CascadeType.ALL)
	private List<MultaModel> listaMultas = new ArrayList<>();
	
	public CarroModel() {
		
	}
	
	public CarroModel( String placa) {
		super();
		this.placa = placa;
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public List<MultaModel> getListaMultas() {
		return listaMultas;
	}
	public void setListaMultas(List<MultaModel> listaMultas) {
		this.listaMultas = listaMultas;
	}
	
}
