package com.aplikacija.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.aplikacija.entities.OrganizacijskaJedinica;
import com.aplikacija.entities.PlaceniDopust;
import com.aplikacija.entities.PodaciGodisnjiOdmor;
import com.aplikacija.entities.PodaciPlaceniDopust;
import com.aplikacija.entities.StatusZahtjeva;
import com.aplikacija.entities.Zahtjev;
import com.aplikacija.entities.Zaposlenik;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class Repozitorij
{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	public Repozitorij(JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrganizacijskaJedinica> dohvatiOrganizacijskeJedinice()
	{
		Query query = entityManager.createQuery("from OrganizacijskaJedinica");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Zaposlenik> dohvatiZaposlenika(String korisnicko_ime)
	{
		Query query = entityManager.createQuery("from Zaposlenik where korisnicko_ime = :korisnicko_ime");
		query.setParameter("korisnicko_ime", korisnicko_ime);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<PlaceniDopust> dohvatiTipovePlacenogDopusta()
	{
		Query query = entityManager.createQuery("from PlaceniDopust");
		return query.getResultList();
	}
	
	public List<Zahtjev> dohvatiZahtjeve(Zaposlenik zaposlenik)
	{
		return zaposlenik.getZahtjevi();
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

	public void posaljiMailRukovoditelju(Zahtjev zahtjev, Zaposlenik zaposlenik) throws MailException
	{
		SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo("dwetherburn@gmail.com");
	    message.setFrom(zaposlenik.getEmail());
	    message.setSubject(zahtjev.getTip());
	    message.setText("Zaposlenik: " + zaposlenik.getIme() + " " + zaposlenik.getPrezime() + "\nTip zahtjeva: " + zahtjev.getTip() + "\nOd datuma: " + 
	    zahtjev.getOd_datuma() + "\nDo datuma: " + zahtjev.getDo_datuma());
	    mailSender.send(message);
	}
	
	@SuppressWarnings("unchecked")
	public List<PodaciGodisnjiOdmor> dohvatiPodatkeZaGodisnjeOdmore()
	{
		Query query = entityManager.createQuery("from PodaciGodisnjiOdmor");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<PodaciPlaceniDopust> dohvatiPodatkeZaPlaceneDopuste()
	{
		Query query = entityManager.createQuery("from PodaciPlaceniDopust");
		return query.getResultList();
	}

	public HSSFWorkbook kreirajIzvjesceGodisnjihOdmora(List<PodaciGodisnjiOdmor> podaciGodisnjihOdmora)
	{		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Korištenje godišnjeg odmora");
		
		Row redakNasloviStupaca = sheet.createRow(0);
		redakNasloviStupaca.createCell(0).setCellValue("Organizacijska jedinica");
		redakNasloviStupaca.createCell(1).setCellValue("Zaposlenik");
		redakNasloviStupaca.createCell(2).setCellValue("Maticni broj zaposlenika");
		redakNasloviStupaca.createCell(3).setCellValue("Broj dana godišnjeg odmora");
		redakNasloviStupaca.createCell(4).setCellValue("Godine staža");
		redakNasloviStupaca.createCell(5).setCellValue("Rola");
		redakNasloviStupaca.createCell(6).setCellValue("Tjelesno oštećenje/invalidnost");
		redakNasloviStupaca.createCell(7).setCellValue("Broj djece");
		redakNasloviStupaca.createCell(8).setCellValue("Starost djece");
		
		for(int i = 1; i < podaciGodisnjihOdmora.size(); i++)
		{
			Row redak = sheet.createRow(i);
			redak.createCell(0).setCellValue(podaciGodisnjihOdmora.get(i).getOrganizacijska_jedinica());
			redak.createCell(1).setCellValue(podaciGodisnjihOdmora.get(i).getZaposlenik());
			redak.createCell(2).setCellValue(podaciGodisnjihOdmora.get(i).getMaticni_broj_zaposlenika());
			redak.createCell(3).setCellValue(podaciGodisnjihOdmora.get(i).getBroj_dana_godisnjeg_odmora());
			redak.createCell(4).setCellValue(podaciGodisnjihOdmora.get(i).getGodine_staza());
			redak.createCell(5).setCellValue(podaciGodisnjihOdmora.get(i).getRola());
			redak.createCell(6).setCellValue(podaciGodisnjihOdmora.get(i).getTjelesno_ostecenje_invalidnost());
			redak.createCell(7).setCellValue(podaciGodisnjihOdmora.get(i).getBroj_djece());
			redak.createCell(8).setCellValue(podaciGodisnjihOdmora.get(i).getStarost_djece());
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
	
	public HSSFWorkbook kreirajIzvjescePlacenihDopusta(List<PodaciPlaceniDopust> podaciPlacenihDopusta)
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Korištenje plaćenog dopusta");
		
		Row redakNasloviStupaca = sheet.createRow(0);
		redakNasloviStupaca.createCell(0).setCellValue("Organizacijska jedinica");
		redakNasloviStupaca.createCell(1).setCellValue("Zaposlenik");
		redakNasloviStupaca.createCell(2).setCellValue("Maticni broj zaposlenika");
		redakNasloviStupaca.createCell(3).setCellValue("Tip plaćenog dopusta");
		redakNasloviStupaca.createCell(4).setCellValue("Broj dana plaćenog dopusta");
		
		for(int i = 1; i < podaciPlacenihDopusta.size(); i++)
		{
			Row redak = sheet.createRow(i);
			redak.createCell(0).setCellValue(podaciPlacenihDopusta.get(i).getOrganizacijska_jedinica());
			redak.createCell(1).setCellValue(podaciPlacenihDopusta.get(i).getZaposlenik());
			redak.createCell(2).setCellValue(podaciPlacenihDopusta.get(i).getMaticni_broj_zaposlenika());
			redak.createCell(3).setCellValue(podaciPlacenihDopusta.get(i).getTip_placenog_dopusta());
			redak.createCell(4).setCellValue(podaciPlacenihDopusta.get(i).getBroj_dana_placenog_dopusta());
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
}
