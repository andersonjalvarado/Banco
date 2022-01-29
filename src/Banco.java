import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
/**
 * @author Anderson Alvarado
 *
 */
public class Banco implements Serializable{
	//ATRIBUTO
	private List<Cuenta>cuentas;

	//CONSTRUCTOR
	/**
	 * Constructor por defecto, se pide memoria para la lista de cuentas
	 */
	public Banco() {
		cuentas = new ArrayList<>();
	}

	//METODOS
	/**
	 * Método Buscar un número de cuenta, segun su consecutivo
	 * @param numCuenta: número de la cuenta a buscar
	 * @return Cuenta encontrada
	 */
	public Cuenta buscarCuenta(int consecutivo){
		for (Cuenta cuenta : cuentas) {
			if(cuenta.getConsecutivo() == consecutivo ) {
				return cuenta;
			}
		}
		return null;
	}
	
	/**
	 * Método agregar Cuenta de Ahorros a la lista de cuentas del banco
	 * @param saldo: saldo de la cuenta a agregar 
	 * @param numCuenta: número de cuenta a agregar
	 * @return Consecutivo de la cuenta
	 */
	public int agregarCuentaAhorros( double saldo, String numTarjeta) {
		Cuenta c;
		int consecutivo = cuentas.size()+1;
		LocalDate fechaCreacion = LocalDate.now(); // fecha de creacion de la cuenta
		boolean estado = true; // estado de la cuenta
		
		c = new CuentaAhorros(consecutivo, estado, fechaCreacion, saldo, numTarjeta);		
		cuentas.add(c);	
		return consecutivo;
	}
	
	/**
	 * Método agregar Cuenta Corriente a la lista de cuentas del banco
	 * @param saldo: saldo de la cuenta a agregar 
	 * @param tipo: tipo de la cuenta  agregar, ahorros o corriente
	 * @param numCuenta: número de cuenta a agregar
	 * @return Consecutivo de la cuenta
	 */
	public int agregarCuentaCorriente( double saldo, double sobregiroAutorizado) {
		Cuenta c;
		int consecutivo = cuentas.size()+1;
		LocalDate fechaCreacion = LocalDate.now(); // fecha de creacion de la cuenta
		boolean estado = true; // estado de la cuenta
	
		 c = new CuentaCorriente(consecutivo, estado, fechaCreacion, saldo, sobregiroAutorizado);		
		cuentas.add(c);	
		return consecutivo;
	}
	
	/**
	 * Método Agregar titular
	 * @param numCuenta: número de cuenta a buscar
	 * @param nombre: nombre del titular a agregar 
	 * @param fechaNac: fecha de nacimiento del titular a agregar 
	 * @param telefonoFijo: telefono fijo del titular a agregar 
	 * @param telefonoCelular: telefono celular del titular a agregar 
	 * @param direccion: direccion del titular a agregar 
	 * @throws BancoException: Arroja excepción si no hay una cuenta con ese número de cuenta
	 * @throws CuentaException: Arroja una excepción si no hay un titular con ese nombre
	 */
	public void agregarTitular(int consecutivo, String nombre, LocalDate fechaNac, String telefonoFijo,
				   			   String telefonoCelular, String direccion) throws BancoException, CuentaException, TitularException{	
		Cuenta c = buscarCuenta(consecutivo);
		if(c != null) {
			c.agregarTitular(nombre, fechaNac, telefonoFijo, telefonoCelular, direccion);
		}
		else {
			throw new BancoException("La Cuenta No Existe");
		}	
	}
	
	/**
	 * Método Consignar a una Cuenta
	 * @param consecutivo: número de cuenta
	 * @param monto: monto a consignar en la cuenta
	 * @throws BancoException: Arroja una excepción si no hay una cuenta con ese número de cuenta
	 * @return Cuenta c: Cuenta actualizada
	 */
	public Cuenta consignar(int consecutivo, int monto) throws BancoException{
		Cuenta c = buscarCuenta(consecutivo);
		
		if(c != null) {
			c.consignar(monto);
			return c;
		}
		else {
			throw new BancoException("La Cuenta No Existe");
		}	
	}
	
	/**
	 * Método Retirar de una Cuenta
	 * @param consecutivo: número de cuenta
	 * @param monto: monto a retirar de la cuenta
	 * @throws BancoException: Arroja una excepción si no hay una cuenta con ese número de cuenta
	 * @return Cuenta c: Cuenta actualizada
	 */
	public Cuenta retirar(int consecutivo, int monto) throws BancoException,CuentaException {
		Cuenta c = buscarCuenta(consecutivo);
		if(c != null) {
			c.retirar(monto);
			return c;
		}
		else {
			throw new BancoException("La Cuenta No Existe");
		}	
	}
	
	/**
	 * Método Transferir de una Cuenta a Otra
	 * @param cuentaOrigen: número de cuenta de origen
	 * @param monto: monto a retirar de la cuenta de la cuenta de origen
	 * @param cuentaDestino: número de cuenta a la que se va a transferir el monto
	 * @throws BancoException: Arroja una excepción si no hay una cuenta con ese número de cuenta
	 * @return Lista de Cuentas cuentasTransferir: Cuentas actualizadas
	 */
	/*public List<Cuenta> transferir(int cuentaOrigen, int monto, int cuentaDestino) throws BancoException,CuentaException {
		
		List<Cuenta> cuentasTransferir = new ArrayList<Cuenta>();
		Cuenta c1 = buscarCuenta(cuentaOrigen);
		Cuenta c2 = buscarCuenta(cuentaDestino);
		
		if( (cuentaOrigen != cuentaDestino) && (c1 != null) && (c2 != null) ) {
			c1.retirar(monto);
			c2.consignar(monto);
			cuentasTransferir.add(c1);
			cuentasTransferir.add(c2);
			return cuentasTransferir;
		}
		else {
			throw new BancoException("Una o Más Cuentas No Existen o Digitó La Misma Cuenta");
		}	
	}*/
	
	/**
	 * Método Transferir de una Cuenta a Otra
	 * @param cuentaOrigen: número de cuenta de origen
	 * @param monto: monto a retirar de la cuenta de la cuenta de origen
	 * @param cuentaDestino: número de cuenta a la que se va a transferir el monto
	 * @throws BancoException: Arroja una excepción si no hay una cuenta con ese número de cuenta
	 * @return Lista de Cuentas cuentasTransferir: Cuentas actualizadas
	 */
	public List<Cuenta> transferir(int cuentaOrigen, int monto, int cuentaDestino) throws BancoException,CuentaException {
		
		List<Cuenta> cuentasTransferir = new ArrayList<Cuenta>();
		Cuenta c1 = buscarCuenta(cuentaOrigen);
		Cuenta c2 = buscarCuenta(cuentaDestino);
		
		if( (cuentaOrigen != cuentaDestino) && (c1 != null) && (c2 != null) ) {
			c1.transferir(monto, c2);
			cuentasTransferir.add(c1);
			cuentasTransferir.add(c2);
			return cuentasTransferir;
		}
		else {
			throw new BancoException("Una o Más Cuentas No Existen o Digitó La Misma Cuenta");
		}	
	}
	
	/**
	 * Método Cantidad de Cuentas que Tiene un Titular
	 * @param nomTitular: nombre del titular
	 * @param monto: monto a retirar de la cuenta de la cuenta de origen
	 * @param cuentaDestino: número de cuenta a la que se va a transferir el monto
	 * @return cuentasTitular: Lista de cuentas que posee el titular
	 * @throws CuentaException: Arroja una excepción si no hay un titular con ese nombre
	 */
	public List<Cuenta>cantidadCuentas(String nomTitular) throws BancoException{
		
		List<Cuenta>cuentasTitular = new ArrayList<Cuenta>();
		
		for (Cuenta cuenta : cuentas) {
			if(cuenta.cantidadCuentas(nomTitular)) {
				cuentasTitular.add(cuenta);
			}
		}
		if(cuentasTitular.isEmpty()) {
			throw new BancoException("El titular No Existe");
		}
		return cuentasTitular;		
	}
	
	/**
	 * Método Titulares que Nacieron Después de un Año Dado
	 * @param anio: año de referencia
	 * @return anioTitulares: Lista de titulares que nacieron después del año dado
	 * @throws BancoException: Arroja una excepción si se da un año mayor al actual
	 */
	public List<Titular>anioTitular(int anio) throws BancoException{
		
		//Set<Titular>anioTitulares = new LinkedHashSet<Titular>();
		List<Titular>anioTitulares = new ArrayList<Titular>();
		LocalDate now = LocalDate.now();
		
		if(anio <= now.getYear()) {
			
			for (Cuenta c : cuentas) {
				anioTitulares.addAll(c.anioTitular(anio));	
			}	
			return anioTitulares;
		}	
		else {
			throw new BancoException("¿Eres del futuro? Digite un año válido viajer@ del tiempo");
		}
	}
	
	/**
	 * Método Cuentas Corrientes con Sobregiro Usado Mayor a 0
	 * @return cuentasSobregiradas: Lista de cuentas corrientes con sobregiro usado Mayor a 0
	 */
	public List<Cuenta>cuentasSobregiradas() {
		
		List<Cuenta>cuentasSobregiradas = new ArrayList<Cuenta>();
		for (Cuenta cuenta : cuentas) {
			if( cuenta instanceof CuentaCorriente ) {
				CuentaCorriente c = (CuentaCorriente) cuenta;
				if( c.getSobregiroUsado() > 0 ) {
					cuentasSobregiradas.add(cuenta);
				}
			}
		}
		return cuentasSobregiradas;
	}
	
	// GETTERS AND SETTERS
		/**
		 * @return cuentas
		 */
		public List<Cuenta> getCuentas() {
			return cuentas;
		}
		/**
		 * @param cuentas the cuentas to set
		 */
		public void setCuentas(List<Cuenta> cuentas) {
			this.cuentas = cuentas;
		}
		
	@Override
	public String toString() {
		return "\n\t\t----------------------------------------JAVIBANK---------------------------------------------- \n "+
				"\n\t\tCuentas\n" + cuentas;
	} 
	
}