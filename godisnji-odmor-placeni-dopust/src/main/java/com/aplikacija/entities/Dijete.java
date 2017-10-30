package com.aplikacija.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Dijete
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_dijete;
	private int starost;
	
	@ManyToOne
	@JoinColumn(name = "zaposlenik_id")
	private Zaposlenik zaposlenik;
	
	public Dijete()
	{
		
	}

	public int getId_dijete()	
	{
		return id_dijete;
	}

	public void setId_dijete(int id_dijete)
	{
		this.id_dijete = id_dijete;
	}

	public int getStarost()
	{
		return starost;
	}

	public void setStarost(int starost)
	{
		this.starost = starost;
	}

	public Zaposlenik getZaposlenik()
	{
		return zaposlenik;
	}

	public void setZaposlenik(Zaposlenik zaposlenik)
	{
		this.zaposlenik = zaposlenik;
	}
	
	
}
