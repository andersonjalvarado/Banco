import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anderson Alvarado
 *
 */
public class UtilidadCadenas {

	//CONSTRUCTOR
	public UtilidadCadenas() {
		// TODO Auto-generated constructor stub
	}
	
	//METODOS
	/**
	 * @param cad: cadena a la que se le va a eliminar espacios finales
	 * @return la cadena sin espacios finales
	 */
	public static String eliminarEspacios(String cad){
		return cad.replaceAll("\\s+"," ");
	}
	
	/**
	 * @param cad: cadena a la que se le va a eliminar espacios
	 * @return la cadena con solo un espacio entre las palabras
	 */
	public static String eliminarEspaciosAdicionales(String cad){
		cad = eliminarEspacios(cad);
		cad = cad.trim();
		return cad;
	}
	
	/**
	 * @param cad: cadena a la que se le va a eliminar los caracteres especiales
	 * @return la cadena con solo un - entre las palabras
	 */
	public static String eliminarGuiones(String cad){
		return cad.replaceAll("[-]+","-");
	}
		
	/**
	 * @param tok: expresión por la cual se puede partir una cadena
	 * @return un conjunto de subcadenas "partidas por el token dado"
	 */
	public static String[] tokenizador(String cad, String tok){
		String [] tokens=cad.split(tok);
		return tokens;
	}
	
	/**
	 * @param cad: la cadena a la que se va a convertir a float
	 * @return num de tipo float
	 */
	public static double convertirDouble(String cad){
		double num = Double.parseDouble(cad);
		return num;
	}
	
	/**
	 * @param cad: la cadena a la que se va a convertir a entero
	 * @return num de tipo int
	 */
	public static int convertirInt(String cad){
		int num = Integer.parseInt(cad);
		return num;
	}
	/**
	 * @param cad: la cadena a la que se va a convertir a LocalDate
	 * @return fecha de tipo LocalDate
	 */
	public static LocalDate convertirLocalDate(String cad){
		LocalDate fecha = LocalDate.parse(cad);
		return fecha;
	}
	
	/**
	 * @param cad: la cadena a la que se va a convertir a minusculas
	 * @return cadena en minusculas
	 */
	public static String convertirMinusculas(String cad){
		return cad.toLowerCase();
	}
}