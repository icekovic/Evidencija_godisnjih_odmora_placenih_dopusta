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
	private String od_datuma;
	private String do_datuma;
	
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

	public String getOd_datuma()
	{
		return od_datuma;
	}

	public void setOd_datuma(String od_datuma)
	{
		this.od_datuma = od_datuma;
	}

	public String getDo_datuma()
	{
		return do_datuma;
	}

	public void setDo_datuma(String do_datuma)
	{
		this.do_datuma = do_datuma;
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
