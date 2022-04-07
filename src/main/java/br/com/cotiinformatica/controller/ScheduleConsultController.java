package br.com.cotiinformatica.controller;

import java.io.IOException;
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
import br.com.cotiinformatica.models.ScheduleConsultModel;

@Controller
public class ScheduleConsultController {
		
	@Autowired
	private IScheduleRepository tarefaRepository;

	@RequestMapping(value="/schedule-consult")
	public ModelAndView scheduleConsult(HttpServletResponse response,HttpServletRequest request) 
			throws IOException{
		
		ModelAndView modelAndView = new ModelAndView("schedule-consult");
		
		modelAndView.addObject("model", new ScheduleConsultModel());
		return modelAndView;	
	}
	
	@RequestMapping(value="/post-consult")
	public ModelAndView resutlConsult(ScheduleConsultModel model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("schedule-consult");
		boolean isValid = true;
		
		try {
		if(model.getDataMin().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("erro_dataMin","Por favor insira a data de início da tarefa.");
		}
		
		if(model.getDataMax().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("erro_dataMax","Por favor insira a data de término da tarefa.");
		}
		
		if(isValid) {
			
			User usuario = (User)request.getSession().getAttribute("usuario");
			
			Date dataMin = DateHelper.toDate(model.getDataMin());
			Date dataMax = DateHelper.toDate(model.getDataMax());
			
			List<Schedule> tarefas = tarefaRepository.findByDatas(dataMin,dataMax,
					usuario.getIdUsuario());
			
			modelAndView.addObject("tarefas",tarefas);
			
			int qtdBaixa = 0;
			int qtdMedia = 0;
			int qtdAlta = 0;
			
			for(Schedule t : tarefas) {
				if(t.getPrioridade().equals("Baixa"))qtdBaixa++;
				else if(t.getPrioridade().equals("Media"))qtdMedia++;
				else if(t.getPrioridade().equals("Alta"))qtdAlta++;
			}
			
			modelAndView.addObject("qtdBaixa",qtdBaixa);
			modelAndView.addObject("qtdMedia",qtdMedia);
			modelAndView.addObject("qtdAlta",qtdAlta);
			
		}else {
			throw new Exception();
		}
		
		}catch(Exception e){
			modelAndView.addObject("erro_consulta",e.getMessage());
		}
		modelAndView.addObject("model", model);
		return modelAndView;
	}
	
	@RequestMapping(value="/schedule-delete")
	public ModelAndView deleteSchedule(int id,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("schedule-consult");
		modelAndView.addObject("model",new ScheduleConsultModel());
		
		try {
			
		User usuario = (User)request.getSession().getAttribute("usuario");
			
		Schedule tarefa = tarefaRepository.findById(id, usuario.getIdUsuario());
		
		tarefa.setUsuario(usuario);
		
		tarefaRepository.delete(tarefa);
		
		modelAndView.addObject("mensagem_sucesso","Tarefa "+tarefa.getNome()+", foi deletada com"
				+ " sucesso.");
		
		}catch(Exception e) {
			modelAndView.addObject("mensagem_erro",e.getMessage());
		}
		
		return modelAndView;
	}
}
