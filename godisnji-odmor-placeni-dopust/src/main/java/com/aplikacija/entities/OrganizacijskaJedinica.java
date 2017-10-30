package com.aplikacija.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class OrganizacijskaJedinica
{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_organizacijska_jedinica;	
	private String naziv;
	
	@OneToMany(mappedBy = "organizacijska_jedinica")
	private List<Zaposlenik> zaposlenici;
	
	public OrganizacijskaJedinica()
	{
		
	}
	
	public int getId_organizacijska_jedinica()
	{
		return id_organizacijska_jedinica;
	}

	public void setId_organizacijska_jedinica(int id_organizacijska_jedinica)
	{
		this.id_organizacijska_jedinica = id_organizacijska_jedinica;
	}

	public String getNaziv()	
	{
		return naziv;
	}

	public void setNaziv(String naziv)
	{
		this.naziv = naziv;
	}

	public List<Zaposlenik> getZaposlenici()
	{
		return zaposlenici;
	}

	public void setZaposlenici(List<Zaposlenik> zaposlenici)
	{
		this.zaposlenici = zaposlenici;
	}	
}
