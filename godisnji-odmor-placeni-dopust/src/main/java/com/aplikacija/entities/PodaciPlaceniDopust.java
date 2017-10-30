package com.aplikacija.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PodaciPlaceniDopust
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_podaci_placeni_dopust;
	private String organizacijska_jedinica;
	private String zaposlenik;
	private String maticni_broj_zaposlenika;
	private String tip_placenog_dopusta;
	private int broj_dana_placenog_dopusta;
	
	public PodaciPlaceniDopust()
	{
		
	}
	public int getId_podaci_placeni_dopust()
	{
		return id_podaci_placeni_dopust;
	}
	public void setId_podaci_placeni_dopust(int id_podaci_placeni_dopust)
	{
		this.id_podaci_placeni_dopust = id_podaci_placeni_dopust;
	}
	public String getOrganizacijska_jedinica()
	{
		return organizacijska_jedinica;
	}
	public void setOrganizacijska_jedinica(String organizacijska_jedinica)
	{
		this.organizacijska_jedinica = organizacijska_jedinica;
	}
	public String getZaposlenik()
	{
		return zaposlenik;
	}
	public void setZaposlenik(String zaposlenik)
	{
		this.zaposlenik = zaposlenik;
	}
	public String getMaticni_broj_zaposlenika()
	{
		return maticni_broj_zaposlenika;
	}
	public void setMaticni_broj_zaposlenika(String maticni_broj_zaposlenika)
	{
		this.maticni_broj_zaposlenika = maticni_broj_zaposlenika;
	}
	public String getTip_placenog_dopusta()
	{
		return tip_placenog_dopusta;
	}
	public void setTip_placenog_dopusta(String tip_placenog_dopusta)
	{
		this.tip_placenog_dopusta = tip_placenog_dopusta;
	}
	public int getBroj_dana_placenog_dopusta()
	{
		return broj_dana_placenog_dopusta;
	}
	public void setBroj_dana_placenog_dopusta(int broj_dana_placenog_dopusta)
	{
		this.broj_dana_placenog_dopusta = broj_dana_placenog_dopusta;
	}
}
