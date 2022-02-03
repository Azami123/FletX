package com.sistema.peliculas.util;

import java.awt.Color;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ServerProperties.Jetty.Accesslog.FORMAT;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sistema.peliculas.modelo.Pelicula;

@Component("admin/index")
public class ListarPeliculasPdf extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		Page<Pelicula> peliculas = (Page<Pelicula>) model.get("peliculas");
		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-20, -20, 40, 20);
		document.open();
		PdfPCell celda = null;
		//Nombre Titulo PDF
		PdfPTable tablaTitulo = new PdfPTable(1);

		Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.BLUE);
		Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLUE);
		Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER, 10, Color.BLACK);
		celda = new PdfPCell(new Phrase("LISTADO DE PELICULAS", fuenteTitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(40, 190, 138));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(30);

		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);
		//Tabla para mostrar el listado de Peliculas
		PdfPTable tablaPeliculas = new PdfPTable(3);
		tablaPeliculas.setWidths(new float[] {0.8f,2f,2f});
		
		celda = new PdfPCell(new Phrase("TITULO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaPeliculas.addCell(celda);
		
		celda = new PdfPCell(new Phrase("DESCRIPCIÓN", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaPeliculas.addCell(celda);
		
		celda = new PdfPCell(new Phrase("FECHA DE ESTRENO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaPeliculas.addCell(celda);
		

		peliculas.forEach(pelicula -> {

			tablaPeliculas.addCell(pelicula.getTitulo());
			tablaPeliculas.addCell(pelicula.getSinopsis());
			tablaPeliculas.addCell(pelicula.getFechaEstreno().toString());

		});
		document.add(tablaTitulo);
		document.add(tablaPeliculas);

	}

	private PdfPCell PdfPCell(Phrase phrase) {
		// TODO Auto-generated method stub
		return null;
	}

}
