package com.aplikacija.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Grad
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_grad;
	private String naziv;

	@OneToMany(mappedBy="grad")
	private List<Hotel> hoteli;
	
	public Grad()
	{
		
	}

	public int getId_grad()
	{
		return id_grad;
	}

	public void setId_grad(int id_grad)
	{
		this.id_grad = id_grad;
	}

	public String getNaziv()
	{
		return naziv;
	}

	public void setNaziv(String naziv)
	{
		this.naziv = naziv;
	}

	public List<Hotel> getHoteli()
	{
		return hoteli;
	}

	public void setHoteli(List<Hotel> hoteli)
	{
		this.hoteli = hoteli;
	}
}
