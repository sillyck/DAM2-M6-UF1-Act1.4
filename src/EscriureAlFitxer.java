import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EscriureAlFitxer {

	public static void main(String[] args) throws IOException {
		
		File fitxer = new File("Treballadors.dat");
		RandomAccessFile accesArxiu = new RandomAccessFile(fitxer, "rw");

		int edats[] = {20, 20, 15, 30, 18, 25, 80, 40};
		String noms[] = {"Jordi", "Hector", "Eloy", "Xavier", "Cels", "Cristan", "Arnau", "Carlos"};
		String cognoms[] = {"Ribellas", "Vallve", "Altozano", "Martinez", "Montes", "Seva", "Mas", "Moreno"};
		String dnis[] = {"684149458V", "24817763V", "63193574O", "23145674Z", "95138475G", "66882223U", "83648284I", "43385477J"};
		Double salaris[] = {2000.0, 2000.0, 1000.0, 4000.0, 100.0, 7000.0, 8000.0, 2500.0};
		
		StringBuffer buffer = null;
		int n = cognoms.length;
		
		for(int i=0; i<n; i++) {
			accesArxiu.writeInt(i+1);
			buffer = new StringBuffer(noms[i]);
			buffer.setLength(10);
			accesArxiu.writeChars(buffer.toString());
			buffer = new StringBuffer(cognoms[i]);
			buffer.setLength(10);
			accesArxiu.writeChars(buffer.toString());
			buffer = new StringBuffer(dnis[i]);
			buffer.setLength(9);
			accesArxiu.writeChars(buffer.toString());
			accesArxiu.writeInt(edats[i]);
			accesArxiu.writeDouble(salaris[i]);
		}
		
	}

}
