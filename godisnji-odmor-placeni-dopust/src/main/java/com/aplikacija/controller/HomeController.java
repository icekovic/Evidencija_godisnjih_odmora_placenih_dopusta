package com.aplikacija.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.aplikacija.entities.OrganizacijskaJedinica;
import com.aplikacija.entities.PlaceniDopust;
import com.aplikacija.entities.Rola;
import com.aplikacija.entities.Zahtjev;
import com.aplikacija.entities.Zaposlenik;
import com.aplikacija.service.IRepozitorijGlavnaAplikacija;

@Controller
public class HomeController
{
	@Autowired
	private IRepozitorijGlavnaAplikacija repozitorijGlavnaAplikacija;
	
	@GetMapping(value = "/")
	public String home()
	{
		return "home";
	}
	
	@GetMapping(value = "/registracija")
	public String registracija(HttpServletRequest request, Model model)
	{
		List<OrganizacijskaJedinica> organizacijskejedinice = repozitorijGlavnaAplikacija.dohvatiOrganizacijskeJedinice();
		model.addAttribute("organizacijskeJedinice", organizacijskejedinice);
		return "registracija";
	}
	
	@GetMapping(value = "/profil-zaposlenika")
	public String profilZaposlenika(Model model)
	{
		List<PlaceniDopust> tipoviPlacenogDopusta = repozitorijGlavnaAplikacija.dohvatiTipovePlacenihDopusta();
		model.addAttribute("tipoviPlacenogDopusta", tipoviPlacenogDopusta);
		return "profilZaposlenika";
	}
	
	@PostMapping(value = "/profil-zaposlenika")
	public String profilZaposlenika(Model model, HttpServletRequest request)
	{
		List<Zahtjev> sviZahtjevi = repozitorijGlavnaAplikacija.dohvatiSveZahtjeve();
		List<PlaceniDopust> tipoviPlacenogDopusta = repozitorijGlavnaAplikacija.dohvatiTipovePlacenihDopusta();
		String korisnickoIme = request.getParameter("korisnickoIme");
		String lozinka = request.getParameter("lozinka");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
		Zaposlenik zaposlenik = repozitorijGlavnaAplikacija.dohvatiZaposlenika(korisnickoIme).get(0);
		
		if(zaposlenik.getKorisnicko_ime().equals(korisnickoIme) && encoder.matches(lozinka, zaposlenik.getLozinka()))
		{
			request.getSession().setAttribute("zaposlenik", zaposlenik);
			model.addAttribute("zahtjevi", zaposlenik.getZahtjevi());
			model.addAttribute("sviZahtjevi", sviZahtjevi);
			model.addAttribute("tipoviPlacenogDopusta", tipoviPlacenogDopusta);
			return "profilZaposlenika";
		}
		
		model.addAttribute("kriviPodaci","Korisničko ime ili lozinka nisu ispravni");
		return "home";
	}
	
	@GetMapping(value = "/profil-zaposlenika-registracija")
	public String profilZaposlenikaRegistracija()
	{
		return "profilZaposlenika";
	}
	
	@PostMapping(value = "/profil-zaposlenika-registracija")
	public String profilZaposlenikaRegistracija(HttpServletRequest request)
	{	
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
		
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		String email = request.getParameter("email");
		String korisnickoIme = request.getParameter("korisnicko_ime");
		String lozinka = request.getParameter("lozinka");
		String hashiranaLozinka = encoder.encode(lozinka);
		String maticniBroj = request.getParameter("maticni_broj");
		String datumZaposlenja = request.getParameter("datum_zaposlenja");
		String tjelesnoOstecenjeInvalidnost = request.getParameter("tjelesno_ostecenje_invalidnost");
		int godineStaza = Integer.parseInt(request.getParameter("godine_staza"));
		int brojDjece = Integer.parseInt(request.getParameter("broj_djece"));
		String organizacijska_jedinica = request.getParameter("organizacijskaJedinica");
		
		Rola rola = new Rola();
		rola.setId_rola(1);
		rola.setNaziv("Obični zaposlenik");
		
		OrganizacijskaJedinica organizacijskaJedinica = new OrganizacijskaJedinica();
		List<OrganizacijskaJedinica> organizacijskeJedinice = repozitorijGlavnaAplikacija.dohvatiOrganizacijskeJedinice();
		
		Zaposlenik zaposlenik = new Zaposlenik();
		zaposlenik.setIme(ime);
		zaposlenik.setPrezime(prezime);
		zaposlenik.setKorisnicko_ime(korisnickoIme);
		zaposlenik.setEmail(email);
		zaposlenik.setLozinka(hashiranaLozinka);
		zaposlenik.setMaticni_broj(maticniBroj);
		zaposlenik.setDatum_zaposlenja(datumZaposlenja);
		zaposlenik.setTjelesno_ostecenje_invalidnost(tjelesnoOstecenjeInvalidnost);
		zaposlenik.setGodine_staza(godineStaza);
		zaposlenik.setBroj_djece(brojDjece);
		zaposlenik.setRola(rola);
		
		for(OrganizacijskaJedinica org : organizacijskeJedinice)
		{
			if(org.getNaziv().equals(organizacijska_jedinica))
			{
				organizacijskaJedinica.setId_organizacijska_jedinica(org.getId_organizacijska_jedinica());
				organizacijskaJedinica.setNaziv(org.getNaziv());
			}
		}
		
		zaposlenik.setOrganizacijska_jedinica(organizacijskaJedinica);
		repozitorijGlavnaAplikacija.dodajNovogZaposlenika(zaposlenik);
		request.getSession().setAttribute("zaposlenik", zaposlenik);		
		return "profilZaposlenika";
	}
	
	@GetMapping(value = "/novi-zahtjev-godisnji-odmor")
	public String noviZahtjevGodisnjiOdmor()
	{		
		return "profilZaposlenika";
	}
	
	@PostMapping(value = "/novi-zahtjev-godisnji-odmor")
	public String noviZahtjevGodisnjiOdmor(HttpServletRequest request, Model model)
	{
		String odDatuma = request.getParameter("od_datuma_godisnji_odmor");
		String doDatuma = request.getParameter("do_datuma_godisnji_odmor");
		int brojRadnihDana = Integer.parseInt(request.getParameter("broj_radnih_dana"));
		String odobrenjeOd = request.getParameter("odobrenje_od");
		String napomena = request.getParameter("napomena_godisnji_odmor");
		
		Zahtjev zahtjev = new Zahtjev();
		zahtjev.setOd_datuma(odDatuma);
		zahtjev.setDo_datuma(doDatuma);
		zahtjev.setBroj_radnih_dana(brojRadnihDana);
		zahtjev.setTip("Godišnji odmor");
		zahtjev.setOdobrenje_od(odobrenjeOd);
		zahtjev.setNapomena(napomena);
		
		Zaposlenik zaposlenik = (Zaposlenik) request.getSession().getAttribute("zaposlenik");		
		repozitorijGlavnaAplikacija.dodajZahtjev(zahtjev, zaposlenik);
		
		try
		{
			repozitorijGlavnaAplikacija.posaljiMailRukovoditelju(zahtjev, zaposlenik);
		}
		catch (MailException ex)
		{
			ex.printStackTrace();
		}
		
		List<Zahtjev> zahtjevi = repozitorijGlavnaAplikacija.dohvatiZahtjeve(zaposlenik);
		List<PlaceniDopust> tipoviPlacenogDopusta = repozitorijGlavnaAplikacija.dohvatiTipovePlacenihDopusta();
		model.addAttribute("zahtjevi", zahtjevi);
		model.addAttribute("tipoviPlacenogDopusta", tipoviPlacenogDopusta);
		
		return "profilZaposlenika";
	}
	
	@GetMapping(value = "/novi-zahtjev-placeni-dopust")
	public String noviZahtjevPlaceniDopust()
	{
		return "profilZaposlenika";
	}
	
	@PostMapping(value = "/novi-zahtjev-placeni-dopust")
	public String noviZahtjevPlaceniDopust(HttpServletRequest request, Model model)
	{
		List<PlaceniDopust> tipoviPlacenogDopusta = repozitorijGlavnaAplikacija.dohvatiTipovePlacenihDopusta();
		Zaposlenik zaposlenik = (Zaposlenik) request.getSession().getAttribute("zaposlenik");
		
		String odDatuma = request.getParameter("od_datuma_placeni_dopust");
		String doDatuma = request.getParameter("do_datuma_placeni_dopust");
		int trajanje = Integer.parseInt(request.getParameter("trajanje"));
		String tipPlacenogDopusta= request.getParameter("tip_placenog_dopusta");
		String odobrenjeOd = request.getParameter("odobrenje_od");
		String napomena = request.getParameter("napomena_placeni_dopust");
		
		PlaceniDopust placeniDopust = repozitorijGlavnaAplikacija.dohvatiTipPlacenogDopusta(tipPlacenogDopusta).get(0);
		
		Zahtjev zahtjev = new Zahtjev();
		zahtjev.setOd_datuma(odDatuma);
		zahtjev.setDo_datuma(doDatuma);
		zahtjev.setBroj_radnih_dana(trajanje);
		zahtjev.setTip("Plaćeni dopust");
		zahtjev.setOdobrenje_od(odobrenjeOd);
		zahtjev.setNapomena(napomena);
		zahtjev.setPlaceni_dopust(placeniDopust);
		
		repozitorijGlavnaAplikacija.dodajZahtjev(zahtjev, zaposlenik);			
		model.addAttribute("tipoviPlacenogDopusta", tipoviPlacenogDopusta);
		
		List<Zahtjev> zahtjevi = repozitorijGlavnaAplikacija.dohvatiZahtjeve(zaposlenik);
		model.addAttribute("zahtjevi", zahtjevi);
		
		return "profilZaposlenika";
	}
	
	@GetMapping(value = "/izvjesca")
	public String izvjesca(HttpServletRequest request)
	{
		prikaziIzvjesca(request);
		return "izvjesca";
	}
	
	@GetMapping(value = "/godisnjiOdmori")
	public String kreirajIzvjesceGodisnjihOdmora(HttpServletRequest request)
	{
		repozitorijGlavnaAplikacija.kreirajIzvjesceGodisnjihOdmora();
		prikaziIzvjesca(request);		
		return "izvjesca";
	}
	
	@GetMapping(value = "/placeniDopusti")
	public String kreirajIzvjescePlacenihDopusta(HttpServletRequest request)
	{
		repozitorijGlavnaAplikacija.kreirajIzvjescePlacenihDopusta();
		prikaziIzvjesca(request);
		return "izvjesca";
	}
	
	@GetMapping(value = "/iznosRegresa")
	public String kreirajIznosRegresaGodisnjegOdmora(HttpServletRequest request)
	{
		repozitorijGlavnaAplikacija.kreirajIzvjesceIznosRegresaGodisnjegOdmora();
		prikaziIzvjesca(request);
		return "izvjesca";
	}
	
	@GetMapping(value = "/odjava")
	public String odjava(HttpServletRequest request)
	{
		request.getSession().removeAttribute("zaposlenik");
		return "redirect:/";
	}
	
	private void prikaziIzvjesca(HttpServletRequest request)
	{
		List<String> putanje = new ArrayList<String>();
		File[] izvjesca = new File("D:\\Izvjesca").listFiles();
		
		for(File izvjesce : izvjesca)
		{
			if(izvjesce.isFile())
			{
				putanje.add(izvjesce.getAbsolutePath());
			}
		}
		request.getSession().setAttribute("izvjesca", izvjesca);
	}
	
	@GetMapping(value = "/odobriZahtjev")
	public String odobriZahtjev()
	{
		return "profilZaposlenika";
	}
	
	@PostMapping(value = "/odobriZahtjev")
	public String odobriZahtjev(HttpServletRequest request)
	{
		int idZahtjev = Integer.parseInt(request.getParameter("idZahtjev"));
		repozitorijGlavnaAplikacija.odobriZahtjev(idZahtjev);
		request.getSession().setAttribute("sviZahtjevi", repozitorijGlavnaAplikacija.dohvatiSveZahtjeve());
		return "profilZaposlenika";
	}
	
	@GetMapping(value = "/odbijZahtjev")
	public String odbijZahtjev()
	{
		return "profilZaposlenika";
	}
	
	@PostMapping(value = "/odbijZahtjev")
	public String odbijZahtjevPost(HttpServletRequest request)
	{
		int idZahtjev = Integer.parseInt(request.getParameter("idZahtjev"));
		repozitorijGlavnaAplikacija.odbijZahtjev(idZahtjev);
		request.getSession().setAttribute("sviZahtjevi", repozitorijGlavnaAplikacija.dohvatiSveZahtjeve());
		return "profilZaposlenika";
	}
}
