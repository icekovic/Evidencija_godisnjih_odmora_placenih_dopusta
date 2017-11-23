package com.aplikacija.service;

import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.aplikacija.entities.OrganizacijskaJedinica;
import com.aplikacija.entities.PlaceniDopust;
import com.aplikacija.entities.Zahtjev;
import com.aplikacija.entities.Zaposlenik;

public interface IRepozitorijGlavnaAplikacija
{
	public List<Zaposlenik> dohvatiZaposlenike();
	public List<PlaceniDopust> dohvatiTipovePlacenihDopusta();
	public List<Zaposlenik> dohvatiZaposlenika(String korisnickoIme);
	public List<OrganizacijskaJedinica> dohvatiOrganizacijskeJedinice();
	public List<Zahtjev> dohvatiSveZahtjeve();
	public List<Zahtjev> dohvatiZahtjeve(Zaposlenik zaposlenik);
	public void dodajNovogZaposlenika(Zaposlenik zaposlenik);
	public void dodajZahtjevGodisnjiOdmor(Zahtjev zahtjev, Zaposlenik zaposlenik);
	public void dodajZahtjevPlaceniDopust(Zahtjev zahtjev, Zaposlenik zaposlenik, String tipPlacenogDopusta);
	public void posaljiMailRukovoditelju(Zahtjev zahtjev, Zaposlenik zaposlenik);
	public HSSFWorkbook kreirajIzvjesceGodisnjihOdmora();
	public HSSFWorkbook kreirajIzvjescePlacenihDopusta();
	public HSSFWorkbook kreirajIzvjesceIznosRegresaGodisnjegOdmora();
	public void odobriZahtjev(int idZahtjev);
	public void odbijZahtjev(int idZahtjev);	
}
