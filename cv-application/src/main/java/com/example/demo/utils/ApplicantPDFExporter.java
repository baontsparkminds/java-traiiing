package com.example.demo.utils;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.example.demo.entity.Applicant;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ApplicantPDFExporter {

    private static Font getFont(boolean isBold) {
        return FontFactory.getFont(isBold ? FontFactory.TIMES_BOLD : FontFactory.TIMES_ROMAN);
    }

    private static void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        Font font = getFont(true);

        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Github", font));
        table.addCell(cell);
    }

    private static void writeTableData(PdfPTable table, List<Applicant> applicants) {
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

    public static byte[] export(List<Applicant> applicants) throws DocumentException {
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

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 5f, 5f });
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, applicants);

        document.add(table);

        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}
