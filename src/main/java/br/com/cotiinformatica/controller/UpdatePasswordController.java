package br.com.cotiinformatica.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.User;
import br.com.cotiinformatica.interfaces.IUsuarioRepository;
import br.com.cotiinformatica.models.UpdatePasswordModel;

@Controller
public class UpdatePasswordController {


	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@RequestMapping(value="/update-password")
	public ModelAndView updatePassword(HttpServletResponse response, HttpServletRequest request)
			throws IOException{
		ModelAndView modelAndView = new ModelAndView("update-password");
		modelAndView.addObject("model",new UpdatePasswordModel());
		return modelAndView;
	}
	
	@RequestMapping(value="/post-update")
	public ModelAndView postUpdate(UpdatePasswordModel model,HttpServletResponse response, 
			HttpServletRequest request)throws IOException{
		ModelAndView modelAndView = new ModelAndView("update-password");
		boolean isValid = true;
		
		if(model.getSenhaAtual().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("senha_vazia","Por favor insira sua senha.");
		}
		if(model.getNovaSenha().trim().length()==0) {
			isValid = false;
			modelAndView.addObject("nova_senha_vazia","Por favor insira sua nova senha.");
		}
		if(model.getSenhaConfirmacao().trim().length()==0 || !model.getSenhaConfirmacao().equals
				(model.getNovaSenha())) {
			isValid = false;
			modelAndView.addObject("erro_senha_confirmacao","Senhas não conferem.");
		}
		
		try {
		User usuario = (User)request.getSession().getAttribute("usuario");
		if(usuarioRepository.findByEmailAndSenha(usuario.getEmail(), model.getSenhaAtual()) == null) {
			isValid = false;
			modelAndView.addObject("erro_senhaAtual","Senha digitada não condiz com a senha do "
					+ "usuário logado.");
		}	
		if(isValid) {
			
			usuarioRepository.updatePassword(usuario.getIdUsuario(), model.getNovaSenha());
			modelAndView.addObject("mensagem_sucesso","Senha alterada com sucesso");
			
		}else {
			throw new Exception();
		}
		}catch(Exception e) {
			modelAndView.addObject("erro_mensagem",e.getMessage());
		}
		modelAndView.addObject("model",model);
		return modelAndView;
	}
}
