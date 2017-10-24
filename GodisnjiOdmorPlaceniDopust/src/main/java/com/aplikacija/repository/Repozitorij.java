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
import com.aplikacija.entities.StatusZahtjeva;
import com.aplikacija.entities.Zahtjev;
import com.aplikacija.entities.Zaposlenik;
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
		List<OrganizacijskaJedinica> organizacijskeJedinice = query.getResultList();
		return organizacijskeJedinice;
	}
	
	@SuppressWarnings("unchecked")
	public List<Zaposlenik> dohvatiZaposlenika(String korisnicko_ime)
	{
		Query query = entityManager.createQuery("from Zaposlenik where korisnicko_ime = :korisnicko_ime");
		query.setParameter("korisnicko_ime", korisnicko_ime);
		List<Zaposlenik> zaposlenici = query.getResultList();
		return zaposlenici;
	}
	
	@SuppressWarnings("unchecked")
	public List<PlaceniDopust> dohvatiTipovePlacenogDopusta()
	{
		Query query = entityManager.createQuery("from PlaceniDopust");
		List<PlaceniDopust> tipoviPlacenogDopusta = query.getResultList();
		return tipoviPlacenogDopusta;
	}
	
	public List<Zahtjev> dohvatiZahtjeve(Zaposlenik zaposlenik)
	{	
		List<Zahtjev> zahtjevi = zaposlenik.getZahtjevi();
		return zahtjevi;
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
}
