package com.aplikacija.service;

import org.springframework.stereotype.Repository;

import com.aplikacija.model.OrganizacijskaJedinica;

import java.util.List;

@Repository
public interface IRepozitorij
{
	List<OrganizacijskaJedinica> dohvatiOrganizacijskeJedinice();
}
