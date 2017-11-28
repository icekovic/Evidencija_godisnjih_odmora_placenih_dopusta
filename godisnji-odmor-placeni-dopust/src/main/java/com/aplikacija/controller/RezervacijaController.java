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
		model.addAttribute("odabraniGrad", "Crikvenica");
		
		return "homeRezervacija";		
	}
	
	@GetMapping(value = "/hoteli")
	public String hoteli()
	{
		return "homeRezervacija";
	}
	
	@PostMapping(value = "/hoteli")
	public String hoteli(Model model, HttpServletRequest request)
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
	public String rezervacija(Model model, HttpServletRequest request)
	{
		Zaposlenik zaposlenik = (Zaposlenik) request.getSession().getAttribute("zaposlenik");
		String nazivHotela = request.getParameter("nazivHotela");
		Hotel odabraniHotel = repozitorijRezervacija.dohvatiHotel(nazivHotela);
		List<Rezervacija> rezervacije = repozitorijRezervacija.dohvatiRezervacije(zaposlenik);
		
		model.addAttribute("rezervacije", rezervacije);
		model.addAttribute("odabraniHotel", odabraniHotel);
		request.getSession().setAttribute("odabraniHotel", odabraniHotel);
		
		return "rezervacija";
	}
	
	@GetMapping(value = "/rezerviraj")
	public String rezerviraj()
	{
		return "rezervacija";
	}
	
	@PostMapping(value = "/rezerviraj")
	public String rezerviraj(Model model, HttpServletRequest request)
	{
		Zaposlenik zaposlenik = (Zaposlenik) request.getSession().getAttribute("zaposlenik");
		Hotel hotel = (Hotel) request.getSession().getAttribute("odabraniHotel");
		String datumPrijave = request.getParameter("datum_prijave");
		String datumOdjave = request.getParameter("datum_odjave");
		
		Rezervacija rezervacija = new Rezervacija();
		rezervacija.setDatum_prijave(datumPrijave);
		rezervacija.setDatum_odjave(datumOdjave);
		rezervacija.setZaposlenik(zaposlenik);
		rezervacija.setHotel(hotel);
		repozitorijRezervacija.rezervirajSobu(rezervacija);
		
		List<Rezervacija> rezervacije = repozitorijRezervacija.dohvatiRezervacije(zaposlenik);
		model.addAttribute("rezervacije", rezervacije);
		
		return "rezervacija";
	}
}