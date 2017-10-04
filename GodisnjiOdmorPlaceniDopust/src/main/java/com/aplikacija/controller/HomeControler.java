package com.aplikacija.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aplikacija.model.*;
import com.aplikacija.service.IRepozitorij;

import java.util.List;

@Controller
public class HomeControler
{
	private IRepozitorij repozitorij;
	
	@RequestMapping("/")
	public String home()
	{
		return "home";
	}
	
	@RequestMapping("/registracija")
	public String registracija()
	{
		//List<OrganizacijskaJedinica> organizacijskeJedinice = repozitorij.dohvatiOrganizacijskeJedinice();
		return "registracija";
	}
}