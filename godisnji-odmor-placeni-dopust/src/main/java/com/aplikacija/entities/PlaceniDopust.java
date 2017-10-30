package com.aplikacija.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PlaceniDopust
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_placeni_dopust;
	
	private String tip;
	private int trajanje_u_danima;
	
	@OneToMany(mappedBy = "placeni_dopust")
	private List<Zahtjev> zahtjevi;

	public int getId_placeni_dopust()
	{
		return id_placeni_dopust;
	}

	public void setId_placeni_dopust(int id_placeni_dopust)
	{
		this.id_placeni_dopust = id_placeni_dopust;
	}

	public String getTip()
	{
		return tip;
	}

	public void setTip(String tip)
	{
		this.tip = tip;
	}
	

	public int getTrajanje_u_danima()
	{
		return trajanje_u_danima;
	}

	public void setTrajanje_u_danima(int trajanje_u_danima)
	{
		this.trajanje_u_danima = trajanje_u_danima;
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
