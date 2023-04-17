package co.empresa.Previo1.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.empresa.Previo1.modelo.Paciente;
import co.empresa.Previo1.util.ConexionPostgreSQL;




public class PacienteDaoPostgreSQL implements PacienteDao{

	private ConexionPostgreSQL conexion;
	private static final String INSERT_USUARIO_SQL = "INSERT INTO paciente (documento,nombre,apellido,email,genero,fechanacimiento,telefono,direccion,peso,estatura) VALUES (?,?,?,?,?,?,?,?,?,?);";
	private static final String DELETE_USUARIO_SQL = "DELETE FROM paciente WHERE id=?;";
	private static final String UPDATE_USUARIO_SQL = "UPDATE paciente SET documento=?, nombre=?, apellido=?, email=?,genero=?, fechanacimiento=?,telefono=?, direccion=?, peso=?, estatura=?  WHERE id=?;";
	private static final String SELECT_USUARIO_BY_ID = "SELECT * FROM paciente WHERE id=?;";
	private static final String SELECT_ALL_USUARIO = "SELECT * FROM paciente;";

	public PacienteDaoPostgreSQL() {
		this.conexion = ConexionPostgreSQL.getConexion();
	}

	// METODO PARA INSERTAR USUARIO 
	public void insert(Paciente paciente)  throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_USUARIO_SQL); //Le mandamos la sentencia que queremos ejecutar, el preparedStatement asocia los atributos con los interrogantes dentro de la cadena de texto 		
			preparedStatement.setString(1, paciente.getDocumento()); // ponemos 1 para que haga referencia al primer interrogante de la cadena, y lo remplazamos por el nombre del usuario  
			preparedStatement.setString(2, paciente.getNombre());// ponemos 2 para que haga referencia al segundo interrogante de la cadena, y lo remplazamos por el email usuario
			preparedStatement.setString(3, paciente.getApellido()); // ponemos 3 para que haga referencia al tercer interrogante de la cadena, y lo remplazamos por el pais del usuario  
			preparedStatement.setString(4, paciente.getEmail()); // ponemos 1 para que haga referencia al primer interrogante de la cadena, y lo remplazamos por el nombre del usuario  
			preparedStatement.setString(5, paciente.getGenero());// ponemos 2 para que haga referencia al segundo interrogante de la cadena, y lo remplazamos por el email usuario
			preparedStatement.setDate(6, paciente.getFechaNacimiento()); // ponemos 3 para que haga referencia al tercer interrogante de la cadena, y lo remplazamos por el pais del usuario  
			preparedStatement.setString(7, paciente.getTelefono()); // ponemos 1 para que haga referencia al primer interrogante de la cadena, y lo remplazamos por el nombre del usuario  
			preparedStatement.setString(8, paciente.getDireccion());// ponemos 2 para que haga referencia al segundo interrogante de la cadena, y lo remplazamos por el email usuario
			preparedStatement.setFloat(9, paciente.getPeso()); // ponemos 3 para que haga referencia al tercer interrogante de la cadena, y lo remplazamos por el pais del usuario  
			preparedStatement.setFloat(10, paciente.getEstatura()); // ponemos 1 para que haga referencia al primer interrogante de la cadena, y lo remplazamos por el nombre del usuario  
			
			conexion.execute(); // ejecuta la sentencia
		} catch (SQLException e) {
		}
	}
 
	
	public void delete(int id) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_USUARIO_SQL);
			preparedStatement.setInt(1, id);
			conexion.execute();
		} catch (SQLException e) {
		}
	}
	

    public void update(Paciente paciente) throws SQLException {
       
        try {
	    	PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_USUARIO_SQL);
	    	preparedStatement.setString(1, paciente.getDocumento()); // ponemos 1 para que haga referencia al primer interrogante de la cadena, y lo remplazamos por el nombre del usuario  
			preparedStatement.setString(2, paciente.getNombre());// ponemos 2 para que haga referencia al segundo interrogante de la cadena, y lo remplazamos por el email usuario
			preparedStatement.setString(3, paciente.getApellido()); // ponemos 3 para que haga referencia al tercer interrogante de la cadena, y lo remplazamos por el pais del usuario  
			preparedStatement.setString(4, paciente.getEmail()); // ponemos 1 para que haga referencia al primer interrogante de la cadena, y lo remplazamos por el nombre del usuario  
			preparedStatement.setString(5, paciente.getGenero());// ponemos 2 para que haga referencia al segundo interrogante de la cadena, y lo remplazamos por el email usuario
			preparedStatement.setDate(6, paciente.getFechaNacimiento()); // ponemos 3 para que haga referencia al tercer interrogante de la cadena, y lo remplazamos por el pais del usuario  
			preparedStatement.setString(7, paciente.getTelefono()); // ponemos 1 para que haga referencia al primer interrogante de la cadena, y lo remplazamos por el nombre del usuario  
			preparedStatement.setString(8, paciente.getDireccion());// ponemos 2 para que haga referencia al segundo interrogante de la cadena, y lo remplazamos por el email usuario
			preparedStatement.setFloat(9, paciente.getPeso()); // ponemos 3 para que haga referencia al tercer interrogante de la cadena, y lo remplazamos por el pais del usuario  
			preparedStatement.setFloat(10, paciente.getEstatura()); // ponemos 1 para que haga referencia al primer interrogante de la cadena, y lo remplazamos por el nombre del usuario  
			
	    	preparedStatement.setInt(11, paciente.getId());
	         conexion.execute();
        } catch (SQLException e) {
        }
    }
    
    public List <Paciente> selectAll() {

        List <Paciente> pacientes = new ArrayList<>();   
        try {
        	PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_USUARIO);
            ResultSet rs = conexion.query();// recibe todo sobre los elementos de esta consulta
            while (rs.next()) {
                int id = rs.getInt("id");
                String documento = rs.getString("documento");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String genero = rs.getString("genero");
                Date fechaN = rs.getDate("fechanacimiento");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                Float peso = rs.getFloat("peso");
                Float estatura = rs.getFloat("estatura");
                
       
                
                pacientes.add(new Paciente(id, documento,nombre,apellido,email,genero,fechaN,telefono,direccion,peso,estatura));
            }
        } catch (SQLException e) {
          
        }
        return pacientes;
    }
    
    public Paciente select(int id) {
    	Paciente paciente = null;
        try {
        	PreparedStatement preparedStatement =conexion.setPreparedStatement(SELECT_USUARIO_BY_ID);
            preparedStatement.setInt(1, id);
      
            ResultSet rs = conexion.query();
            while (rs.next()) {
            	 String documento = rs.getString("documento");
                 String nombre = rs.getString("nombre");
                 String apellido = rs.getString("apellido");
                 String email = rs.getString("email");
                 String genero = rs.getString("genero");
                 Date fechaN = rs.getDate("fechanacimiento");
                 String telefono = rs.getString("telefono");
                 String direccion = rs.getString("direccion");
                 Float peso = rs.getFloat("peso");
                 Float estatura = rs.getFloat("estatura");
            	
                paciente = new Paciente(id, documento,nombre,apellido,email,genero,fechaN,telefono,direccion,peso,estatura);
            }
        } catch (SQLException e) {
        }
        return paciente;
    }

    
    

    
	
	
}
