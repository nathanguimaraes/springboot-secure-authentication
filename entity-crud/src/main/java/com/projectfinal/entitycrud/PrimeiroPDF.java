package com.projectfinal.entitycrud;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class PrimeiroPDF {

	public PrimeiroPDF(String fraseAImprimir) {
		
		Document documentPDF = new Document();
		
		try {
		PdfWriter.getInstance(documentPDF, new FileOutputStream("Relatorio1.pdf"));
		
	documentPDF.open();		//abrindo documento
		
			Paragraph paragrafoTeste =  new Paragraph(fraseAImprimir);
			documentPDF.add(paragrafoTeste);
			
		
			
		}catch (DocumentException e) {
		
		e.printStackTrace();
		}catch(FileNotFoundException e) {
		e.printStackTrace();
		}
		
	documentPDF.close();	//fechando pdf
	
	}
	
}
