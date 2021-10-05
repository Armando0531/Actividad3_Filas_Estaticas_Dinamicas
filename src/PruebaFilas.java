
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
	private int indentificador;
	private double tama�o;
	private int numeroHojas;
	public Impresion(int indentificador, double tama�o, int numeroHojas) {
		super();
		this.indentificador = indentificador;
		this.tama�o =tama�o;
		this.numeroHojas = numeroHojas;
	}
	@Override
	public String toString() {
		return "Impresion [indentificador=" + indentificador + ", tama�o=" + tama�o + ", numeroHojas=" + numeroHojas
				+ "]";
	}
	
}




public class PruebaFilas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
