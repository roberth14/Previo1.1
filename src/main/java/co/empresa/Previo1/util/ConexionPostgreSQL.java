package co.empresa.Previo1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionPostgreSQL {

	private Connection con = null;
	private PreparedStatement preparedStatement;
	private static ConexionPostgreSQL db;
	private static final String url = " database-1.ct3gev1bipds.us-east-2.rds.amazonaws.com/";
	private static final String dbName = "testpweb";
	private static final String driver = "org.postgresql.Driver";
	private static final String userName = "student";
	private static final String password = "Student22";


	public ConexionPostgreSQL() {
		try {
			Class.forName(driver).newInstance(); // instancia clase que creando
			con = (Connection) DriverManager.getConnection(url + dbName, userName, password);// realizamos la conexion
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cerrarConexion() { // Para cerrar la conexion
		try {
			con.close(); // cerramos la conexion
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	// metodo Singleton
	public static ConexionPostgreSQL getConexion() {
		if (db == null) {
			db = new ConexionPostgreSQL();
		}
		return db;
	}
	
	// metodo que nos retorna el resultado de una consulta
	public ResultSet query() throws SQLException {
		ResultSet res = this.preparedStatement.executeQuery();// el preparedStatement es el que permite ejecutar las
																// consultas
		return res;
	}

	// metodo para actualizaciones o el que ejecuta el dml
	public int execute() throws SQLException {
		int result = this.preparedStatement.executeUpdate();// el preparedStatement es el que permite ejecutar las
															// actualizaciones
		return result;
	}

	// metodo para obtener el objeto conexion
	public Connection getCon() {
		return this.con;
	}

	// inicializar el preparedStatement dentro del ejercicio
	public PreparedStatement setPreparedStatement(String sql) throws SQLException {
		this.preparedStatement = con.prepareStatement(sql); // el sql que ingresamos lo preparamos para hacer la
															// sentencia a ejecutar
		return this.preparedStatement;
	}

	

}
