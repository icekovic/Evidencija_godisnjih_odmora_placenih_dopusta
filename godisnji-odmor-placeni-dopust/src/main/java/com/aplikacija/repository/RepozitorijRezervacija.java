package com.aplikacija.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.aplikacija.entities.Grad;
import com.aplikacija.entities.Hotel;
import com.aplikacija.entities.Rezervacija;
import com.aplikacija.entities.Zaposlenik;

@Repository
@Transactional
public class RepozitorijRezervacija
{
	@PersistenceContext
	private EntityManager entityManager;
		
	@SuppressWarnings("unchecked")
	public List<Grad> dohvatiGradove()
	{
		Query query = entityManager.createQuery("from Grad");
		return query.getResultList();
	}
	
	public List<Hotel> dohvatiHotele(String odabraniGrad)
	{
		Query query = entityManager.createQuery("from Grad where naziv = :odabraniGrad");
		query.setParameter("odabraniGrad", odabraniGrad);
		Grad grad = (Grad) query.getResultList().get(0);	
		return grad.getHoteli();
	}

	public Hotel dohvatiHotel(String nazivHotela)
	{
		Query query = entityManager.createQuery("from Hotel where naziv = :nazivHotela");
		query.setParameter("nazivHotela", nazivHotela);
		Hotel hotel = (Hotel) query.getResultList().get(0);
		return hotel;
	}

	public void rezervirajSobu(Rezervacija rezervacija)
	{
		entityManager.persist(rezervacija);
	}

	public List<Rezervacija> dohvatiRezervacije(Zaposlenik zaposlenik)
	{
		return zaposlenik.getRezervacije();
	}
}
