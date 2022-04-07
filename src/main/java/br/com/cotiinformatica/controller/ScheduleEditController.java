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
import br.com.cotiinformatica.models.ScheduleEditModel;

@Controller
public class ScheduleEditController {
	
	@Autowired
	private IScheduleRepository tarefaRepository;
		
	@RequestMapping(value="/schedule-edit")
	public ModelAndView scheduleEdit(HttpServletResponse response,Integer id
			,HttpServletRequest request) throws IOException{
		ModelAndView modelAndView = new ModelAndView("schedule-edit");
		
		ScheduleEditModel model = new ScheduleEditModel();
		
		try {
			
		User usuario = (User)request.getSession().getAttribute("usuario");
		
		Schedule tarefa = tarefaRepository.findById(id,usuario.getIdUsuario());
		
		model.setIdTarefa(tarefa.getIdTarefa());
		model.setNome(tarefa.getNome());
		model.setData(DateHelper.toString(tarefa.getData()));
		model.setHora(tarefa.getHora());
		model.setPrioridade(tarefa.getPrioridade());
		model.setDescricao(tarefa.getDescricao());
		
		}catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		modelAndView.addObject("model",model);
		modelAndView.addObject("prioridades",Priority.values());
		return modelAndView;
	}
	
	@RequestMapping(value="/post-edit")
	public ModelAndView postEdit(ScheduleEditModel model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("schedule-edit");
		boolean isValid = true;
		
		if(model.getNome().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("erro_nome","Digite um nome válido.");
		}
		
		if(model.getData().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("erro_data","Insira uma data válida.");
		}
		
		if(model.getHora().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("erro_hora","Insira uma hora válida.");
		}
		
		if(model.getPrioridade().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("erro_prioridade","Adicione uma prioridade válida.");
		}
		
		if(model.getDescricao().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("erro_descricao","Digite uma descrição válida.");
		}
		
		try {
		if(isValid) {
			
			Schedule tarefa = new Schedule();
			User usuario = (User)request.getSession().getAttribute("usuario");
			
			tarefa.setIdTarefa(model.getIdTarefa());
			tarefa.setNome(model.getNome());
			tarefa.setData(DateHelper.toDate(model.getData()));
			tarefa.setHora(model.getHora());
			tarefa.setPrioridade(model.getPrioridade());
			tarefa.setDescricao(model.getDescricao());
			tarefa.setUsuario(usuario);
			
			tarefaRepository.update(tarefa);
			
			modelAndView.addObject("sucesso_edit","Atualização feita com sucesso.");
			
		}else {
			throw new Exception();
		}
		}catch(Exception e) {
			modelAndView.addObject("erro_edit","Atualização não foi bem sucedida.");
		}
		
		modelAndView.addObject("model",model);
		modelAndView.addObject("prioridades",Priority.values());
		return modelAndView;
	}
}
