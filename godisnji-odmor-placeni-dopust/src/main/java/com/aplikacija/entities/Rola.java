package com.aplikacija.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Rola
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_rola;
	private String naziv;
	
	@OneToMany(mappedBy = "rola")
	private List<Zaposlenik> zaposlenici;
	
	public Rola()
	{
		
	}

	public int getId_rola()
	{
		return id_rola;
	}

	public void setId_rola(int id_rola)
	{
		this.id_rola = id_rola;
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
