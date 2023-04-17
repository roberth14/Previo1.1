package co.empresa.Previo1.modelo;
import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Paciente implements Serializable {
	
	
	private Integer id;
	private String documento;
	private String nombre;
	private String apellido;
	private String email;
	private String genero;
	private Date fechaNacimiento;
	private String telefono;
	private String direccion;
	private float peso;
	private float estatura;
	
	
	
	
	
	 

  }