/**
 * @author AndersonAlvarado
*
*/
public class TitularException extends Exception{
	
	private String detalle; 
	
	/**
	 * Constructor con parametros
	 * @param a: Detalle de la excepción
	 */
	public TitularException(String a){ 
			detalle = a; 
	} 
		
	// toString
	public String toString(){ 
			return "\n\tBanco Excp: " + detalle; 
	} 
}