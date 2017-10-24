package com.aplikacija.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class StatusZahtjeva
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_status_zahtjeva;
	private String status;
	
	@OneToMany(mappedBy = "status_zahtjeva")
	private List<Zahtjev> zahtjevi;
	
	public StatusZahtjeva()
	{
		
	}

	public int getId_status_zahtjeva()	
	{
		return id_status_zahtjeva;
	}

	public void setId_status_zahtjeva(int id_status_zahtjeva)
	{
		this.id_status_zahtjeva = id_status_zahtjeva;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public List<Zahtjev> getZahtjevi()	
	{
		return zahtjevi;
	}

	public void setZahtjevi(List<Zahtjev> zahtjevi)
	{
		this.zahtjevi = zahtjevi;
	}
	
	
}
