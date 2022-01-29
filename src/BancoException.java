/**
 * @author AndersonAlvarado
*
*/
public class BancoException extends Exception{
	
	private String detalle; 

	/**
	 * Constructor con parametros
	 * @param a: Detalle de la excepción
	 */
	public BancoException(String a){ 
			detalle = a; 
	} 
		
	// toString
	public String toString(){ 
			return "\n\tBanco Excp: " + detalle; 
	} 
}