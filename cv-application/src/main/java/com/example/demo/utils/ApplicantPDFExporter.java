package com.example.demo.utils;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Applicant;
import com.example.demo.service.dto.GithubInformationDto;
import com.google.gson.Gson;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ApplicantPDFExporter {

	private final static RestTemplate restTemplate = new RestTemplate();

	private static Font getFont(boolean isBold) {
		return FontFactory.getFont(isBold ? FontFactory.TIMES_BOLD : FontFactory.TIMES_ROMAN);
	}

	private static GithubInformationDto getGithubInformation(String userName) throws IOException {
		ResponseEntity<String> response = restTemplate.exchange("https://api.github.com/search/users?q=" + userName,
				HttpMethod.GET, null, String.class);
		Gson gson = new Gson();
		return gson.fromJson(response.getBody(), GithubInformationDto.class);
	}

	private static void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(5);

		Font font = getFont(true);

		font.setColor(Color.BLACK);

		cell.setPhrase(new Phrase("Avatar", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Email", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Github", font));
		table.addCell(cell);
	}

	private static void writeTableData(PdfPTable table, List<Applicant> applicants)
			throws BadElementException, MalformedURLException, IOException {
		Font font = getFont(false);
		PdfPCell cell = new PdfPCell();
		cell.setPadding(5);

		for (Applicant applicant : applicants) {
			GithubInformationDto github = getGithubInformation(applicant.getGithub());

			String url = github.getItems().isEmpty() ? "https://cdn-icons-png.flaticon.com/512/25/25231.png?w=360"
					: github.getItems().get(0).getAvatarUrl();

			Image img = Image.getInstance(new URL(url));
			cell.setImage(img);
			table.addCell(cell);
			cell.setPhrase(new Phrase(applicant.getEmail(), font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(applicant.getGithub(), font));
			table.addCell(cell);
		}
	}

	public static byte[] export(List<Applicant> applicants)
			throws DocumentException, MalformedURLException, IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, byteArrayOutputStream);

		Font font = getFont(true);

		document.open();
		font.setSize(18);
		font.setColor(Color.BLACK);

		Paragraph p = new Paragraph("List of Applicant", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 2f, 4f, 4f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table, applicants);

		document.add(table);

		document.close();

		return byteArrayOutputStream.toByteArray();
	}
}
