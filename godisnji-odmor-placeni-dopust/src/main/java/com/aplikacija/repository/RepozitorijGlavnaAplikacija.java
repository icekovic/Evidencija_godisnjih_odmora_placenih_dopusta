package com.aplikacija.repository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.aplikacija.entities.Dijete;
import com.aplikacija.entities.OrganizacijskaJedinica;
import com.aplikacija.entities.PlaceniDopust;
import com.aplikacija.entities.StatusZahtjeva;
import com.aplikacija.entities.Zahtjev;
import com.aplikacija.entities.Zaposlenik;
import com.aplikacija.service.IRepozitorijGlavnaAplikacija;

@Repository
@Transactional
public class RepozitorijGlavnaAplikacija implements IRepozitorijGlavnaAplikacija
{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@SuppressWarnings("unchecked")
	public List<Zaposlenik> dohvatiZaposlenike()
	{
		Query query = entityManager.createQuery("from Zaposlenik");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<PlaceniDopust> dohvatiTipovePlacenihDopusta()
	{
		Query query = entityManager.createQuery("from PlaceniDopust");
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	public List<Zaposlenik> dohvatiZaposlenika(String korisnickoIme)
	{
		Query query = entityManager.createQuery("from Zaposlenik where korisnicko_ime = :korisnickoIme");
		query.setParameter("korisnickoIme", korisnickoIme);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<OrganizacijskaJedinica> dohvatiOrganizacijskeJedinice()
	{
		Query query = entityManager.createQuery("from OrganizacijskaJedinica");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Zahtjev> dohvatiSveZahtjeve()
	{
		Query query = entityManager.createQuery("from Zahtjev");
		return query.getResultList();
	}
	
	public List<Zahtjev> dohvatiZahtjeve(Zaposlenik zaposlenik)
	{
		List<Zahtjev> zahtjevi = new ArrayList<>();
		
		for(Zahtjev zahtjev : dohvatiSveZahtjeve())
		{
			if(zahtjev.getZaposlenik().getId_zaposlenik() == zaposlenik.getId_zaposlenik())
			{
				zahtjevi.add(zahtjev);
			}
		}
		
		return zahtjevi;
	}

	public void dodajNovogZaposlenika(Zaposlenik zaposlenik)
	{
		entityManager.persist(zaposlenik);
	}

	public void dodajZahtjev(Zahtjev zahtjev, Zaposlenik zaposlenik)
	{
		StatusZahtjeva statusZahtjeva = new StatusZahtjeva();
		statusZahtjeva.setId_status_zahtjeva(1);
		statusZahtjeva.setStatus("Zaprimljen");
		
		zahtjev.setZaposlenik(zaposlenik);
		zahtjev.setStatus_zahtjeva(statusZahtjeva);
		entityManager.persist(zahtjev);
	}

	public void posaljiMailRukovoditelju(Zahtjev zahtjev, Zaposlenik zaposlenik)
	{
		SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo("dwetherburn@gmail.com");
	    message.setFrom(zaposlenik.getEmail());
	    message.setSubject(zahtjev.getTip());
	    message.setText("Zaposlenik: " + zaposlenik.getIme() + " " + zaposlenik.getPrezime() + "\nTip zahtjeva: " + zahtjev.getTip() + "\nOd datuma: " + 
	    zahtjev.getOd_datuma() + "\nDo datuma: " + zahtjev.getDo_datuma());
	    mailSender.send(message);
	}

	public HSSFWorkbook kreirajIzvjesceGodisnjihOdmora()
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Korištenje godišnjeg odmora");
		
		Row redakNasloviStupaca = sheet.createRow(0);
		redakNasloviStupaca.createCell(0).setCellValue("Organizacijska jedinica");
		redakNasloviStupaca.createCell(1).setCellValue("Zaposlenik");
		redakNasloviStupaca.createCell(2).setCellValue("Maticni broj zaposlenika");
		redakNasloviStupaca.createCell(3).setCellValue("Od datuma");
		redakNasloviStupaca.createCell(4).setCellValue("Do datuma");
		redakNasloviStupaca.createCell(5).setCellValue("Broj dana godišnjeg odmora");
		redakNasloviStupaca.createCell(6).setCellValue("Godine staža");
		redakNasloviStupaca.createCell(7).setCellValue("Rola");
		redakNasloviStupaca.createCell(8).setCellValue("Tjelesno oštećenje/invalidnost");
		redakNasloviStupaca.createCell(9).setCellValue("Broj djece");
		redakNasloviStupaca.createCell(10).setCellValue("Starost djece");
		
		List<OrganizacijskaJedinica> organizacijskeJedinice = dohvatiOrganizacijskeJedinice();
		int i = 1;
		
		for(OrganizacijskaJedinica organizacijskaJedinica : organizacijskeJedinice)
		{
			for(Zaposlenik zaposlenik : organizacijskaJedinica.getZaposlenici())
			{
				for(Zahtjev zahtjev : zaposlenik.getZahtjevi())
				{
					for(Dijete dijete : zaposlenik.getDjeca())
					{
						if(zahtjev.getTip().equals("Godišnji odmor"))
						{
							Row redak = sheet.createRow(i);
							redak.createCell(0).setCellValue(organizacijskaJedinica.getNaziv());
							redak.createCell(1).setCellValue(zaposlenik.getIme() + " " + zaposlenik.getPrezime());
							redak.createCell(2).setCellValue(zaposlenik.getMaticni_broj());
							redak.createCell(3).setCellValue(zahtjev.getOd_datuma());
							redak.createCell(4).setCellValue(zahtjev.getDo_datuma());
							redak.createCell(5).setCellValue(zahtjev.getBroj_radnih_dana());
							redak.createCell(6).setCellValue(zaposlenik.getGodine_staza());
							redak.createCell(7).setCellValue(zaposlenik.getRola().getNaziv());
							redak.createCell(8).setCellValue(zaposlenik.getTjelesno_ostecenje_invalidnost());
							redak.createCell(9).setCellValue(zaposlenik.getBroj_djece());
							redak.createCell(10).setCellValue(dijete.getStarost());
							i++;
						}						
					}
				}
			}
		}
	
		try
		{
			workbook.write(new FileOutputStream("D:/Izvjesca/GodisnjiOdmori.xls"));
			workbook.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}	
		return workbook;
	}
	
	public HSSFWorkbook kreirajIzvjescePlacenihDopusta()
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Korištenje plaćenog dopusta");
		
		Row redakNasloviStupaca = sheet.createRow(0);
		redakNasloviStupaca.createCell(0).setCellValue("Organizacijska jedinica");
		redakNasloviStupaca.createCell(1).setCellValue("Zaposlenik");
		redakNasloviStupaca.createCell(2).setCellValue("Maticni broj zaposlenika");
		redakNasloviStupaca.createCell(3).setCellValue("Tip plaćenog dopusta");
		redakNasloviStupaca.createCell(4).setCellValue("Broj dana plaćenog dopusta");
		
		List<OrganizacijskaJedinica> organizacijskeJedinice = dohvatiOrganizacijskeJedinice();
		int i = 1;
		
		for(OrganizacijskaJedinica organizacijskaJedinica : organizacijskeJedinice)
		{
			for(Zaposlenik zaposlenik : organizacijskaJedinica.getZaposlenici())
			{
				for(Zahtjev zahtjev : zaposlenik.getZahtjevi())
				{				
					if(zahtjev.getTip().equals("Plaćeni dopust"))
					{
						Row redak = sheet.createRow(i);
						redak.createCell(0).setCellValue(organizacijskaJedinica.getNaziv());
						redak.createCell(1).setCellValue(zaposlenik.getIme() + " " + zaposlenik.getPrezime());
						redak.createCell(2).setCellValue(zaposlenik.getMaticni_broj());
						redak.createCell(3).setCellValue(zahtjev.getPlaceni_dopust().getTip());
						redak.createCell(4).setCellValue(zahtjev.getPlaceni_dopust().getTrajanje_u_danima());
						i++;
					}				
				}			
			}			
		}
	
		try
		{
			workbook.write(new FileOutputStream("D:/Izvjesca/PlaceniDopusti.xls"));
			workbook.close();
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return workbook;
	}
	
	public HSSFWorkbook kreirajIzvjesceIznosRegresaGodisnjegOdmora()
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Iznos regresa za godišnji odmor");
		
		Row redakNasloviStupaca = sheet.createRow(0);
		redakNasloviStupaca.createCell(0).setCellValue("Organizacijska jedinica");
		redakNasloviStupaca.createCell(1).setCellValue("Zaposlenik");
		redakNasloviStupaca.createCell(2).setCellValue("Maticni broj zaposlenika");
		redakNasloviStupaca.createCell(3).setCellValue("Iznos regresa za godišnji odmor");
		int i = 1;
		
		List<OrganizacijskaJedinica> organizacijskeJedinice = dohvatiOrganizacijskeJedinice();
		for(OrganizacijskaJedinica organizacijskaJedinica : organizacijskeJedinice)
		{
			for(Zaposlenik zaposlenik : organizacijskaJedinica.getZaposlenici())
			{
				for(Zahtjev zahtjev : zaposlenik.getZahtjevi())
				{
					Row redak = sheet.createRow(i);
					redak.createCell(0).setCellValue(organizacijskaJedinica.getNaziv());
					redak.createCell(1).setCellValue(zaposlenik.getIme() +" " + zaposlenik.getPrezime());
					redak.createCell(2).setCellValue(zaposlenik.getMaticni_broj());
					redak.createCell(3).setCellValue(zahtjev.getBroj_radnih_dana() * 3000);
					i++;
				}
			}
		}
		
		try
		{
			workbook.write(new FileOutputStream("D:/Izvjesca/IznosiRegresa.xls"));
			workbook.close();
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return workbook;
	}

	public void odobriZahtjev(int idZahtjev)
	{
		Zahtjev zahtjev = entityManager.find(Zahtjev.class, idZahtjev);
		zahtjev.setStatus_zahtjeva(entityManager.getReference(StatusZahtjeva.class, 2));
		entityManager.merge(zahtjev);
	}

	public void odbijZahtjev(int idZahtjev)
	{
		Zahtjev zahtjev = entityManager.find(Zahtjev.class, idZahtjev);
		zahtjev.setStatus_zahtjeva(entityManager.getReference(StatusZahtjeva.class, 3));
		entityManager.merge(zahtjev);
	}
}
