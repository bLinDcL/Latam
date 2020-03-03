package cl.desafio.latam.json.response;

public class PersonaResponse {

	private String nombre;
	private String apellido;
	private Integer edad;
	private String fecha;
	private String mensaje;

	public PersonaResponse() {
	}

	public PersonaResponse(String nombre, String apellido, Integer edad, String fecha, String mensaje) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.fecha = fecha;
		this.mensaje = mensaje;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", fecha=" + fecha
				+ ", mensaje=" + mensaje + "]";
	}

}
