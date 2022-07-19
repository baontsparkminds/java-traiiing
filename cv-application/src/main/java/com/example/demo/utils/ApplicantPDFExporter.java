package com.example.demo.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.entity.Applicant;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ApplicantPDFExporter {
	private List<Applicant> applicants;

	private Font getFont(boolean isBold) {
		return FontFactory.getFont(isBold ? FontFactory.TIMES_BOLD : FontFactory.TIMES_ROMAN);
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(5);

		Font font = getFont(true);

		font.setColor(Color.BLACK);

		cell.setPhrase(new Phrase("Email", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Github", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		Font font = getFont(false);
		PdfPCell cell = new PdfPCell();
		cell.setPadding(5);

		for (Applicant applicant : applicants) {
			cell.setPhrase(new Phrase(applicant.getEmail(), font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(applicant.getGithub(), font));
			table.addCell(cell);
		}
	}

	public String export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		Font font = getFont(true);

		document.open();
		font.setSize(18);
		font.setColor(Color.BLACK);

		Paragraph p = new Paragraph("List of Applicant", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 5f, 5f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

		return document.toString();
	}
}
