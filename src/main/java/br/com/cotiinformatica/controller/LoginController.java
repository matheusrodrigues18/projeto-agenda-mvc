package br.com.cotiinformatica.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.interfaces.IUsuarioRepository;
import br.com.cotiinformatica.models.LoginModel;

@Controller
public class LoginController {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@RequestMapping(value="/")
	public ModelAndView home(HttpServletResponse response, HttpServletRequest request)
			throws IOException{
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("model",new LoginModel());
		return modelAndView;
	}
	
	@RequestMapping(value="post-login",method=RequestMethod.POST)
	public ModelAndView postHome(LoginModel model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("login");
		try {
			
		boolean isValid = true;
		
		if(model.getEmail().trim().length()==0) {
			modelAndView.addObject("erro_email","Por favor informe seu e-mail de acesso.");
			isValid = false;
		}
		if(model.getSenha().trim().length()==0) {
			modelAndView.addObject("erro_senha","Por favor informe sua senha de acesso.");
			isValid = false;
		}
		if(usuarioRepository.findByEmailAndSenha(model.getEmail(), model.getSenha())==null) {
			isValid = false;
		}
		if(isValid) {
			
			//gravar dados em uma sessão
			request.getSession().setAttribute("usuario", usuarioRepository.findByEmailAndSenha(model.getEmail(), model.getSenha()));
			
			
			
			modelAndView.setViewName("redirect:schedule-consult");
		}else {
			throw new Exception();
		}
		}catch(Exception e) {
			modelAndView.addObject("erro_login","Erro! dados não cadastrados, tente novamente.");
		}
		modelAndView.addObject("model", model);
		return modelAndView;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletResponse response, HttpServletRequest request)
			throws IOException{
		request.getSession().removeAttribute("usuario");
		
		//redirecionar para a página de login
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/");
		
		return modelAndView;
	}
}
