package com.aplikacija.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
		//varijabla zaposlenik se dohvaca iz get metode
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);	//12 oznacava snagu lozinke		
		
		Zaposlenik zaposlenikBaza = repozitorij.dohvatiZaposlenika(zaposlenik.getKorisnicko_ime()).get(0);
		List<PlaceniDopust> tipoviPlacenogDopusta = repozitorij.dohvatiTipovePlacenogDopusta();
		List<Zahtjev> zahtjevi = repozitorij.dohvatiZahtjeve(zaposlenikBaza);
		model.addAttribute("zaposlenik", zaposlenikBaza);
		model.addAttribute("tipoviPlacenogDopusta", tipoviPlacenogDopusta);
		model.addAttribute("zahtjevi", zahtjevi);
		if(zaposlenik.getKorisnicko_ime().equals(zaposlenikBaza.getKorisnicko_ime()) 
				&& encoder.matches(zaposlenik.getLozinka(), zaposlenikBaza.getLozinka()))
		{
			return "profilZaposlenika";
		}
		
		model.addAttribute("kriviPodaci","Korisničko ime ili lozinka nisu ispravni");
		return "home";
	}
		
	@RequestMapping(value = "/noviZahtjev", method = RequestMethod.GET)
	public String kreirajZahtjev(Model model, @ModelAttribute Zaposlenik zaposlenik)
	{
		List<Zahtjev> zahtjevi = repozitorij.dohvatiZahtjeve(zaposlenik);
		model.addAttribute("zaposlenik", new Zaposlenik());
		model.addAttribute("tipoviPlacenogDopusta", new PlaceniDopust());
		model.addAttribute("zahtjevi", zahtjevi);
		return "profilZaposlenika";
	}
	
	@RequestMapping(value = "/noviZahtjev", method = RequestMethod.POST)
	public String kreirajZahtjev(Model model, @ModelAttribute Zaposlenik zaposlenik, HttpServletRequest request)
	{	
		String od_datuma = request.getParameter("od_datuma");
		String do_datuma = request.getParameter("do_datuma");
		int broj_radnih_dana = Integer.parseInt(request.getParameter("broj_radnih_dana"));
		String tip_zahtjeva = request.getParameter("tip_zahtjeva");
		String tip_placenog_dopusta = request.getParameter("tip_placenog_dopusta");
		String odobrenje_od = request.getParameter("odobrenje_od");
		String napomena = request.getParameter("napomena");
		
		Zahtjev noviZahtjev = new Zahtjev();
		noviZahtjev.setOd_datuma(od_datuma);
		noviZahtjev.setDo_datuma(do_datuma);
		noviZahtjev.setBroj_radnih_dana(broj_radnih_dana);
		noviZahtjev.setTip(tip_zahtjeva);
		noviZahtjev.setOdobrenje_od(odobrenje_od);
		noviZahtjev.setNapomena(napomena);
		
		repozitorij.dodajZahtjev(noviZahtjev, zaposlenik);
		
		try
		{
			repozitorij.posaljiMailRukovoditelju(noviZahtjev, zaposlenik);
		}
		catch (MailException ex)
		{
			ex.printStackTrace();
		}
		
		List<Zahtjev> zahtjevi = repozitorij.dohvatiZahtjeve(zaposlenik);
		List<PlaceniDopust> tipoviPlacenogDopusta = repozitorij.dohvatiTipovePlacenogDopusta();		
		
		model.addAttribute("tipoviPlacenogDopusta", tipoviPlacenogDopusta);
		model.addAttribute("zahtjevi", zahtjevi);
		model.addAttribute("zahtjev", noviZahtjev);
		model.addAttribute("zaposlenik", zaposlenik);
		return "profilZaposlenika";
	}
	
	@RequestMapping(value = "/izvjesca", method = RequestMethod.GET)
	public String izvjesca()
	{
		return "izvjesca";
	}
	
	@RequestMapping(value = "/odjava",method = RequestMethod.GET)
	public String odjava(HttpSession session)
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