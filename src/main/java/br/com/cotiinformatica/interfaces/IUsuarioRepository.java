package br.com.cotiinformatica.interfaces;

import br.com.cotiinformatica.entities.User;

public interface IUsuarioRepository {
		
	void create(User usuario) throws Exception;
	
	User findByEmail(String email) throws Exception;
	
	User findByEmailAndSenha(String email, String senha) throws Exception;
	
	void updatePassword(Integer idUsuario, String newPass) throws Exception;
}
