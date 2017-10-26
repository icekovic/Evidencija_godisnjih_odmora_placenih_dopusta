package com.aplikacija.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PodaciGodisnjiOdmor
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_podaci_godisnjeg_odmora;
	private String organizacijska_jedinica;
	private String zaposlenik;
	private String maticni_broj_zaposlenika;
	private int broj_dana_godisnjeg_odmora;
	private int godine_staza;
	private String rola;
	private String tjelesno_ostecenje_invalidnost;
	private int broj_djece;
	private int starost_djece;
	
	public PodaciGodisnjiOdmor()
	{
		
	}

	public int getId_podaci_godisnjeg_odmora()
	{
		return id_podaci_godisnjeg_odmora;
	}

	public void setId_podaci_godisnjeg_odmora(int id_podaci_godisnjeg_odmora)
	{
		this.id_podaci_godisnjeg_odmora = id_podaci_godisnjeg_odmora;
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

	public int getBroj_dana_godisnjeg_odmora()
	{
		return broj_dana_godisnjeg_odmora;
	}

	public void setBroj_dana_godisnjeg_odmora(int broj_dana_godisnjeg_odmora)
	{
		this.broj_dana_godisnjeg_odmora = broj_dana_godisnjeg_odmora;
	}

	public int getGodine_staza()
	{
		return godine_staza;
	}

	public void setGodine_staza(int godine_staza)
	
	{
		this.godine_staza = godine_staza;
	}

	public String getRola()
	{
		return rola;
	}

	public void setRola(String rola)
	{
		this.rola = rola;
	}

	public String getTjelesno_ostecenje_invalidnost()
	{
		return tjelesno_ostecenje_invalidnost;
	}

	public void setTjelesno_ostecenje_invalidnost(String tjelesno_ostecenje_invalidnost)
	{
		this.tjelesno_ostecenje_invalidnost = tjelesno_ostecenje_invalidnost;
	}

	public int getBroj_djece()
	{
		return broj_djece;
	}

	public void setBroj_djece(int broj_djece)
	{
		this.broj_djece = broj_djece;
	}

	public int getStarost_djece()
	{
		return starost_djece;
	}

	public void setStarost_djece(int starost_djece)
	{
		this.starost_djece = starost_djece;
	}
	
	
}
