package com.aplikacija.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Hotel
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_hotel;
	private String naziv;
	private int broj_zvjezdica;
	private String detalji;
	
	@ManyToOne
	@JoinColumn(name="grad_id")
	private Grad grad;
	
	public Hotel()
	{
		
	}

	public int getId_hotel() {
		return id_hotel;
	}

	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}

	public String getNaziv()
	{
		return naziv;
	}

	public void setNaziv(String naziv)
	{
		this.naziv = naziv;
	}

	public int getBroj_zvjezdica()
	{
		return broj_zvjezdica;
	}

	public void setBroj_zvjezdica(int broj_zvjezdica)
	{
		this.broj_zvjezdica = broj_zvjezdica;
	}

	public String getDetalji()
	{
		return detalji;
	}

	public void setDetalji(String detalji)
	{
		this.detalji = detalji;
	}

	public Grad getGrad()
	{
		return grad;
	}

	public void setGrad(Grad grad)
	{
		this.grad = grad;
	}
}
