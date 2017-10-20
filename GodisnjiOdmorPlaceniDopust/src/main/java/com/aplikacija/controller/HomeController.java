package com.aplikacija.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.aplikacija.entities.*;
import com.aplikacija.repository.Repozitorij;

@Controller
@SessionAttributes("zaposlenik")
public class HomeController
{
	Repozitorij repozitorij;
	
	@Autowired
	public HomeController(Repozitorij repozitorij)
	{
		this.repozitorij = repozitorij;
	}
	
	//stranica za prijavu(home) - po defaultu
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model)
	{
		model.addAttribute("zaposlenik", new Zaposlenik());
		return "home";
	}
	
	@RequestMapping(value = "/profilZaposlenika", method = RequestMethod.GET)
	public String profilZaposlenika(Model model)
	{
		model.addAttribute("zaposlenik", new Zaposlenik());
		model.addAttribute("tipoviPlacenogDopusta", new PlaceniDopust());
		return "profilZaposlenika";
	}
	
	//profil zaposlenika - na nju se redirecta sa stranice za prijavu nakon unosa podataka
	@RequestMapping(value = "/profilZaposlenika", method = RequestMethod.POST)
	public String profilZaposlenika(@ModelAttribute Zaposlenik zaposlenik, Model model)
	{		
		//varijabla zaposlenik se dohvaća iz get metode
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);	//12 označava snagu lozinke		
		
		Zaposlenik zaposlenikBaza = repozitorij.dohvatiZaposlenika(zaposlenik.getKorisnicko_ime()).get(0);
		List<PlaceniDopust> tipoviPlacenogDopusta = repozitorij.dohvatiTipovePlacenogDopusta();
		model.addAttribute("tipoviPlacenogDopusta", tipoviPlacenogDopusta);
		model.addAttribute("zaposlenik", zaposlenikBaza);
		
		if(zaposlenik.getKorisnicko_ime().equals(zaposlenikBaza.getKorisnicko_ime()) && encoder.matches(zaposlenik.getLozinka(), zaposlenikBaza.getLozinka()))
		{
			return "profilZaposlenika";
		}
		return "home";
	}
		
	@RequestMapping(value = "/noviZahtjev", method = RequestMethod.GET)
	public String kreirajZahtjev(Model model)
	{
		model.addAttribute("zahtjev", new Zahtjev());
		model.addAttribute("tipoviPlacenogDopusta", new PlaceniDopust());
		return "profilZaposlenika";
	}
	
	@RequestMapping(value = "/noviZahtjev", method = RequestMethod.POST)
	public String kreirajZahtjev(Model model, @ModelAttribute PlaceniDopust tipoviPlacenogDopusta)
	{
		//dohvati tip plaćenog dopusta iz selecta (ako nije odabran, dohvaća se null)
		//umetni zahtjev sa pripadajućim placeni_dopust_id (dohvaćen iz selecta) i zaposlenik_id(iz sessiona)
		//umetni status zahtjeva (hadkodira se ZAPRIMLJEN) i zahtjev_id
		List<PlaceniDopust> placeniDopust = repozitorij.dohvatiTipovePlacenogDopusta();
		model.addAttribute("tipoviPlacenogDopusta", placeniDopust);
		return "profilZaposlenika";
	}
	
	@RequestMapping(value = "/odjava",method = RequestMethod.GET)
	public String odjava(@ModelAttribute("zaposlenik") Zaposlenik zaposlenik, HttpSession session)
	{
		session.invalidate();
		return "redirect:/";
	}
	
	
//		REGISTRACIJA SE TREBA REFAKTORIRATI 
//		@RequestMapping(value = "/registracija", method = RequestMethod.GET)
//		public String registracija(Model model)
//		{
//			
//			List<OrganizacijskaJedinica> organizacijskeJedinice = repozitorij.dohvatiOrganizacijskeJedinice();		
//			model.addAttribute("organizacijskeJedinice", organizacijskeJedinice);
//			model.addAttribute("zaposlenik", new Zaposlenik());
//			return "registracija";
//		}
}