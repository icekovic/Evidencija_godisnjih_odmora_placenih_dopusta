package com.aplikacija.repository;

import java.util.ArrayList;
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
import com.aplikacija.service.IRepozitorijRezervacija;

@Repository
@Transactional
public class RepozitorijRezervacija implements IRepozitorijRezervacija
{
	@PersistenceContext
	private EntityManager entityManager;
	
	public void rezervirajSobu(Rezervacija rezervacija)
	{
		entityManager.persist(rezervacija);
	}
	
	@SuppressWarnings("unchecked")
	public List<Rezervacija> dohvatiSveRezervacije()
	{
		Query query = entityManager.createQuery("from Rezervacija");
		return query.getResultList();
	}
		
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

	public List<Rezervacija> dohvatiRezervacije(Zaposlenik zaposlenik)
	{
		List<Rezervacija> rezervacije = new ArrayList<>();
		for(Rezervacija rezervacija : dohvatiSveRezervacije())
		{
			if(rezervacija.getZaposlenik().getId_zaposlenik() == zaposlenik.getId_zaposlenik())
			{
				rezervacije.add(rezervacija);
			}
		}
		
		return rezervacije;
	}
}
