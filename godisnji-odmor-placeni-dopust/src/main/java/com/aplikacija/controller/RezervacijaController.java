package com.aplikacija.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.aplikacija.entities.Grad;
import com.aplikacija.entities.Hotel;
import com.aplikacija.entities.Rezervacija;
import com.aplikacija.entities.Zaposlenik;
import com.aplikacija.service.IRepozitorijRezervacija;

@Controller
public class RezervacijaController
{
	@Autowired
	private IRepozitorijRezervacija repozitorijRezervacija;
	
	@GetMapping(value = "/home-rezervacija")
	public String homeRezervacija(Model model)
	{
		List<Grad> gradovi = repozitorijRezervacija.dohvatiGradove();
		List<Hotel> hoteli = repozitorijRezervacija.dohvatiHotele("Crikvenica");
		model.addAttribute("gradovi", gradovi);
		model.addAttribute("hoteli", hoteli);
		
		return "homeRezervacija";
	}

	@GetMapping(value = "/dohvati-hotele")
	public String dohvatiHotele(Model model)
	{
		model.addAttribute("odabraniGrad", "Crikvenica");
		return "homeRezervacija";
	}
	
	@PostMapping(value = "/dohvatiHotele")
	public String dohvatiHotele(Model model, HttpServletRequest request)
	{
		String odabraniGrad = request.getParameter("odabraniGrad");	
		List<Grad> gradovi = repozitorijRezervacija.dohvatiGradove();
		List<Hotel> hoteli = repozitorijRezervacija.dohvatiHotele(odabraniGrad);
		model.addAttribute("odabraniGrad", odabraniGrad);
		model.addAttribute("gradovi", gradovi);
		model.addAttribute("hoteli", hoteli);
		
		return "homeRezervacija";
	}
	
	@GetMapping(value = "/rezervacija")
	public String rezervacija()
	{
		return "rezervacija";
	}
	
	@PostMapping(value = "/rezervacija")
	public String rezervacija(HttpServletRequest request, Model model)
	{
		Zaposlenik zaposlenik = (Zaposlenik) request.getSession().getAttribute("zaposlenik");
		String nazivHotela =  request.getParameter("nazivHotela");
		Hotel odabraniHotel = repozitorijRezervacija.dohvatiHotel(nazivHotela);
		request.getSession().setAttribute("odabraniHotel", odabraniHotel);
		model.addAttribute("rezervacije", zaposlenik.getRezervacije());
		return "rezervacija";
	}
	
	@GetMapping(value = "/rezerviraj")
	public String rezerviraj(HttpServletRequest request)
	{
		//prikaziRezervacije(request, zaposlenik);
		return "rezervacija";
	}
	
	@PostMapping(value = "/rezerviraj")
	public String rezervirajPost(HttpServletRequest request, Model model)
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
		model.addAttribute("rezervacije", zaposlenik.getRezervacije());
		//prikaziRezervacije(m, zaposlenik);
		
		return "rezervacija";
	}
}