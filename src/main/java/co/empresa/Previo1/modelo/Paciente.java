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
	
	public Paciente(String documento, String nombre, String apellido, String email, String genero, Date fechaNacimiento,
			String telefono, String direccion, float peso, float estatura) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.direccion = direccion;
		this.peso = peso;
		this.estatura = estatura;
	}
	
	
	
	
	
	
	
	 

  }