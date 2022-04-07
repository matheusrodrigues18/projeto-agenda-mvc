package br.com.cotiinformatica.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.User;
import br.com.cotiinformatica.interfaces.IUsuarioRepository;
import br.com.cotiinformatica.messages.MailMessage;
import br.com.cotiinformatica.models.ForgotPasswordModel;

@Controller
public class ForgotPasswordController {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@RequestMapping(value="/forgot-password")
	public ModelAndView forgotPassword(HttpServletResponse response, HttpServletRequest request)
			throws IOException{
		ModelAndView modelAndView = new ModelAndView("forgot-password");
		modelAndView.addObject("model",new ForgotPasswordModel());
		return modelAndView;
	}
	
	@RequestMapping(value="/post-resolution")
	public ModelAndView postAction(ForgotPasswordModel model,HttpServletResponse response,
			HttpServletRequest request)throws IOException{
		ModelAndView modelAndView = new ModelAndView("forgot-password");
		boolean isValid = true;
		
		if(model.getEmail()==null || model.getEmail().trim().length() == 0) {
			isValid = false;
			modelAndView.addObject("erro_email","Por favor insira seu e-mail.");
		}
		
		try {
		if(isValid) {
			User usuario = usuarioRepository.findByEmail(model.getEmail());
			
			if(usuario!=null) {
				String novaSenha = String.valueOf(new Random().nextInt(999999999));
				MailMessage.send(usuario.getEmail(), 
						"Nova senha gerada com sucesso - COTI Informática Controle de Tarefas",
						"Olá, "+usuario.getNome()+"\n\n"+"Sua nova senha de acesso é: "+novaSenha+
						".\n"+"Utilize esta senha para acessar o sistema e depois atualize para uma"
						+ " senha de sua preferência, através do menu > configurações > atualizar senha."+
						"\n\nAtt,Equipe COTI Informática");
				
				usuarioRepository.updatePassword(usuario.getIdUsuario(), novaSenha);
				
				modelAndView.addObject("mensagem_sucesso","Nova senha gerada e "
						+ "enviada para o e-mail com sucesso.");
				
				model = new ForgotPasswordModel();
			}else {
				modelAndView.addObject("email_invalido","E-mail inválido, tente outro.");
			}
			
		}else {
			throw new Exception();
		}
		}catch(Exception e) {
			modelAndView.addObject("mensagem_erro",e.getMessage());
		}
		modelAndView.addObject("model",model);
		return modelAndView;
	}
}
