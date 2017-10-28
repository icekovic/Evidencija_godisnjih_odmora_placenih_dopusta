package com.aplikacija.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.aplikacija.entities.OrganizacijskaJedinica;
import com.aplikacija.entities.PlaceniDopust;
import com.aplikacija.entities.PodaciGodisnjiOdmor;
import com.aplikacija.entities.StatusZahtjeva;
import com.aplikacija.entities.Zahtjev;
import com.aplikacija.entities.Zaposlenik;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
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
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Zaposlenik> dohvatiZaposlenika(String korisnicko_ime)
	{
		Query query = entityManager.createQuery("from Zaposlenik where korisnicko_ime = :korisnicko_ime");
		query.setParameter("korisnicko_ime", korisnicko_ime);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<PlaceniDopust> dohvatiTipovePlacenogDopusta()
	{
		Query query = entityManager.createQuery("from PlaceniDopust");
		return query.getResultList();
	}
	
	public List<Zahtjev> dohvatiZahtjeve(Zaposlenik zaposlenik)
	{
		return zaposlenik.getZahtjevi();
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
	
	@SuppressWarnings("unchecked")
	public List<PodaciGodisnjiOdmor> dohvatiPodatkeZaGodisnjeOdmore()
	{
		Query query = entityManager.createQuery("from PodaciGodisnjiOdmor");
		return query.getResultList();
	}

	public void kreirajIzvjesceGodisnjihOdmora(List<PodaciGodisnjiOdmor> podaciGodisnjihOdmora)
	{
//		try
//		{
//			Document document = new Document(PageSize.A4.rotate());
//			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/Izvjesca/GodisnjiOdmori.pdf"));
//			writer.addPageDictEntry(PdfName.ROTATE, PdfPage.LANDSCAPE);
//			document.open();
//			document.add(new Paragraph("Broj dana godišnjih odmora po organizacijskim jedinicama", 
//					FontFactory.getFont(FontFactory.COURIER_BOLD, 18, Font.BOLD, BaseColor.BLACK)));
//			
//			PdfPTable table = new PdfPTable(9);
//			table.addCell("Organizacijska jedinica");
//			table.addCell("Zaposlenik");
//			table.addCell("Matični broj zaposlenika");
//			table.addCell("Broj dana godišnjeg odmora");
//			table.addCell("Godine staža");
//			table.addCell("Rola");
//			table.addCell("Tjelesno oštećenje/invalidnost");
//			table.addCell("Broj djece");
//			table.addCell("Starost djece");
//			
//			document.add(table);
//			
//			document.close();
//		}
//		catch (Exception ex)
//		{
//			ex.printStackTrace();
//		}
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Broj dana godišnjih odmora");
		
		Row redakNasloviStupaca = sheet.createRow(0);
		redakNasloviStupaca.createCell(0).setCellValue("Organizacijska jedinica");
		redakNasloviStupaca.createCell(1).setCellValue("Zaposlenik");
		redakNasloviStupaca.createCell(2).setCellValue("Maticni broj zaposlenika");
		redakNasloviStupaca.createCell(3).setCellValue("Broj dana godišnjeg odmora");
		redakNasloviStupaca.createCell(4).setCellValue("Godine staža");
		redakNasloviStupaca.createCell(5).setCellValue("Rola");
		redakNasloviStupaca.createCell(6).setCellValue("Tjelesno oštećenje/invalidnost");
		redakNasloviStupaca.createCell(7).setCellValue("Broj djece");
		redakNasloviStupaca.createCell(8).setCellValue("Starost djece");
		
		for(int i = 1; i < podaciGodisnjihOdmora.size(); i++)
		{
			Row redak = sheet.createRow(i);
			redak.createCell(0).setCellValue(podaciGodisnjihOdmora.get(i).getOrganizacijska_jedinica());
			redak.createCell(1).setCellValue(podaciGodisnjihOdmora.get(i).getZaposlenik());
			redak.createCell(2).setCellValue(podaciGodisnjihOdmora.get(i).getMaticni_broj_zaposlenika());
			redak.createCell(3).setCellValue(podaciGodisnjihOdmora.get(i).getBroj_dana_godisnjeg_odmora());
			redak.createCell(4).setCellValue(podaciGodisnjihOdmora.get(i).getGodine_staza());
			redak.createCell(5).setCellValue(podaciGodisnjihOdmora.get(i).getRola());
			redak.createCell(6).setCellValue(podaciGodisnjihOdmora.get(i).getTjelesno_ostecenje_invalidnost());
			redak.createCell(7).setCellValue(podaciGodisnjihOdmora.get(i).getBroj_djece());
			redak.createCell(8).setCellValue(podaciGodisnjihOdmora.get(i).getStarost_djece());
		}
	
		try
		{
			workbook.write(new FileOutputStream("D:/Izvjesca/GodisnjiOdmori.xls"));
			workbook.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}		
	}
}
