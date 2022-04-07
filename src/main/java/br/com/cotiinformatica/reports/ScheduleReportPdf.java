package br.com.cotiinformatica.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.cotiinformatica.entities.Schedule;
import br.com.cotiinformatica.entities.User;

public class ScheduleReportPdf {
	
	public ByteArrayInputStream create(Date dataMin, Date dataMax, User usuario,
			List<Schedule> tarefas) throws Exception{
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		Document document = new Document();
		
		PdfWriter writer = PdfWriter.getInstance(document, out);
		
		document.open();
		
		Image logo = Image.getInstance(new URL("https://www.cotiinformatica.com.br/imagens/logo-coti-informatica.png"));
		document.add(logo);
		document.add(new Paragraph("\n"));
		
		document.add(new Paragraph("Relatório de tarefas", new Font(FontFamily.COURIER,24)));
		document.add(new Paragraph("\n"));
		
		document.add(new Paragraph("Nome do Usuário: "+ usuario.getNome()));
		document.add(new Paragraph("E-mail do Usuário: "+usuario.getEmail()));
		document.add(new Paragraph("\n"));
		
		document.add(new Paragraph("Data Início: "+new SimpleDateFormat("dd/MM/yyyy").format(dataMin)));
		document.add(new Paragraph("Data Término: "+new SimpleDateFormat("dd/MM/yyyy").format(dataMax)));
		
		document.add(new Paragraph("\n"));
		
		PdfPTable table = new PdfPTable(5);//5 colunas
		table.setWidthPercentage(100);//100% de largura
		table.addCell("NOME DA TAREFA");
		table.addCell("DATA");
		table.addCell("HORA");
		table.addCell("PRIORIDADE");
		table.addCell("DESCRIÇÃO");
		
		for(Schedule t : tarefas) {
			table.addCell(t.getNome());
			table.addCell(new SimpleDateFormat("dd/MM/yyyy").format(t.getData()));
			table.addCell(t.getHora());
			table.addCell(t.getPrioridade());
			table.addCell(t.getDescricao());
		}
		
		document.add(table);
		
		document.add(new Paragraph("\nQuantidade de tarefas: "+tarefas.size()));
		
		document.close();
		
		writer.close();
		
		return new ByteArrayInputStream(out.toByteArray());
	}
	
}
