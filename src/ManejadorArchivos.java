import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Anderson Alvarado
 *
 */
public class ManejadorArchivos {
	
	//CONSTRUCTOR
	public ManejadorArchivos() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Método Leer Archivo
	 * @param nomArchivo: Nombre del archivo
	 * @return Banco: Banco creado, con las cuentas y titulares
	 * @throws BancoException: Arroja excepción si no hay una cuenta con ese número de cuenta
	 * @throws CuentaException: Arroja una excepción si no hay un titular con ese nombre
	 * @throws IOException: Arroja una excepción si no se abre el archivo correctamente
	 */
	public static Banco leeerArchivo(String nomArchivo)throws BancoException, CuentaException, TitularException, IOException {
		
		Banco bank = new Banco(); 
		int consecutivo;
		try{
			InputStreamReader input = new InputStreamReader(new FileInputStream(nomArchivo));
			BufferedReader fa = new BufferedReader(input);
			
			String cad = fa.readLine(); // linea #Cuenta
			// Se lee hasta el FIN que tiene al archivo
			while (!cad.equalsIgnoreCase("#FIN")){		
				
				cad = fa.readLine(); // linea #Saldo------Tipo---------Tarjeta/Sobregiro
				
				cad = fa.readLine(); // datos Cuenta  
				cad = UtilidadCadenas.eliminarGuiones(cad);// cambiar los - por solo un -
				String[] datosCuenta = UtilidadCadenas.tokenizador(cad,"-");//Se parte en tokens, el separador es "-"
				
				double saldo = UtilidadCadenas.convertirDouble(datosCuenta[0]); // convertir a float el saldo
				
				if(datosCuenta[1].equalsIgnoreCase("ahorros")) {
					consecutivo = bank.agregarCuentaAhorros(saldo, datosCuenta[2]); // consecutivo de la cuenta
				}
				else {
					double num = UtilidadCadenas.convertirDouble(datosCuenta[2]); // convertir a int el sobregiro
					consecutivo = bank.agregarCuentaCorriente(saldo, num); // consecutivo de la cuenta
				}
						
				cad = fa.readLine(); // linea #Titulares
				cad = fa.readLine(); // linea #nombre----------fecha Nacimiento----TelFijo-----Celular---Direccion	
				cad = fa.readLine(); //datos Titulares
			
				while(!cad.equalsIgnoreCase("0")) {
					
					String [] datosTitulares = UtilidadCadenas.tokenizador(cad,"\\*"); //Se parte en tokens, el separador es "*"	
					for (int i=0; i<datosTitulares.length; i++) {
						datosTitulares[i] = UtilidadCadenas.eliminarEspaciosAdicionales(datosTitulares[i]);
					}
					LocalDate fechaNac = UtilidadCadenas.convertirLocalDate(datosTitulares[1]);// se convierte en formato localDate
					// añadir titular	
					bank.agregarTitular(consecutivo, datosTitulares[0], fechaNac, datosTitulares[2], datosTitulares[3],datosTitulares[4]); 
					cad = fa.readLine(); //datos Titulares
				}	
				cad=fa.readLine(); // siguiente linea          
			}
		}
		catch (CuentaException cuentaEx) {
			throw cuentaEx; 
		}
		catch (TitularException titularEx) {
			throw titularEx; 
		} 
		catch (BancoException bancoEx){
			throw bancoEx; 
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (DateTimeException dateEx) {
			System.out.println("\n\tError en el formato de fecha, cuentas creadas incompletas: " + dateEx); 
		}
		catch (ArrayIndexOutOfBoundsException ArrayEx) {
			System.out.println("\n\tError en el archivo " + ArrayEx); 
		}
		catch (Exception Ex) {
			throw Ex;
		}
		return bank;
	}
	
	/**
	 * Método Escribir Archivo
	 * @param sobregiradas: Lista de cuentas con sobregiro mayor a 0
	 * @param nomArchivo: Nombre del archivo
	 * @throws IOException: Arroja una excepción si no se crea el archivo correctamente
	 */
	public static void EscribirAArchivoTexto(List<Cuenta>sobregiradas, String nomarch) throws IOException{
			
		try{
			// Se crea el flujo de salida al archivo de nombre nomarch
			OutputStreamWriter out= new OutputStreamWriter(new FileOutputStream(nomarch));
		        	
			for (Cuenta sobregirada : sobregiradas) {
				out.write(sobregirada.toString());
				out.write("\n");
			}
			
			/*
			for(int i=0; i<sobregiradas.size(); i++) {
				
				
				out.write("#Cuenta\n");
		        out.write("#Saldo------Tipo---------SobregiroAutorizado-------SobregiroUsado\n");
		        out.write((int)sobregiradas.get(i).getSaldo() + "-------" + sobregiradas.get(i).getTipo() + "-------"  
		        			    + sobregiradas.get(i).getSobregiroAutorizado() + "-------" + sobregiradas.get(i).getSobregiroUsado() + "\n");
		        out.write("#Titulares\n");
		        out.write("#nombre----------fecha Nacimiento----TelFijo-----Celular---Direccion\n");
		        for (int j = 0; j < sobregiradas.get(i).getTitulares().size(); j++) {
		        	out.write(sobregiradas.get(i).getTitulares().get(j).getNombre() + " *");  
		        	out.write(sobregiradas.get(i).getTitulares().get(j).getFechaNac() + " *");
		        	out.write(sobregiradas.get(i).getTitulares().get(j).getTelefonoFijo() + " *");
		        	out.write(sobregiradas.get(i).getTitulares().get(j).getTelefonoCelular() + " *");
		        	out.write(sobregiradas.get(i).getTitulares().get(j).getDireccion() + "\n");
				}
		        out.write("0\n");
			}
		    out.write("#FIN");*/
		    out.close();
		}
		catch(IOException ioe){
			throw ioe;
		}
		catch (Exception Ex) {
			System.out.println("\n\tExcepción: " + Ex);
		}
	}
	
	/**
	 * Método Serializar Archivo
	 * @param bank: Banco para serializar
	 * @param nomArchivo: Nombre del archivo
	 * @throws IOException: Arroja una excepción si no se crea el archivo correctamente
	 */
	public static void serializarArchivo(Banco bank, String nomarch) throws IOException, Exception, BancoException{
		
		try{
	  		if(bank != null) {
	  			FileOutputStream fileOut = new  FileOutputStream(nomarch);
		  		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		  		out.writeObject(bank);
		  		out.close();
	  		}
	  		else {
	  			throw new BancoException("El Banco no ha sido creado");
	  		}	
		}
		catch(IOException ioe){
			throw ioe;
		}
		catch (Exception Ex) {
			throw Ex;
		}
	}
	
	/**
	 * Método Deserializar Archivo
	 * @param nomArchivo: Nombre del archivo a deserializar
	 * @throws IOException: Arroja una excepción si no se abre el archivo correctamente
	 */
	public static Banco deserializarArchivo(String nomarch) throws IOException{
	   
		Banco bank = null;
		try{
			// Se abre un flujo de entrada
	  		FileInputStream fileIn = new FileInputStream(nomarch);
	  		// Se especializa a que el filtro permita leer objetos
			ObjectInputStream in=new ObjectInputStream(fileIn);
			// Le asigna a cur, el objeto leido del archivo
			bank = (Banco) in.readObject();
		}
		catch(IOException ioe){
			throw ioe;
		}
		catch (Exception Ex) {
			System.out.println("\n\tExcepción: " + Ex);
		}
		return bank;
	}
}