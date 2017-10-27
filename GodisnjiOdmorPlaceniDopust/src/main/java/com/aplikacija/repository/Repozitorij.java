package com.aplikacija.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.aplikacija.entities.OrganizacijskaJedinica;
import com.aplikacija.entities.PlaceniDopust;
import com.aplikacija.entities.PodaciGodisnjiOdmor;
import com.aplikacija.entities.StatusZahtjeva;
import com.aplikacija.entities.Zahtjev;
import com.aplikacija.entities.Zaposlenik;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
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

	public void kreirajIzvjesceGodisnjihOdmoraPdf(List<PodaciGodisnjiOdmor> podaciGodisnjihOdmora)
	{
		try
		{
			Document document = new Document();
			//PdfWriter.getInstance(document, new FileOutputStream("D:/Izvjesca/GodisnjiOdmori.pdf"));
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/Izvjesca/GodisnjiOdmori.pdf"));
			writer.addPageDictEntry(PdfName.ROTATE, PdfPage.LANDSCAPE);
			document.open();
			document.add(new Paragraph("Godisnji odmori"));
			document.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
}
