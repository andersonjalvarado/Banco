import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Anderson Alvarado
 * *Clase que hereda de la clase cuenta
 */
public class CuentaCorriente extends Cuenta {

	private	double sobregiroAutorizado;
	private	double sobregiroUsado;
	/**
	 * Constructor por defecto
	 */
	public CuentaCorriente() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor con parametros 
	 * @param consecutivo
	 * @param estado
	 * @param fechaCreacion
	 * @param saldo
	 * @param tipo
	 * @param numTarjeta
	 */
	public CuentaCorriente(int consecutivo, boolean estado, LocalDate fechaCreacion, double saldo, double sobregiroAutorizado) {
		super(consecutivo, estado, fechaCreacion, saldo);
		this.sobregiroAutorizado = sobregiroAutorizado;
	}
	
	/**
	 * Método Consignar a una Cuenta Corriente
	 * @param monto: monto a consignar en la cuenta Corriente
	 */
	public void consignar(int monto){
		
		if(getSobregiroUsado() == 0) {
			setSaldo(getSaldo() + monto);
		}
		else {
			if(monto > getSobregiroUsado()) {
				setSaldo(monto - getSobregiroUsado());
				//setSobregiroAutorizado(getSobregiroUsado() + getSobregiroAutorizado());
				setSobregiroUsado(0);
			}
			else {
				setSobregiroUsado(getSobregiroUsado() - monto);
				//setSobregiroAutorizado(getSobregiroAutorizado() + monto);
			}
		}
	}
	
	/**
	 * Método Retirar de una Cuenta Corriente
	 * @param monto: monto a retirar de la cuenta Corriente
	 * @throws CuentaException: Arroja una excepción si no tiene suficiente dinero para retirar
	 */
	public void retirar(int monto) throws CuentaException{
	
		if(monto <= getSaldo()) {
			setSaldo(getSaldo() - monto);
		}
		else {
			if( (monto + getSobregiroUsado() <= getSaldo() + getSobregiroAutorizado())) {		
				setSobregiroUsado(getSobregiroUsado() + monto - getSaldo());	
				/*monto -= getSaldo();
				setSobregiroAutorizado( Math.abs(monto - getSobregiroAutorizado()) );*/
				setSaldo(0);
			}
			else {
				throw new CuentaException("Fondos Insuficientes");
			}
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
	/**
	 * @return sobregiroAutorizado
	 */
	public double getSobregiroAutorizado() {
		return sobregiroAutorizado;
	}
	/**
	 * @param sobregiroAutorizado the sobregiroAutorizado to set
	 */
	public void setSobregiroAutorizado(double sobregiroAutorizado) {
		this.sobregiroAutorizado = sobregiroAutorizado;
	}
	
	/**
	 * @return sobregiroUsado
	 */
	public double getSobregiroUsado() {
		return sobregiroUsado;
	}
	/**
	 * @param sobregiroUsado the sobregiroUsado to set
	 */
	public void setSobregiroUsado(double sobregiroUsado) {
		this.sobregiroUsado = sobregiroUsado;
	}

	@Override
	public String toString() {
		return "\n\n\tCuenta Corriente: Sobregiro autorizado:$" + sobregiroAutorizado  + ", Sobregiro usado:$" + sobregiroUsado 
				+ ", " + super.toString();
	}			
}