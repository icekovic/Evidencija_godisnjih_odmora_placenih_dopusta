package com.aplikacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.aplikacija.repository.Repozitorij;

@Component
public class Configuration
{
	private Repozitorij repozitorij;
	
	@Autowired
	public Configuration(Repozitorij repozitorij)
	{
		this.repozitorij = repozitorij;
	}
}
