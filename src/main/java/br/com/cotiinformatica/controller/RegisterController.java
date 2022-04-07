package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.User;
import br.com.cotiinformatica.interfaces.IUsuarioRepository;
import br.com.cotiinformatica.models.RegisterModel;

@Controller
public class RegisterController {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@RequestMapping("/register")
	public ModelAndView register(HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("register");
		modelAndView.addObject("model", new RegisterModel());
		return modelAndView;
	}

	@RequestMapping(value = "post-register", method = RequestMethod.POST)
	public ModelAndView postRegister(RegisterModel model) {
		ModelAndView modelAndView = new ModelAndView("register");
		try {
		boolean isValid = true;
		if (model.getNome().trim().length() == 0) {
			modelAndView.addObject("erro_nome", "Por favor informe seu nome.");
			isValid = false;
		}
		if (model.getEmail().trim().length() == 0) {
			modelAndView.addObject("erro_email", "Por favor informe seu e-mail.");
			isValid = false;
		}
		if (model.getSenha().trim().length() == 0) {
			modelAndView.addObject("erro_senha", "Por favor informe sua senha.");
			isValid = false;
		}
		if (!model.getSenhaConfirmacao().equals(model.getSenha())) {
			modelAndView.addObject("erro_senhac", "Senhas não conferem, verifique novamente.");
			isValid = false;
		}
		
		if(usuarioRepository.findByEmail(model.getEmail()) != null) {
			modelAndView.addObject("erro_email","E-mail já cadastrado no sistema, tente outro.");
			isValid = false;
		}
		
		if(isValid) {
			User usuario = new User();
			usuario.setNome(model.getNome());
			usuario.setEmail(model.getEmail());
			usuario.setSenha(model.getSenha());
			
			usuarioRepository.create(usuario);
			
			modelAndView.addObject("sucesso_cadastro","Usúario de acesso cadastrado com sucesso!");
			model = new RegisterModel();
		}else {
			throw new Exception();
		}
		
		}catch (Exception e) {
			modelAndView.addObject("erro_cadastro","Dados não fornecidos corretamente!");
		}
		modelAndView.addObject("model", model);
		return modelAndView;
	}
}
