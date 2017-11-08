package com.aplikacija.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.aplikacija.entities.Grad;
import com.aplikacija.entities.Hotel;
import com.aplikacija.entities.Rezervacija;
import com.aplikacija.entities.Zaposlenik;
import com.aplikacija.repository.RepozitorijRezervacija;

@Controller
public class RezervacijaController
{
	@Autowired
	private RepozitorijRezervacija repozitorijRezervacija;
	
	@GetMapping(value = "/homeRezervacija")
	public String homeRezervacija(HttpServletRequest request)
	{
		List<Grad> gradovi = repozitorijRezervacija.dohvatiGradove();
		request.getSession().setAttribute("gradovi", gradovi);
		return "homeRezervacija";
	}

	@GetMapping(value = "/dohvatiHotele")
	public String dohvatiHotele()
	{
		return "homeRezervacija";
	}
	
	@PostMapping(value = "/dohvatiHotele")
	public String dohvatiHotele(HttpServletRequest request)
	{
		String odabraniGrad = request.getParameter("odabraniGrad");	
		List<Grad> gradovi = repozitorijRezervacija.dohvatiGradove();
		List<Hotel> hoteli = repozitorijRezervacija.dohvatiHotele(odabraniGrad);
		request.getSession().setAttribute("odabraniGrad", odabraniGrad);
		request.getSession().setAttribute("gradovi", gradovi);
		request.getSession().setAttribute("hoteli", hoteli);
		
		return "homeRezervacija";
	}
	
	@GetMapping(value = "/rezervacija")
	public String rezervacija()
	{
		return "rezervacija";
	}
	
	@PostMapping(value = "/rezervacija")
	public String rezervacija(HttpServletRequest request)
	{
		String nazivHotela =  request.getParameter("nazivHotela");
		Hotel odabraniHotel = repozitorijRezervacija.dohvatiHotel(nazivHotela);
		request.getSession().setAttribute("odabraniHotel", odabraniHotel);
		return "rezervacija";
	}
	
	@GetMapping(value = "/rezerviraj")
	public String rezerviraj(HttpServletRequest request)
	{
		Zaposlenik zaposlenik = (Zaposlenik) request.getSession().getAttribute("zaposlenik");
		prikaziRezervacije(request, zaposlenik);
		return "rezervacija";
	}
	
	@PostMapping(value = "/rezerviraj")
	public String rezervirajPost(HttpServletRequest request)
	{
		String datumPrijave = request.getParameter("datum_prijave");
		String datumOdjave = request.getParameter("datum_odjave");
		Zaposlenik zaposlenik = (Zaposlenik) request.getSession().getAttribute("zaposlenik");
		Hotel hotel = (Hotel) request.getSession().getAttribute("odabraniHotel");
		
		Rezervacija rezervacija = new Rezervacija();
		rezervacija.setDatum_prijave(datumPrijave);
		rezervacija.setDatum_odjave(datumOdjave);
		rezervacija.setZaposlenik(zaposlenik);
		rezervacija.setHotel(hotel);
		repozitorijRezervacija.rezervirajSobu(rezervacija);
		prikaziRezervacije(request, zaposlenik);
		
		return "rezervacija";
	}
	
	private void prikaziRezervacije(HttpServletRequest request, Zaposlenik zaposlenik)
	{
		List<Rezervacija> rezervacije = repozitorijRezervacija.dohvatiRezervacije(zaposlenik);
		request.getSession().setAttribute("rezervacije", rezervacije);
	}
}