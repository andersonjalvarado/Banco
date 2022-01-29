import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Anderson Alvarado
 *Clase que hereda de la clase cuenta
 */
public class CuentaAhorros extends Cuenta {

	private	String numTarjeta;
	
	/**
	 * Constructor por defecto
	 */
	public CuentaAhorros() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor con parametros 
	 * @param consecutivo: número de la cuenta
	 * @param estado: true para activa, false para desactiva
	 * @param fechaCreacion: la fecha de hoy
	 * @param saldo: saldo en cada cuenta
	 * @param numTarjeta
	 */
	public CuentaAhorros(int consecutivo, boolean estado, LocalDate fechaCreacion, double saldo, String numTarjeta) {
		super(consecutivo, estado, fechaCreacion, saldo);
		this.numTarjeta = numTarjeta;
	}
	
	//METODOS
	/**
	 * Método Consignar a una Cuenta de Ahorros
	 * @param monto: monto a consignar en la cuenta de Ahorros
	 */
	public void consignar(int monto){
		setSaldo(getSaldo() + monto);
	}
	
	/**
	 * Método Retirar de una Cuenta de Ahorros
	 * @param monto: monto a retirar de la cuenta de Ahorros
	 * @throws CuentaException: Arroja una excepción si no tiene suficiente dinero para retirar
	 */
	public void retirar(int monto) throws CuentaException{
		
		if(monto <= getSaldo()) {
			setSaldo(getSaldo() - monto);
		}
		else{
			throw new CuentaException("Fondos Insuficientes");
		}		
	}
	
	/**
	 * Método Abstracto Transferir de una Cuenta
	 * @param monto: monto que se va a retirar de la cuenta
	 * @param destino: cuenta a la que se le va a transferir el monto
	 * @throws CuentaException: Arroja una excepción de la clase hija
	 */
	public void transferir(int monto, Cuenta destino) throws CuentaException{
		retirar(monto);
		destino.consignar(monto);
	}
	
	
	//SETTERS AND GETTERS
	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	@Override
	public String toString() {
		return "\n\n\tCuenta de Ahorros: Número de Tarjeta:" + numTarjeta + ", " + super.toString();
	}				
}