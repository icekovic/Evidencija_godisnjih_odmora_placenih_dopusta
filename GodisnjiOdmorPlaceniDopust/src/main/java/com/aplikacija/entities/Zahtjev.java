package com.aplikacija.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Zahtjev
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_zahtjev;
	private String od_datuma;
	private String do_datuma;
	private int broj_radnih_dana;
	private String tip;
	private String odobrenje_od;
	private String napomena;
	
	@ManyToOne
	@JoinColumn(name = "zaposlenik_id")
	private Zaposlenik zaposlenik;
	
	@ManyToOne
	@JoinColumn(name = "placeni_dopust_id")
	private PlaceniDopust placeni_dopust;

	public Zahtjev()
	{
		
	}

	public int getId_zahtjev()
	{
		return id_zahtjev;
	}

	public void setId_zahtjev(int id_zahtjev)
	{
		this.id_zahtjev = id_zahtjev;
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

	public int getBroj_radnih_dana()
	{
		return broj_radnih_dana;
	}

	public void setBroj_radnih_dana(int broj_radnih_dana) 
	{
		this.broj_radnih_dana = broj_radnih_dana;
	}

	public String getTip()
	{
		return tip;
	}

	public void setTip(String tip)
	{
		this.tip = tip;
	}

	public String getOdobrenje_od()
	{
		return odobrenje_od;
	}

	public void setOdobrenje_od(String odobrenje_od)
	{
		this.odobrenje_od = odobrenje_od;
	}

	public String getNapomena()
	{
		return napomena;
	}

	public void setNapomena(String napomena)
	{
		this.napomena = napomena;
	}

	public Zaposlenik getZaposlenik()
	{
		return zaposlenik;
	}

	public void setZaposlenik(Zaposlenik zaposlenik)
	{
		this.zaposlenik = zaposlenik;
	}

	public PlaceniDopust getPlaceni_dopust()	
	{
		return placeni_dopust;
	}

	public void setPlaceni_dopust(PlaceniDopust placeni_dopust)
	{
		this.placeni_dopust = placeni_dopust;
	}
}
