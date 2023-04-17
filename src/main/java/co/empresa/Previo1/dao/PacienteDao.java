package co.empresa.Previo1.dao;

import java.sql.SQLException;
import java.util.List;

import co.empresa.Previo1.modelo.Paciente;

public interface PacienteDao {

	public void insert (Paciente paciente)throws SQLException;
	public Paciente select (int id);
	public List <Paciente> selectAll();
	public void delete (int id) throws SQLException;
	public void update (Paciente paciente) throws SQLException;
	
}
