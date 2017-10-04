package com.aplikacija.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aplikacija.model.OrganizacijskaJedinica;

public class Repozitorij implements IRepozitorij
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<OrganizacijskaJedinica> dohvatiOrganizacijskeJedinice()
	{
		List<OrganizacijskaJedinica> organizacijskeJedinice = null;
		try(Session session = sessionFactory.openSession())
		{
			Query query = session.createQuery("from OrganizacijskaJedinica");
			organizacijskeJedinice = query.list();
		}
		return organizacijskeJedinice;
	}
}
