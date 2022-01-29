/**
 * @author AndersonAlvarado
*
*/
public class CuentaException extends Exception{
	
	private String detalle; 
	
	/**
	 * Constructor con parametros
	 * @param a: Detalle de la excepción
	 */
	public CuentaException(String a){ 
			detalle = a; 
	} 
		
	// toString
	public String toString(){ 
			return "\n\tBanco Excp: " + detalle; 
	} 
}