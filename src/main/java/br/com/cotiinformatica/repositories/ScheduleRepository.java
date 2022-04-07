package br.com.cotiinformatica.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import br.com.cotiinformatica.entities.Schedule;
import br.com.cotiinformatica.helpers.DateHelper;
import br.com.cotiinformatica.interfaces.IScheduleRepository;

public class ScheduleRepository implements IScheduleRepository {

	private JdbcTemplate jdbcTemplate;
	
	public ScheduleRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void create(Schedule tarefa) throws Exception {

		String sql = "INSERT INTO TAREFA(NOME, DESCRICAO, DATA, HORA, PRIORIDADE, IDUSUARIO) "
				   + "VALUES(?, ?, ?, ?, ?, ?)";

		Object[] params = {
			tarefa.getNome(),
			tarefa.getDescricao(),
			DateHelper.toString(tarefa.getData()),
			tarefa.getHora(),
			tarefa.getPrioridade(),
			tarefa.getUsuario().getIdUsuario()
		};
		
		jdbcTemplate.update(sql, params);
	}

	@Override
	public void update(Schedule tarefa) throws Exception {

		String sql = "UPDATE TAREFA SET NOME = ?, DESCRICAO = ?, DATA = ?, HORA = ?, PRIORIDADE = ? "
				   + "WHERE IDTAREFA = ? AND IDUSUARIO = ?";
		
		Object[] params = {
				tarefa.getNome(),
				tarefa.getDescricao(),
				DateHelper.toString(tarefa.getData()),
				tarefa.getHora(),
				tarefa.getPrioridade(),
				tarefa.getIdTarefa(),
				tarefa.getUsuario().getIdUsuario()
			};

		jdbcTemplate.update(sql, params);
	}

	@Override
	public void delete(Schedule tarefa) throws Exception {

		String sql = "DELETE FROM TAREFA WHERE IDTAREFA = ? AND IDUSUARIO = ?";
		
		Object[] params = {
				tarefa.getIdTarefa(),
				tarefa.getUsuario().getIdUsuario()
			};
		
		jdbcTemplate.update(sql, params);
	}

	@Override
	public List<Schedule> findAll(Integer idUsuario) throws Exception {

		String sql = "SELECT * FROM TAREFA WHERE IDUSUARIO = ? ORDER BY DATA, HORA";
		
		Object[] params = {
			idUsuario
		};
		
		List<Schedule> lista = jdbcTemplate.query(sql, params, new RowMapper<Schedule>() {
			@Override
			public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {

				Schedule tarefa = new Schedule();
				
				tarefa.setIdTarefa(rs.getInt("IDTAREFA"));
				tarefa.setNome(rs.getString("NOME"));
				tarefa.setDescricao(rs.getString("DESCRICAO"));
				tarefa.setData(rs.getDate("DATA"));
				tarefa.setHora(rs.getString("HORA"));
				tarefa.setPrioridade(rs.getString("PRIORIDADE"));
				
				return tarefa;
			}	
		});
		
		return lista;
	}

	@Override
	public List<Schedule> findByDatas(Date dataMin, Date dataMax, Integer idUsuario) throws Exception {

		String sql = "SELECT * FROM TAREFA WHERE IDUSUARIO = ? AND DATA BETWEEN ? AND ? "
				   + "ORDER BY DATA, HORA";
		
		Object[] params = {
				idUsuario,
				DateHelper.toString(dataMin), 
				DateHelper.toString(dataMax)
			};
		
		List<Schedule> lista = jdbcTemplate.query(sql, params, new RowMapper<Schedule>() {
			@Override
			public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {

				Schedule tarefa = new Schedule();
				
				tarefa.setIdTarefa(rs.getInt("IDTAREFA"));
				tarefa.setNome(rs.getString("NOME"));
				tarefa.setDescricao(rs.getString("DESCRICAO"));
				tarefa.setData(rs.getDate("DATA"));
				tarefa.setHora(rs.getString("HORA"));
				tarefa.setPrioridade(rs.getString("PRIORIDADE"));
				
				return tarefa;
			}	
		});
		
		return lista;
	}

	@Override
	public Schedule findById(Integer idTarefa, Integer idUsuario) throws Exception {

		String sql = "SELECT * FROM TAREFA WHERE IDTAREFA = ? AND IDUSUARIO = ?";
		
		Object[] params = {
				idTarefa,
				idUsuario
			};
		
		List<Schedule> lista = jdbcTemplate.query(sql, params, new RowMapper<Schedule>() {
			@Override
			public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {

				Schedule tarefa = new Schedule();
				
				tarefa.setIdTarefa(rs.getInt("IDTAREFA"));
				tarefa.setNome(rs.getString("NOME"));
				tarefa.setDescricao(rs.getString("DESCRICAO"));
				tarefa.setData(rs.getDate("DATA"));
				tarefa.setHora(rs.getString("HORA"));
				tarefa.setPrioridade(rs.getString("PRIORIDADE"));
				
				return tarefa;
			}	
		});
		
		if(lista != null && lista.size() == 1) {
			return lista.get(0);
		}
		else {
			return null;
		}	
	}
}
