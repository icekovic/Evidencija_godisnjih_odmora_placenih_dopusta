package com.aplikacija.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.web.bind.annotation.SessionAttributes;

@Entity
@SessionAttributes("zaposlenik")
public class Zaposlenik
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_zaposlenik;
	
	private String ime;
	private String prezime;
	private String email;
	private String korisnicko_ime;
	private String lozinka;
	private String maticni_broj;	
	private String datum_zaposlenja;	
	private String tjelesno_ostecenje_invalidnost;
	private int godine_staza;
	private int broj_djece;
	
	@ManyToOne
	@JoinColumn(name = "organizacijska_jedinica_id")
	private OrganizacijskaJedinica organizacijska_jedinica;
	
	@ManyToOne
	@JoinColumn(name = "rola_id")
	private Rola rola;
	
	@OneToMany(mappedBy = "zaposlenik")
	private List<Dijete> djeca;
	
	@OneToMany(mappedBy = "zaposlenik")
	private List<Zahtjev> zahtjevi;
	
	public Zaposlenik()	
	{
		
	}

	public int getId_zaposlenik()
	{
		return id_zaposlenik;
	}

	public void setId_zaposlenik(int id_zaposlenik)
	{
		this.id_zaposlenik = id_zaposlenik;
	}

	public String getIme()
	{
		return ime;
	}

	public void setIme(String ime)
	{
		this.ime = ime;
	}

	public String getPrezime()
	{
		return prezime;
	}

	public void setPrezime(String prezime)
	{
		this.prezime = prezime;
	}
	

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getKorisnicko_ime()
	{
		return korisnicko_ime;
	}

	public void setKorisnicko_ime(String korisnicko_ime)
	{
		this.korisnicko_ime = korisnicko_ime;
	}

	public String getLozinka()
	{
		return lozinka;
	}

	public void setLozinka(String lozinka)
	{
		this.lozinka = lozinka;
	}

	public String getMaticni_broj()
	{
		return maticni_broj;
	}

	public void setMaticni_broj(String maticni_broj)
	{
		this.maticni_broj = maticni_broj;
	}

	public String getDatum_zaposlenja()
	{
		return datum_zaposlenja;
	}

	public void setDatum_zaposlenja(String datum_zaposlenja)	
	{
		this.datum_zaposlenja = datum_zaposlenja;
	}

	public String getTjelesno_ostecenje_invalidnost()
	{
		return tjelesno_ostecenje_invalidnost;
	}

	public void setTjelesno_ostecenje_invalidnost(String tjelesno_ostecenje_invalidnost)
	
	{
		this.tjelesno_ostecenje_invalidnost = tjelesno_ostecenje_invalidnost;
	}

	public int getGodine_staza()
	{
		return godine_staza;
	}

	public void setGodine_staza(int godine_staza)
	{
		this.godine_staza = godine_staza;
	}

	public int getBroj_djece()
	{
		return broj_djece;
	}

	public void setBroj_djece(int broj_djece)
	{
		this.broj_djece = broj_djece;
	}

	public OrganizacijskaJedinica getOrganizacijska_jedinica()
	{
		return organizacijska_jedinica;
	}

	public void setOrganizacijska_jedinica(OrganizacijskaJedinica organizacijska_jedinica)
	{
		this.organizacijska_jedinica = organizacijska_jedinica;
	}

	public Rola getRola()
	{
		return rola;
	}

	public void setRola(Rola rola)
	{
		this.rola = rola;
	}

	public List<Dijete> getDjeca()
	{
		return djeca;
	}

	public void setDjeca(List<Dijete> djeca)
	{
		this.djeca = djeca;
	}

	public List<Zahtjev> getZahtjevi()
	{
		return zahtjevi;
	}

	public void setZahtjevi(List<Zahtjev> zahtjevi)
	{
		this.zahtjevi = zahtjevi;
	}
	
	
}
