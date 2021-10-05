import java.util.Arrays;

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
	public void setId(int identificador) {
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


public class PruebaFilas {

	public static void main(String[] args) {
		
		Impresion i1 = new Impresion(15,17,19);
		System.out.println(i1);
		Impresion i2 = new Impresion(10,15,58);
		System.out.println(i2);
		ImplementacionFilaEstatica ife1 = new ImplementacionFilaEstatica(2);
		ife1.insertar(i1);
		ife1.insertar(i2);
		System.out.println(ife1);
		ife1.sacar();
		System.out.println(ife1);
		ife1.sacar();
		System.out.println(ife1);
		ife1.sacar();

	}

}
