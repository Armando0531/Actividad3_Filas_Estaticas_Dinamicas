import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

interface Validacion{
	Scanner input = new Scanner(System.in);
	
	public static int validacionNatural() {
		int ret = 0;
		boolean err = false;
		do {
			try {
				ret = input.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println("Error, intente de nuevo:");
				input.nextLine();
				err=true;
			}
			if (ret>0) {
				err=false;
			}else {
				System.out.println("Ingrese solo numeros");
				err=true;
			}
		}while(err);
		return ret;
	}
}

interface RegistroImpresiones{
	
	public boolean filaLlena();
	public void insertar(Impresion impresion);
	public boolean filaVacia();
	public Impresion sacar();
	public void mostrarFrente();
	public void totalImpresas();
	public void totalBytes();
	
}

class Impresion{
	private int identificador;
	private int tamaño;
	private int numeroHojas;
	public Impresion(int identificador, int tamaño, int numeroHojas) {
		super();
		this.identificador = identificador;
		this.tamaño =tamaño;
		this.numeroHojas = numeroHojas;
	}
	
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public int getTamaño() {
		return tamaño;
	}
	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}
	public int getNumeroHojas() {
		return numeroHojas;
	}
	public void setCntHojas(int numeroHojas) {
		this.numeroHojas = numeroHojas;
	}
	@Override
	public String toString() {
		return "Impresion [identificador=" + identificador + ", tamaño=" + tamaño + ", numeroHojas=" + numeroHojas
				+ "]";
	}
	
}

class ImplementacionFilaEstatica implements RegistroImpresiones{
	private Impresion registro[];
	private int frente;

	public ImplementacionFilaEstatica() {
	}
	public ImplementacionFilaEstatica(int tamaño) {
		this.registro = new Impresion[tamaño];
		this.frente = -1;
	}
	
	private Impresion[] getRegistro() {
		return registro;
	}
	private void setRegistro(Impresion[] registro) {
		this.registro = registro;
	}
	private int getFrente() {
		return frente;
	}
	private void setFrente(int frente) {
		this.frente = frente;
	}
	
	public boolean filaLlena() {
		return this.getFrente()==(this.getRegistro().length-1);
	}
	public void insertar(Impresion impresion) {
		if (this.filaLlena()){
			System.out.println("Fila llena");
		}else {
			this.setFrente(this.getFrente()+1);
			Impresion datos[]=this.getRegistro();
			Impresion datost[]=datos;
			int fr=this.getFrente();
			for (int i = fr; i > 0; i--) {
				datost[i]=datos[i-1];
			}
			
			datost[0]=impresion;
			this.setRegistro(datost);
		}
	}
	public boolean filaVacia() {
		if (frente==(-1)) {
			System.out.println("fila vacia");
		}
		return (frente==(-1));
	}
	public Impresion sacar() {
		if (this.filaVacia()){
			return null;
		}else {
			int fr = this.getFrente();
			Impresion datos[]=this.getRegistro();
			Impresion datosr[]=new Impresion[this.getRegistro().length];
			for (int i = 0; i < fr; i++) {
				datosr[i]=datos[i];
			}
			this.setRegistro(datosr);
			this.setFrente(this.getFrente()-1);
			return datos[fr];
		}
	}
	public void mostrarFrente() {
		if (this.filaVacia()) {
		}else {
			Impresion datos[]=this.getRegistro();
			int fr=this.getFrente();
			System.out.println("Frente: "+datos[fr]);
		}
	}
	public void totalImpresas() {
		Impresion datos[]=this.getRegistro();
		int sum=0;
		if(this.filaVacia()) {
		}else {
			for (int i = 0; i < datos.length; i++) {
				Impresion tmp = datos[i];
				sum+=tmp.getNumeroHojas();
			}
		}
		System.out.println("Total de hojas impresas: "+sum);
	}
	public void totalBytes() {
		Impresion datos[]=this.getRegistro();
		int sum=0;
		if(this.filaVacia()) {
		}else {
			for (int i = 0; i < datos.length; i++) {
				Impresion tmp = datos[i];
				sum+=tmp.getTamaño();
		}
		}
		System.out.println("Total de bytes recibidos: "+sum);
	}
	
	@Override
	public String toString() {
		return "ImplementacionFilaEstatica [registro=" + Arrays.toString(registro) + ", frente=" + frente + "]";
	}
	

}

class ImplementacionFilaDinamica implements RegistroImpresiones{
	private Queue<Impresion> registro = new LinkedList<Impresion>();

	public Queue<Impresion> getRegistro() {
		return registro;
	}
	public void setRegistro(Queue<Impresion> registro) {
		this.registro = registro;
	}
	
	@Override
	public boolean filaLlena() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void insertar(Impresion impresion) {
		Queue<Impresion> registro=this.getRegistro();
		registro.add(impresion);
		this.setRegistro(registro);
	}
	@Override
	public boolean filaVacia() {
		Queue<Impresion> registro=this.getRegistro();
		if (registro.isEmpty()) {
			System.out.println("fila vacia");
		}
		return registro.isEmpty();
	}
	@Override
	public Impresion sacar() {
		if (this.filaVacia()) {
			return null;
		}else {
			Queue<Impresion> registro=this.getRegistro();
			return registro.poll();
		}
	}
	@Override
	public void mostrarFrente() {
		if (this.filaVacia()) {
		}else {
			Queue<Impresion> registro=this.getRegistro();
			Impresion frente = registro.peek();
			System.out.println(frente);
		}
	}
	@Override
	public void totalImpresas() {
		Queue<Impresion> reg=this.getRegistro();
		int sum=0;
		if(this.filaVacia()) {
		}else {
			for (int i=0;i<reg.size();i++) {
				Impresion tmp = reg.poll();
				sum+=tmp.getNumeroHojas();
				reg.add(tmp);
			}
		}
		System.out.println("Total de hojas impresas: "+sum);
	}
	@Override
	public void totalBytes() {
		Queue<Impresion> reg=this.getRegistro();
		int sum=0;
		if(this.filaVacia()) {
		}else {
			for (int i=0;i<reg.size();i++) {
				Impresion tmp = reg.poll();
				sum+=tmp.getTamaño();
				reg.add(tmp);
			}
		}
		System.out.println("Total de bytes recibidos: "+sum);
	}
	
	@Override
	public String toString() {
		return "ImplementacionFilaDinamica [registro=" + registro + "]";
	}
	
	
}

public class PruebaFilas {

	public static void main(String[] args) {
		
		int id=0;
		byte opc=0;
		boolean salir=false;
		boolean salir1=false;
		
		ImplementacionFilaEstatica ife1 = new ImplementacionFilaEstatica(2);
		ImplementacionFilaDinamica ifd1 = new ImplementacionFilaDinamica();
		
		do {
			System.out.println("1)Pila estatica");
			System.out.println("2)Pila dinamica");
			System.out.println("3)Salir");
			opc = (byte) Validacion.validacionNatural();
			switch (opc) {
			case 1:
				do {
					salir=false;
					System.out.println("1)Agregar a la cola de impresión");
					System.out.println("2)Mostrar el total de hojas impresas");
					System.out.println("3)Mostrar impresión");
					System.out.println("4)Mostrar total de bytes recibidos para la impresion");
					System.out.println("5)Salir");
					
					opc = (byte) Validacion.validacionNatural();
					switch (opc) {
					case 1:
						System.out.println("Cantidad:");
						int cnt = Validacion.validacionNatural();
						ife1 = new ImplementacionFilaEstatica(cnt);
						for(int i=0;i<cnt;i++) {
							System.out.println("Impresion "+(i+1)+":");
							System.out.println("Cantidad de hojas:");
							int cntHojas = Validacion.validacionNatural();
							System.out.println("Tamaño en bytes:");
							int tamaño = Validacion.validacionNatural();
							ife1.insertar(new Impresion(++id,tamaño,cntHojas));
						}
						break;
					case 2:
						ife1.totalImpresas();
						break;
					case 3:
						ife1.mostrarFrente();
						ife1.sacar();
						System.out.println(ife1);
						break;
					case 4:
						ife1.totalBytes();
						break;
					case 5:
						salir=true;
						break;
					default:
						System.out.println("Opcion no valida");
						break;
					}
				} while (!salir);
				break;
			case 2:
				do {
					salir=false;
					System.out.println("1)Agregar a la cola de impresión");
					System.out.println("2)Mostrar el total de hojas impresas");
					System.out.println("3)Mostrar impresión");
					System.out.println("4)Mostrar total de bytes recibidos para la impresion");
					System.out.println("5)Salir");
					
					opc = (byte) Validacion.validacionNatural();
					switch (opc) {
					case 1:
						System.out.println("Cantidad:");
						int cnt = Validacion.validacionNatural();
						ifd1 = new ImplementacionFilaDinamica();
						for(int i=0;i<cnt;i++) {
							System.out.println("Impresion "+(i+1)+":");
							System.out.println("Cantidad de hojas:");
							int cntHojas = Validacion.validacionNatural();
							System.out.println("Tamaño en bytes:");
							int tamaño = Validacion.validacionNatural();
							ifd1.insertar(new Impresion(++id,tamaño,cntHojas));
						}
						break;
					case 2:
						ifd1.totalImpresas();
						break;
					case 3:
						ifd1.mostrarFrente();
						ifd1.sacar();
						System.out.println(ifd1);
						break;
					case 4:
						ifd1.totalBytes();
						break;
					case 5:
						salir=true;
						break;
					default:
						System.out.println("Opcion no valida");
						break;
					}
				} while (!salir);
				break;
			case 3:
				salir1=true;
				break;
			default:
				System.out.println("Opcion no valida");
				break;
			}
		} while (!salir1);
		System.out.println();
		System.out.println("Fin del programa");

	}

}
