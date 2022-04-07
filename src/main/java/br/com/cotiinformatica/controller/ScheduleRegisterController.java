package br.com.cotiinformatica.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Schedule;
import br.com.cotiinformatica.entities.User;
import br.com.cotiinformatica.enums.Priority;
import br.com.cotiinformatica.helpers.DateHelper;
import br.com.cotiinformatica.interfaces.IScheduleRepository;
import br.com.cotiinformatica.models.ScheduleRegisterModel;

@Controller
public class ScheduleRegisterController {
	
	@Autowired
	private IScheduleRepository tarefaRepository;
	
		
	@RequestMapping(value="/schedule-register")
	public ModelAndView scheduleRegister(HttpServletResponse response, HttpServletRequest request) 
			throws IOException{
		ModelAndView modelAndView = new ModelAndView("schedule-register");
		modelAndView.addObject("model",new ScheduleRegisterModel());
		modelAndView.addObject("prioridades",Priority.values());
		return modelAndView;
	}
	
	@RequestMapping(value="/post-register-schedule")
	public ModelAndView postRegisterSchedule(ScheduleRegisterModel model,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("schedule-register");
		boolean isValid = true;
		
		try {
		if(model.getNome().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("erro_nome","Por favor informe o nome da tarefa.");
		}
		
		if(model.getData().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("erro_data","Por favor informe a data da tarefa.");
		}
		
		if(model.getHora().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("erro_hora","Por favor informe a hora da tarefa.");
		}
		
		if(model.getPrioridade().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("erro_prioridade","Por favor informe a prioridade da tarefa.");
		}
		
		if(model.getDescricao().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("erro_descricao","Por favor informe a descrição da tarefa.");
		}
		
		if(isValid) {
	
			  Schedule tarefa = new Schedule();
			  
			  tarefa.setNome(model.getNome());
			  //tarefa.setData(new SimpleDateFormat("yyyy-MM-dd").parse(model.getData()));
			  tarefa.setData(DateHelper.toDate(model.getData()));
			  tarefa.setHora(model.getHora());
			  tarefa.setDescricao(model.getDescricao());
			  tarefa.setPrioridade(model.getPrioridade());
			  tarefa.setUsuario((User)request.getSession().getAttribute("usuario"));
			  
			  tarefaRepository.create(tarefa);
			
			  modelAndView.addObject("mensagem_sucesso","Tarefa cadastrada com sucesso!");
			  model = new ScheduleRegisterModel();
		}else {
			throw new Exception();
		}
		
		}catch(Exception e) {
			modelAndView.addObject("erro_cadastro","Dados não fornecidos corretamente!");
		}
		
		modelAndView.addObject("model",model);
		modelAndView.addObject("prioridades",Priority.values());
		return modelAndView;
	}
}
