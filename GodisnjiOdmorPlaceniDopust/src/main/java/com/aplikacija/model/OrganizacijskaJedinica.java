package com.aplikacija.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrganizacijskaJedinica
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDOrganizacijskaJedinica")
	private int idOrganizacijskaJedinica;
	
	@Column(name = "Naziv")
	private String naziv;
	
	public OrganizacijskaJedinica()
	{
		
	}
	
	public OrganizacijskaJedinica(int idOrganizacijskaJedinica, String naziv)
	{
		super();
		this.idOrganizacijskaJedinica = idOrganizacijskaJedinica;
		this.naziv = naziv;
	}

	public int getIdOrganizacijskaJedinica()
	{
		return idOrganizacijskaJedinica;
	}

	public void setIdOrganizacijskaJedinica(int idOrganizacijskaJedinica)
	{
		this.idOrganizacijskaJedinica = idOrganizacijskaJedinica;
	}

	public String getNaziv()	
	{
		return naziv;
	}

	public void setNaziv(String naziv)
	{
		this.naziv = naziv;
	}
	
	
}
