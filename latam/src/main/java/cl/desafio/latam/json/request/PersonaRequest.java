package cl.desafio.latam.json.request;

public class PersonaRequest {

	private String nombre;
	private String apellido;
	private String fecha;

	public PersonaRequest() {
	}

	public PersonaRequest(String nombre, String apellido, String fecha) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha = fecha;
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", fecha=" + fecha + "]";
	}

}
