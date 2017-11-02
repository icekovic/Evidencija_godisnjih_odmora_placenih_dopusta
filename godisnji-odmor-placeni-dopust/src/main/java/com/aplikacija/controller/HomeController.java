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
import com.aplikacija.entities.PodaciGodisnjiOdmor;
import com.aplikacija.entities.PodaciPlaceniDopust;
import com.aplikacija.entities.Rola;
import com.aplikacija.entities.Zahtjev;
import com.aplikacija.entities.Zaposlenik;
import com.aplikacija.repository.Repozitorij;

@Controller
public class HomeController
{
	@Autowired
	private Repozitorij repozitorij;
	
	@GetMapping(value = "/")
	public String home(Model model)
	{
		return "home";
	}
	
	@GetMapping(value = "/registracija")
	public String registracija(HttpServletRequest request)
	{
		List<OrganizacijskaJedinica> organizacijskejedinice = repozitorij.dohvatiOrganizacijskeJedinice();
		request.getSession().setAttribute("organizacijskeJedinice", organizacijskejedinice);
	
		return "registracija";
	}
	
	@GetMapping(value = "/profilZaposlenika")
	public String profilZaposlenika(HttpServletRequest request)
	{
		return "profilZaposlenika";
	}
	
	@PostMapping(value = "/profilZaposlenika")
	public String profilZaposlenika(Model model, HttpServletRequest request)
	{
		List<Zahtjev> sviZahtjevi = repozitorij.dohvatiSveZahtjeve();
		String korisnickoIme = request.getParameter("korisnickoIme");
		String lozinka = request.getParameter("lozinka");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
		Zaposlenik zaposlenik = repozitorij.dohvatiZaposlenika(korisnickoIme).get(0);
		
		if(zaposlenik.getKorisnicko_ime().equals(korisnickoIme) && encoder.matches(lozinka, zaposlenik.getLozinka()))
		{
			request.getSession().setAttribute("zaposlenik", zaposlenik);
			request.getSession().setAttribute("zahtjevi", zaposlenik.getZahtjevi());
			request.getSession().setAttribute("sviZahtjevi", sviZahtjevi);
			return "profilZaposlenika";
		}
		
		model.addAttribute("kriviPodaci","Korisničko ime ili lozinka nisu ispravni");
		return "home";
	}
	
	@GetMapping(value = "/profilZaposlenikaRegistracija")
	public String profilZaposlenikaRegistracija()
	{
		return "profilZaposlenika";
	}
	
	@PostMapping(value = "/profilZaposlenikaRegistracija")
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
		List<OrganizacijskaJedinica> organizacijskeJedinice = repozitorij.dohvatiOrganizacijskeJedinice();
		
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
		repozitorij.dodajNovogZaposlenika(zaposlenik);
		request.getSession().setAttribute("zaposlenik", zaposlenik);		
		return "profilZaposlenika";
	}
	
	@GetMapping(value = "/noviZahtjev")
	public String noviZahtjev(HttpServletRequest request)
	{
		Zaposlenik zaposlenik = (Zaposlenik) request.getSession().getAttribute("zaposlenik");	
		request.getSession().setAttribute("zahtjevi", zaposlenik.getZahtjevi());	
		return "profilZaposlenika";
	}
	
	@PostMapping(value = "/noviZahtjev")
	public String noviZahtjevPost(HttpServletRequest request)
	{
		String odDatuma = request.getParameter("od_datuma");
		String doDatuma = request.getParameter("do_datuma");
		int brojRadnihDana = Integer.parseInt(request.getParameter("broj_radnih_dana"));
		String tipZahtjeva = request.getParameter("tip_zahtjeva");
		String tipPlacenogDopusta= request.getParameter("tip_placenog_dopusta");
		String odobrenjeOd = request.getParameter("odobrenje_od");
		String napomena = request.getParameter("napomena");
		
		Zahtjev zahtjev = new Zahtjev();
		zahtjev.setOd_datuma(odDatuma);
		zahtjev.setDo_datuma(doDatuma);
		zahtjev.setBroj_radnih_dana(brojRadnihDana);
		zahtjev.setTip(tipZahtjeva);
		zahtjev.setOdobrenje_od(odobrenjeOd);
		zahtjev.setNapomena(napomena);
		
		Zaposlenik zaposlenik = (Zaposlenik) request.getSession().getAttribute("zaposlenik");		
		repozitorij.dodajZahtjev(zahtjev, zaposlenik);
		
		try
		{
			repozitorij.posaljiMailRukovoditelju(zahtjev, zaposlenik);
		}
		catch (MailException ex)
		{
			ex.printStackTrace();
		}
		
		request.getSession().setAttribute("zahtjevi", zaposlenik.getZahtjevi());	
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
		List<PodaciGodisnjiOdmor> podaciGodisnjihOdmora = repozitorij.dohvatiPodatkeZaGodisnjeOdmore();
		repozitorij.kreirajIzvjesceGodisnjihOdmora(podaciGodisnjihOdmora);
		prikaziIzvjesca(request);
		return "izvjesca";
	}
	
	@GetMapping(value = "/placeniDopusti")
	public String kreirajIzvjescePlacenihDopusta(HttpServletRequest request)
	{
		List<PodaciPlaceniDopust> podaciPlacenihDopusta = repozitorij.dohvatiPodatkeZaPlaceneDopuste();
		repozitorij.kreirajIzvjescePlacenihDopusta(podaciPlacenihDopusta);
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
		request.getSession().setAttribute("putanje", putanje);
	}
	
//	@GetMapping(value = "/{idZaposlenik}")
//	public Zaposlenik dohvatiZaposlenika(@PathVariable final int idZaposlenik)
//	{
//		return repozitorij.dohvatiZaposlenika(idZaposlenik).get(0);
//	}
}
