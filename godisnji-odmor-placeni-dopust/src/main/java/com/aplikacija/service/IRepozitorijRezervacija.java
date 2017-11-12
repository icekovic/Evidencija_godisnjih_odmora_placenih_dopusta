package com.aplikacija.service;

import java.util.List;
import com.aplikacija.entities.Grad;
import com.aplikacija.entities.Hotel;
import com.aplikacija.entities.Rezervacija;
import com.aplikacija.entities.Zaposlenik;

public interface IRepozitorijRezervacija
{
	public List<Grad> dohvatiGradove();
	public List<Hotel> dohvatiHotele(String odabraniGrad);
	public Hotel dohvatiHotel(String nazivHotela);
	public void rezervirajSobu(Rezervacija rezervacija);
	public List<Rezervacija> dohvatiRezervacije(Zaposlenik zaposlenik);
}
