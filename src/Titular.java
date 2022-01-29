import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Anderson Alvarado
 *
 */
public class Titular implements Serializable{
	
	//ATRIBUTOS
	private String nombre;
	private	LocalDate fechaNac;
	private	String telefonoFijo;
	private	String telefonoCelular;
	private	String direccion;

	//CONSTRUCTORES
	/**
	 * Constructor por defecto
	 */
	public Titular() {
	
	}
	
	/**
	 * Constructor con parametros
	 * @param nombre
	 * @param fechaNac
	 * @param telefonoFijo
	 * @param telefonoCelular
	 * @param direccion
	 */
	public Titular(String nombre, LocalDate fechaNac, String telefonoFijo, String telefonoCelular, String direccion) throws TitularException{
		if(nombre != null) {
			this.nombre = nombre;
			this.fechaNac = fechaNac;
			this.telefonoFijo = telefonoFijo;
			this.telefonoCelular = telefonoCelular;
			this.direccion = direccion;
		}
		else {
			throw new TitularException("Nombre Inválido");
		}
	}
	
	// GETTERS AND SETTERS
	/**
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return fechaNac
	 */
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	/**
	 * @param fechaNac the fechaNac to set
	 */
	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	/**
	 * @return telefonoFijo
	 */
	public String getTelefonoFijo() {
		return telefonoFijo;
	}
	/**
	 * @param telefonoFijo the telefonoFijo to set
	 */
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	/**
	 * @return telefonoCelular
	 */
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	/**
	 * @param telefonoCelular the telefonoCelular to set
	 */
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	/**
	 * @return direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaNac == null) ? 0 : fechaNac.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Titular other = (Titular) obj;
		if (fechaNac == null) {
			if (other.fechaNac != null)
				return false;
		} else if (!fechaNac.equals(other.fechaNac))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "\n\t Nombre:" + nombre + ", Fecha de nacimiento:" + fechaNac + ", Teléfono fijo:" + telefonoFijo
				+ ", Teléfono celular:" + telefonoCelular + ", Dirección:" + direccion;
	}
}