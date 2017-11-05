package com.aplikacija.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.aplikacija.entities.Grad;
import com.aplikacija.entities.Hotel;

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
	
	@SuppressWarnings("unchecked")
	public List<Hotel> dohvatiHotele()
	{
		Query query = entityManager.createQuery("from Hotel");
//		query.setParameter("gradId", gradId);
		return query.getResultList();
	}
}
