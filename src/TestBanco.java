import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
/**
 * @author Andersiton
 *
 */
public class TestBanco {
	
	public static Banco bank; 
	public static Scanner sn = new Scanner(System.in);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean salir = false;
		
		try {
			do{
				switch(menu()) {
					
					case 1: crearCuentas();
							break;					
					case 2: consignar();
							break;							
					case 3: retirar();
							break;
					case 4: transferir();
							break;
					case 5: cantidadCuentas();
							break;
					case 6: anioTitular();
							break;
					case 7: cuentasSobregiradas();
							break;
					case 8: serializarArchivo();
							break;
					case 9: deserializarArchivo();
							break;
					case 10:imprimirBanco();
							break;
					case 11:crearCuentasManual();
							break;		
					case 0: salir = true;
							System.out.println("\n\t VUELVA PRONTO!");
							break;
					default:System.out.println ("\n\t OPCIÓN INVÁLIDA! Intente de nuevo \n");
							break;
				}
			}while(!salir);
		}
		catch (InputMismatchException inputEx) {
			System.out.println("\n\tNo Digitó un número. Excepción: " + inputEx);
			
		}
	}
	
	/**
	 * Menú de opciones 
	 * @return opcion
	 */
	public static int menu() {
		System.out.println ("\n\n\t\t	MENÚ PRINCIPAL");
		System.out.println ("\n\t1. Crear cuentas y títulares");
		System.out.println ("\t2. Consignar en una cuenta");
		System.out.println ("\t3. Retirar de la cuenta");
		System.out.println ("\t4. Transferir de una cuenta a otra");
		System.out.println ("\t5. Cantidad de cuentas del titular");
		System.out.println ("\t6. Titulares nacidos después de un año dado");
		System.out.println ("\t7. Cuentas sobregiradas");
		System.out.println ("\t8. Serializar");
		System.out.println ("\t9. Deserializar");
		System.out.println ("\t10. Imprimir cuentas del banco");
		System.out.println ("\t11. Crear cuentas manualmente");
		System.out.println ("\t0. Salir");
		System.out.print ("\n\tDigite una opción: ");
		return sn.nextInt();
	}
	
	/**
	 * Impresión del Banco, con las cuentas y sus titulares 
	 */
	public static void imprimirBanco() {
		System.out.println (bank.toString());
	}
	
	/**
	 * Método Crear Cuentas y Titulares Para el Banco
	 */
	public static void crearCuentas(){
		int opc;
		try {
			bank = ManejadorArchivos.leeerArchivo("Cuentas2.txt");
			System.out.println ("\n\t\t Cuentas Creadas");
			do {
				System.out.println ("\n\n\t    ¿Desea ver las cuentas?");
				System.out.println ("\n\t1. Si");
				System.out.println ("\t2. No");
				System.out.print ("\n\t");
				opc = sn.nextInt();
				switch (opc) {
				case 1: imprimirBanco();
						break;					
				case 2: break;		
				default:System.out.println ("\n\tOPCIÓN INVÃ�LIDA! Intente de nuevo \n");
						break;
				}
			}while( opc != 2);	
		} 
		catch (TitularException titularEx) {
			System.out.println("\n\tExcepción: " + titularEx); 
		} 
		catch (CuentaException cuentaEx) {
			System.out.println("\n\tExcepción: " + cuentaEx + "  causa: " + cuentaEx.getMessage());
		}
		catch (BancoException bancoEx) {
			System.out.println("\n\tExcepción: " + bancoEx + "  causa: " + bancoEx.getMessage());
		} 
		catch (IOException ioe) {
			System.out.println("\n\ttExcepción: " + ioe); 
		}
		catch (Exception Ex) {
			System.out.println("\n\tExcepción: " + Ex);
		}
	
	}
	/**
	 * Método para crear las cuentas manulmanete
	 */
	public static void crearCuentasManual() {
		sn.nextLine();
		int consecutivo;
		try {

			System.out.print ("\n\tDigite el tipo de cuenta (Ahorros o Corriente): ");
			String tipo = UtilidadCadenas.eliminarEspaciosAdicionales(UtilidadCadenas.convertirMinusculas(sn.nextLine()));
			
			System.out.print ("\n\tDigite el saldo de la cuenta: ");
			float saldo = sn.nextInt();
			sn.nextLine();	
			
			if(tipo.equalsIgnoreCase("ahorros")) {
				System.out.print ("\n\tDigite el número de la tarjeta: ");
				String numTarjeta = sn.nextLine();
				consecutivo = bank.agregarCuentaAhorros(saldo, numTarjeta);
			}
			else {
				System.out.print ("\n\tDigite el sobregiro autorizado: ");
				double sobregiroAutorizado = sn.nextDouble();
				consecutivo = bank.agregarCuentaCorriente(saldo, sobregiroAutorizado);
			}
						
			System.out.print ("\n\tNúmero de titulares de la cuenta: ");
			int tit = sn.nextInt();
			sn.nextLine();
			for (int i = 0; i < tit; i++) {
				System.out.print ("\n\tDigite el nombre del titular: ");
				String nombre = sn.nextLine();
				System.out.print ("\n\tDigite fecha de nacimiento de  " + nombre + " (aaaa-mm-dd): ");
				String fechaNac = sn.nextLine();
				System.out.print ("\n\tDigite telefono fijo de  " + nombre+": ");
				String telefonoFijo = sn.nextLine();
				System.out.print ("\n\tDigite telefono celular de  " + nombre+": ");
				String telefonoCelular = sn.nextLine();
				System.out.print ("\n\tDigite dirección de  " + nombre+": ");
				String direccion = sn.nextLine();
				
				bank.agregarTitular(consecutivo, nombre, UtilidadCadenas.convertirLocalDate(fechaNac), telefonoFijo, telefonoCelular, direccion);
			}
		}
		catch (TitularException titularEx) {
			System.out.println("\n\tExcepción: " + titularEx); 
		} 
		catch (CuentaException cuentaEx) {
			System.out.println("\n\tExcepción: " + cuentaEx + "  causa: " + cuentaEx.getMessage());
		}
		catch (BancoException bancoEx) {
			System.out.println("\n\tExcepción: " + bancoEx + "  causa: " + bancoEx.getMessage());
		}
		catch (NullPointerException nullEx) {
			System.out.println("\n\tNo creó las cuentas. Excepción: " + nullEx);
			System.out.println("\tCreando cuentas... \n");
			crearCuentas();
		}
		catch (DateTimeException dateEx) {
			System.out.println("\n\tExcepción: " + dateEx.getMessage() + " el formato de fecha debe ser aaaa-mm-dd ");
			System.out.println("\tVuelva a intentarlo");
			crearCuentasManual();
		}
		catch (Exception Ex) {
			System.out.println("\n\tExcepción: " + Ex);
		}
	}
	
	/**
	 * Método Consignar en una Cuenta
	 * Se pide el número de la cuenta y el monto a consignar
	 */
	public static void consignar() {
		
		sn.nextLine();
		try {
			System.out.print ("\n\tDigite el número de la cuenta a la que va consignar: ");
			int numCuenta = sn.nextInt();
		
			System.out.print ("\n\tDigite el monto a consignar: ");
			int monto = sn.nextInt();
		
			Cuenta c = bank.consignar(numCuenta, monto);
			
			System.out.print ("\n\tConsignación Exitosa");
			System.out.print ("\n\n\tNúmero de cuenta: " + c.getConsecutivo());
			
			if(c instanceof CuentaCorriente) {
				CuentaCorriente corriente = (CuentaCorriente)c;
				System.out.print ("\n\tNuevo saldo: $" + corriente.getSaldo());
				System.out.print ("\n\tSobregiro Autorizado: $" + corriente.getSobregiroAutorizado());
				System.out.print ("\n\tSobregiro Usado: $" + corriente.getSobregiroUsado());
			}
			else {
				System.out.print ("\n\tNuevo saldo: $" + c.getSaldo());
			}
		}
		catch (BancoException bancoEx) {
			System.out.println("\n\tExcepción: " + bancoEx + "  causa: " + bancoEx.getMessage());
			int opc;
			do {
				System.out.println ("\n\n\t¿Desea volver a consultar las cuentas?");
				System.out.println ("\n\t1. Si");
				System.out.println ("\t2. No");
				System.out.print ("\n\t");
				opc = sn.nextInt();
				switch (opc) {
				case 1: imprimirBanco(); 
						break;					
				case 2: consignar();
						break;		
				default:System.out.println ("\n\tOPCIÓN INVÁLIDA! Intente de nuevo \n");
						break;
				}
			}while( opc != 2);
		}
		catch (InputMismatchException inputEx) {
			System.out.println("\n\tDigite un monto correcto. Excepción: " + inputEx);
			consignar();
		}
		catch (NullPointerException nullEx) {
			System.out.println("\n\tNo creó las cuentas. Excepción: " + nullEx);
			System.out.println("\tCreando cuentas... \n");
			crearCuentas();
		}
		catch (Exception Ex) {
			System.out.println("\n\tExcepción: " + Ex);
		}
	}
	
	/**
	 * Método Retirar de una Cuenta
	 * Se pide el número de la cuenta y el monto a retirar
	 */
	public static void retirar() {
		
		sn.nextLine();
		try {	
			System.out.print ("\n\tDigite el número de la cuenta de donde va a retirar: ");
			int numCuenta = sn.nextInt();
		
			System.out.print ("\n\tDigite el monto a retirar: ");
			int monto = sn.nextInt();
	
			Cuenta c = bank.retirar(numCuenta, monto);
			
			System.out.print ("\n\tRetiro Exitoso");
			System.out.print ("\n\n\tNúmero de cuenta: " + c.getConsecutivo());
			if(c instanceof CuentaCorriente) {
				CuentaCorriente corriente = (CuentaCorriente)c;
				System.out.print ("\n\tNuevo saldo: $" + corriente.getSaldo());
				System.out.print ("\n\tSobregiro Autorizado: $" + corriente.getSobregiroAutorizado());
				System.out.print ("\n\tSobregiro Usado: $" + corriente.getSobregiroUsado());
			}
			else {
				System.out.print ("\n\tNuevo saldo: $" + c.getSaldo());
			}
		}
		catch (BancoException bancoEx) {
			System.out.println("\n\tExcepción: " + bancoEx + "  causa: " + bancoEx.getMessage());
			int opc;
			do {
				System.out.println ("\n\n\t¿Desea volver a consultar las cuentas?");
				System.out.println ("\n\t1. Si");
				System.out.println ("\t2. No");
				System.out.print ("\n\t");
				opc = sn.nextInt();
				switch (opc) {
				case 1: imprimirBanco(); 
						break;				
				case 2: retirar();
						break;		
				default:System.out.println ("\n\tOPCIÓN INVÁLIDA! Intente de nuevo \n");
						break;
				}
			}while( opc != 2);
		}
		catch (InputMismatchException inputEx) {
			System.out.println("n\tDigite un monto correcto. Excepción: " + inputEx);
			retirar();
		}
		catch (NullPointerException nullEx) {
			System.out.println("\n\tNo creó las cuentas. Excepción: " + nullEx);
			System.out.println("\tCreando cuentas... \n");
			crearCuentas();
		}
		catch (Exception Ex) {
			System.out.println("\n\tExcepción: " + Ex);
		}
	}
	
	/**
	 * Método Transferir de una Cuenta a Otra
	 * Se pide el número de la cuenta de origen, el monto a transferir y el nÃºmero de la cuenta de destino
	 */
	public static void transferir() {
		
		sn.nextLine();
		try {	
			System.out.print ("\n\tDigite el número de la cuenta de origen: ");
			int cuentaOrigen = sn.nextInt();
			System.out.print ("\n\tDigite el monto a transferir: ");	
			int monto = sn.nextInt();
			System.out.print ("\n\tDigite el número de la cuenta de destino: ");
			int cuentaDestino = sn.nextInt();
	
			List<Cuenta> cuentasTransferir = bank.transferir(cuentaOrigen, monto,cuentaDestino);
			
			System.out.print ("\n\tTransferencia Exitosa");
			for (Cuenta c : cuentasTransferir) {
				System.out.print ("\n\n\tNúmero de cuenta: " + c.getConsecutivo());
				if(c instanceof CuentaCorriente) {
					CuentaCorriente corriente = (CuentaCorriente)c;
					System.out.print ("\n\tNuevo saldo: $" + corriente.getSaldo());
					System.out.print ("\n\tSobregiro Autorizado: $" + corriente.getSobregiroAutorizado());
					System.out.print ("\n\tSobregiro Usado: $" + corriente.getSobregiroUsado());
				}
				else {
					System.out.print ("\n\tNuevo saldo: $" + c.getSaldo());
				}
			}		
		}
		catch (BancoException bancoEx) {
			System.out.println("\n\tExcepción: " + bancoEx + "  causa: " + bancoEx.getMessage());
			int opc;
			do {
				System.out.println ("\n\n\t¿Desea volver a consultar las cuentas?");
				System.out.println ("\n\t1. Si");
				System.out.println ("\t2. No");
				System.out.print ("\n\t");
				opc = sn.nextInt();
				switch (opc) {
				case 1: imprimirBanco(); 
						break;				
				case 2: transferir();
						break;		
				default:System.out.println ("\n\tOPCIÓN INVÁLIDA! Intente de nuevo \n");
						break;
				}
			}while( opc != 2);		
		}
		catch (InputMismatchException inputEx) {
			System.out.println("n\tNoDigite un monto correcto. Excepción: " + inputEx);
			transferir();
		}
		catch (NullPointerException nullEx) {
			System.out.println("\n\tNo creó las cuentas. Excepción: " + nullEx);
			System.out.println("\tCreando cuentas... \n");
			crearCuentas();
		}
		catch (Exception Ex) {
			System.out.println("\n\tExcepción: " + Ex);
		}
	}
	
	/**
	 * Método Cantidad de Cuentas que Tiene un Titular
	 * Se pide el nombre del titular
	 * Se imprime el número de cuentas del titular y el saldo total
	 */
	public static void cantidadCuentas() {	
		
		sn.nextLine();
		try {
			float saldoTotal = 0;
			System.out.print ("\n\tDigite el nombre del titular: ");
			String nomTitular = UtilidadCadenas.eliminarEspaciosAdicionales(sn.nextLine());
			
			List<Cuenta>cuentasTitular = bank.cantidadCuentas(UtilidadCadenas.convertirMinusculas(nomTitular));
			System.out.println ("\n\tCuentas que posee " + nomTitular + ":");
			
			System.out.println (cuentasTitular.toString());
			
			for (Cuenta cuenta : cuentasTitular) {
				saldoTotal += cuenta.getSaldo(); 
			}
			System.out.println ("\n\tSaldo total: $" + saldoTotal);
		}
		catch (BancoException bancoEx) {
			System.out.println("\n\tExcepción: " + bancoEx );
			cantidadCuentas();				
		}
		catch (NullPointerException nullEx) {
			System.out.println("\n\tNo creó las cuentas. ExcepciÃ³n: " + nullEx);
			System.out.println("\tCreando cuentas... \n");
			crearCuentas();
		}
		catch (Exception Ex) {
			System.out.println("\n\tExcepción: " + Ex);
		}
	}
	
	/**
	 * Método Titulares que Nacieron Después de un AÃ±o 
	 * Se pide el aÃ±o
	 * Se imprimen los titulares nacidos después del año dado
	 */
	public static void anioTitular() {
		
		sn.nextLine();
		try {
			System.out.print ("\n\tDigite el año: ");
			int anio = sn.nextInt();
			
			//Set<Titular>anioTitulares = bank.anioTitular(anio);
			List<Titular>anioTitulares = bank.anioTitular(anio);
			
			System.out.print ("\n\tTitulares nacidos después de " + anio + ":\n");
			for (Titular titular : anioTitulares) {		
				System.out.print ("\n\tNombre: " + titular.getNombre() + ",  Fecha de nacimiento: " + titular.getFechaNac() + "\n");
			}
			if(anioTitulares.isEmpty()) {
				System.out.print ("\n\tNingún titular nacio durante o después de " + anio);
			}
			
		}
		catch (BancoException bancoEx) {
			System.out.println("\n\t" + bancoEx.getMessage());
			anioTitular();
		}
		catch (InputMismatchException inputEx) {
			System.out.println("n\tNo digito un número. Excepción: " + inputEx);
			anioTitular();
		}
		catch (NullPointerException nullEx) {
			System.out.println("\n\tNo creó las cuentas. Excepción: " + nullEx);
			System.out.println("\tCreando cuentas... \n");
			crearCuentas();
		}
		catch (Exception Ex) {
			System.out.println("\n\tExcepción: " + Ex);
		}
	}
	
	/**
	 * Método Cuentas con Sobregiro Usado Mayor a 0
	 * Se crea archivo sobregiradas.txt
	 * Se imprimer cuentas con sobregiro usado mayor a 0
	 */
	public static void cuentasSobregiradas(){
		try {
			List<Cuenta>sobregiradas = bank.cuentasSobregiradas();
			
			if(sobregiradas.isEmpty()) {
				System.out.println ("\n\tNinguna cuenta corriente tiene sobregiro");
			}
			else {
				ManejadorArchivos.EscribirAArchivoTexto(sobregiradas, "sobregiradas.txt");
				
				System.out.println ("\n\t\t Cuentas Sobregiradas");
				
				for (Cuenta cuenta : sobregiradas) {
					System.out.println (cuenta.toString());
				}	
			}	
		}
		catch (IOException ioe) {
			System.out.println("\n\tExcepción: " + ioe); 
		} 
		catch (NullPointerException nullEx) {
			System.out.println("\n\tNo creó las cuentas. Excepción: " + nullEx);
			System.out.println("\tCreando cuentas... \n");
			crearCuentas();
		}
		catch (Exception Ex) {
			System.out.println("\n\tExcepción: " + Ex);
		}
	}
	
	/**
	 * Método Serializar Banco
	 * Se crea el archivo JaviBank.dat
	 */
	public static void serializarArchivo() {
		try {
			ManejadorArchivos.serializarArchivo(bank, "JaviBank.dat");
			System.out.println("\n\tBanco Serializado \n");
		}
		catch (IOException ioe) {
			System.out.println("\n\tExcepción: " + ioe); 
		}
		catch (BancoException bancoEx) {
			System.out.println("\n\tExcepción: " + bancoEx + " causa: " + bancoEx.getMessage());
			System.out.println("\tCreando cuentas... \n");
			crearCuentas();
		}
		catch (Exception Ex) {
			System.out.println("\n\tExcepción: " + Ex);
		}
	}
	
	/**
	 * Método Deserializar archivo
	 * Se imprime banco deserializado del archivo JaviBank.dat
	 */
	public static void deserializarArchivo() {
		try {
			bank = ManejadorArchivos.deserializarArchivo("JaviBank.dat");
			System.out.println("\n\tBanco Deserealizado");
			imprimirBanco();
		}
		catch (IOException ioe) {
			System.out.println("\n\tNo Serializó el Banco. Excepción: " + ioe);
			System.out.println("\n\tSerializando Banco... \n");
			serializarArchivo();
		} 
		catch (NullPointerException nullEx) {
			System.out.println("\n\tNo Serializó el Banco. Excepción: " + nullEx);
			System.out.println("\n\tSerializando Banco... \n");
			serializarArchivo();
		}
		catch (Exception Ex) {
			System.out.println("\n\tExcepción: " + Ex);
		}
	}
}