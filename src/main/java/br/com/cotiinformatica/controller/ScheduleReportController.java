package br.com.cotiinformatica.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Schedule;
import br.com.cotiinformatica.entities.User;
import br.com.cotiinformatica.helpers.DateHelper;
import br.com.cotiinformatica.interfaces.IScheduleRepository;
import br.com.cotiinformatica.models.ScheduleReportModel;
import br.com.cotiinformatica.reports.ScheduleReportPdf;

@Controller
public class ScheduleReportController {
		
	@Autowired
	private IScheduleRepository tarefaRepository;
	
	
	@RequestMapping(value="/schedule-report")
	public ModelAndView scheduleReport(HttpServletResponse response, HttpServletRequest request) 
			throws IOException{
		ModelAndView modelAndView = new ModelAndView("schedule-report");
		modelAndView.addObject("model", new ScheduleReportModel());
		return modelAndView;
	}
	
	@RequestMapping(value="/post-report")
	public ModelAndView postReport(ScheduleReportModel model,HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		ModelAndView modelAndView = new ModelAndView("schedule-report");
		boolean isValid = true;
		
		if(model.getDataMin().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("erro_dataMin","Por favor informa a data de início.");
		}
		
		if(model.getDataMax().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("erro_dataMax","Por favor informa a data de término.");
		}
		
		try {
		if(isValid) {
			
		User usuario = (User)request.getSession().getAttribute("usuario");
			
		Date dataMin = DateHelper.toDate(model.getDataMin());
		Date dataMax = DateHelper.toDate(model.getDataMax());
		
		List<Schedule> tarefas = tarefaRepository.findByDatas(dataMin, dataMax, usuario.getIdUsuario());
			
		ScheduleReportPdf tarefaReport = new ScheduleReportPdf();
		ByteArrayInputStream stream = tarefaReport.create(dataMin, dataMax, usuario, tarefas);
			
		response.setContentType("application/pdf");
		response.addHeader("Content-disposition", "attachment; filename=schedule_report.pdf");
		
		byte[] data = stream.readAllBytes();
		
		OutputStream out = response.getOutputStream();
		out.write(data,0,data.length);
		out.flush();
		out.close();
		
		response.getOutputStream().flush();
		return null;
		
		}else{
			throw new Exception();
		}
		
		}catch(Exception e) {
			modelAndView.addObject("mensagem_erro",e.getMessage());
		}
		
		modelAndView.addObject("model", model);
		return modelAndView;
	}
}
