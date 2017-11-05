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
import com.aplikacija.repository.RepozitorijRezervacija;

@Controller
public class RezervacijaController
{
	@Autowired
	private RepozitorijRezervacija repozitorijRezervacija;
	
	@GetMapping(value = "/homeRezervacija")
	public String homeRezervacija(Model model)
	{
		List<Grad> gradovi = repozitorijRezervacija.dohvatiGradove();
		List<Hotel> hoteli = repozitorijRezervacija.dohvatiHotele();
		model.addAttribute("gradovi", gradovi);
		model.addAttribute("hoteli", hoteli);
		return "homeRezervacija";
	}
	
	@PostMapping(value = "/homeRezervacija")
	public String homeRezervacija(HttpServletRequest request)
	{
		int idGrad = Integer.parseInt(request.getParameter("idGrad"));
		return "homeRezervacija";
	}
	
	//hidden idGrad ide u post mapping
}