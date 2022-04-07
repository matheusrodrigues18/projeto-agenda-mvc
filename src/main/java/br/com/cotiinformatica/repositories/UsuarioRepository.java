package br.com.cotiinformatica.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import br.com.cotiinformatica.entities.User;
import br.com.cotiinformatica.interfaces.IUsuarioRepository;

public class UsuarioRepository implements IUsuarioRepository {

	// componente do Spring para acesso ao banco de dados
	// atraves do DataSource configurado na classe MvcConfiguration
	private JdbcTemplate jdbcTemplate;

	// construtor para receber a conexão do banco de dados
	public UsuarioRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(User usuario) throws Exception {

		String sql = "INSERT INTO USUARIO(NOME, EMAIL, SENHA) VALUES(?, ?, MD5(?))";

		Object[] params = {
			usuario.getNome(),
			usuario.getEmail(),
			usuario.getSenha()
		};
		
		jdbcTemplate.update(sql, params);
	}

	@Override
	public User findByEmail(String email) throws Exception {

		String sql = "SELECT * FROM USUARIO WHERE EMAIL = ?";
		
		Object[] params = {
			email
		};
		
		List<User> lista = jdbcTemplate.query(sql, params, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {

				User usuario = new User();
				
				usuario.setIdUsuario(rs.getInt("IDUSUARIO"));
				usuario.setNome(rs.getString("NOME"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setSenha(rs.getString("SENHA"));
				
				return usuario;
			}	
		});
		
		//verificar se algum usuario foi encontrado na consulta
		if(lista != null && lista.size() == 1) {
			return lista.get(0); //retornando o usuário encontrado
		}
		else {
			return null; //retornando vazio
		}
	}

	@Override
	public User findByEmailAndSenha(String email, String senha) throws Exception {

		String sql = "SELECT * FROM USUARIO WHERE EMAIL = ? AND SENHA = MD5(?)";
		
		Object[] params = {
			email,
			senha
		};
		
		List<User> lista = jdbcTemplate.query(sql, params, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {

				User usuario = new User();
				
				usuario.setIdUsuario(rs.getInt("IDUSUARIO"));
				usuario.setNome(rs.getString("NOME"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setSenha(rs.getString("SENHA"));
				
				return usuario;
			}	
		});
		
		//verificar se algum usuario foi encontrado na consulta
		if(lista != null && lista.size() == 1) {
			return lista.get(0); //retornando o usuário encontrado
		}
		else {
			return null; //retornando vazio
		}		
	}

	@Override
	public void updatePassword(Integer idUsuario, String newPass) throws Exception {
		// TODO Auto-generated method stub
		String sql = "UPDATE USUARIO SET SENHA = MD5(?) WHERE IDUSUARIO = ?";
		
		Object[] params = {
			newPass,
			idUsuario
		};
		
		jdbcTemplate.update(sql,params);
	}
}
