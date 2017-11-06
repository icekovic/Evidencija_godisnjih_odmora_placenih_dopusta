package com.aplikacija.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.aplikacija.entities.Grad;
import com.aplikacija.entities.Hotel;
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
}