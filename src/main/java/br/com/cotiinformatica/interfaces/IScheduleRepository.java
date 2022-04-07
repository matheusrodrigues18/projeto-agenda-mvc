package br.com.cotiinformatica.interfaces;

import java.util.Date;
import java.util.List;

import br.com.cotiinformatica.entities.Schedule;

public interface IScheduleRepository {

	void create(Schedule tarefa) throws Exception;
	
	void update(Schedule tarefa) throws Exception;
	
	void delete(Schedule tarefa) throws Exception;
	
	List<Schedule> findByDatas(Date dataMin, Date dataMax,Integer idUsuario) throws Exception;
	
	List<Schedule> findAll(Integer idUsuario) throws Exception;
	
	Schedule findById(Integer id, Integer idUsuario) throws Exception;
}
