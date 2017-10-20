package com.aplikacija.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.aplikacija.entities.OrganizacijskaJedinica;
import com.aplikacija.entities.PlaceniDopust;
import com.aplikacija.entities.Zaposlenik;
import java.util.List;

@Repository
@Transactional
public class Repozitorij
{
	@PersistenceContext
	private EntityManager entityManager;
	
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
}
