package com.aplikacija.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Rezervacija
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_rezervacija;
	private String datum_prijave;
	private String datum_odjave;
	
	@ManyToOne
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name="zaposlenik_id")
	private Zaposlenik zaposlenik;
	
	public Rezervacija()
	{
		
	}

	public int getId_rezervacija()
	{
		return id_rezervacija;
	}

	public void setId_rezervacija(int id_rezervacija)
	{
		this.id_rezervacija = id_rezervacija;
	}

	public String getDatum_prijave()
	{
		return datum_prijave;
	}

	public void setDatum_prijave(String datum_prijave)
	{
		this.datum_prijave = datum_prijave;
	}

	public String getDatum_odjave()
	{
		return datum_odjave;
	}

	public void setDatum_odjave(String datum_odjave)
	{
		this.datum_odjave = datum_odjave;
	}

	public Hotel getHotel()
	{
		return hotel;
	}

	public void setHotel(Hotel hotel)
	{
		this.hotel = hotel;
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
