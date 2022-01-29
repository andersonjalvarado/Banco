import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
/**
 * @author Anderson Alvarado
 *Clase Abstracta Cuenta
 */
public abstract class Cuenta implements Serializable{
	
	///ATRIBUTOS
	private int consecutivo;
	private double saldo;
	private boolean estado;
	private	LocalDate fechaCreacion;
	private List<Titular>titulares;
	
	//CONSTRUCTORES;
	/**
	 * Constructor por defecto
	 */
	public Cuenta() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor con parametros que hereda a sus subclases
	 * @param consecutivo: número de la cuenta 
	 * @param estado: true para activa, false para desactiva
	 * @param fechaCreacion: la fecha de hoy
	 * @param saldo: saldo en cada cuenta
	 * @param titulares: lista de titulares de la cuenta
	 */
	public Cuenta(int consecutivo, boolean estado, LocalDate fechaCreacion, double saldo) {
		this.consecutivo = consecutivo;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.saldo = saldo;
		titulares = new ArrayList<Titular>();
	}
	
	//METODOS
	/**
	 *  Método Buscar un Titular de la cuenta
	 * @param nomTitular: nombre del titular a buscar
	 * @return t: Titular encontrado
	 */
	public Titular buscarTitular(String nombre){
		Titular t;
		for (int i =0; i< titulares.size(); i++) {
			t = titulares.get(i);
			if(t.getNombre().equalsIgnoreCase(nombre)) {
				return t;
			}
		}
		return null;
	}
	
	/**
	 * Método agregar Titular a una Cuenta
	 * @param nombre: nombre del titular a agregar 
	 * @param fechaNac: Fecha de nacimiento del titular a agregar 
	 * @param telefonoFijo: Teléfono fijo del titular a agregar 
	 * @param telefonoCelular: Teléfono celular del titular a agregar 
	 * @param direccion: Dirección del titular a agregar 
	 * @throws CuentaException: Arroja una excepción si no hay un titular con ese nombre
	 */
	public void agregarTitular(String nombre, LocalDate fechaNac, String telefonoFijo, String telefonoCelular, String direccion) 
				throws CuentaException, TitularException{
		Titular t = buscarTitular(nombre);
		if(t == null) {
			t = new Titular(nombre, fechaNac, telefonoFijo,telefonoCelular,direccion);
			titulares.add(t);
		}
		else {
			throw new CuentaException("El titular Ya Existe");
		}	
	}
	
	/**
	 * Método Abstracto Consignar a una Cuenta
	 * @param monto: monto que se va a consignar en la cuenta
	 */
	public abstract void consignar(int monto); 
	
	/**
	 * Método Abstracto Retirar de una Cuenta
	 * @param monto: monto que se va a retirar de la cuenta
	 * @throws CuentaException: Arroja una excepción de la clase hija
	 */
	public abstract void retirar(int monto) throws CuentaException; 
	
	/**
	 * Método Abstracto Transferir de una Cuenta
	 * @param monto: monto que se va a retirar de la cuenta
	 * @param destino: cuenta a la que se le va a transferir el monto
	 * @throws CuentaException: Arroja una excepción de la clase hija
	 */
	public abstract void transferir(int monto, Cuenta destino) throws CuentaException; 
	
	/**
	 * Método Cantidad de Cuentas que Tiene un Titular
	 * @param nomTitular: nombre del titular
	 * @return sw: de tipo bool, true si encuentra el titular en la cuenta
	 */
	public boolean cantidadCuentas(String nomTitular) {
		boolean sw = false;
		
		for (Titular titular : titulares) {
			if (UtilidadCadenas.convertirMinusculas(titular.getNombre()).equalsIgnoreCase(nomTitular)) {
				sw = true;
			}
		}		
		return sw;
	}	
	
	/**
	 * Método Titulares que Nacieron Después de un Año Dado
	 * @param anio: año de referencia
	 * @return anioTitulares: Lista de titulares que nacieron después del año dado
	 */
	public List<Titular>anioTitular(int anio) {
		List<Titular>anioTitulares = new ArrayList<Titular>();
		
		for (Titular titular : titulares) {
			if (titular.getFechaNac().getYear() >= anio) {
				anioTitulares.add(titular);
			}
		}
		return anioTitulares;
	}
	
	// GETTERS AND SETTERS
	/**
	 * @return consecutivo
	 */
	public int getConsecutivo() {
		return consecutivo;
	}
	/**
	 * @param consecutivo the consecutivo to set
	 */
	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}	
	
	/**
	 * @return saldo
	 */
	public double getSaldo() {
		return saldo;
	}
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
		
	/**
	* @return estado
	*/
	public boolean getEstado() {
		return estado;
	}
	/**
	* @param saldo the estado to set
	*/
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
		
	/**
	* @return fechaCreacion
	*/
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	*/
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	/**
	 * @return titulares
	 */
	public List<Titular> getTitulares() {
		return titulares;
	}
	/**
	 * @param titulares the titulares to set
	 */
	public void setTitulares(List<Titular> titulares) {
		this.titulares = titulares;
	}	

	
	@Override
	public String toString() {
		return "Número de cuenta:" + consecutivo + ", Saldo:$" + saldo + ", Estado:ACTIVA" + 
				", Fecha de creación:" + fechaCreacion + "\n\tTitulares:" + titulares;
	}	
}