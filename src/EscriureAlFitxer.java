import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EscriureAlFitxer {

	public static void main(String[] args) throws IOException {
		
		File fitxer = new File("Treballadors.dat");
		RandomAccessFile accesArxiu = new RandomAccessFile(fitxer, "rw");

		int edats[] = {20, 18, 21, 22};
		String noms[] = {"Jordi", "Hector", "Eloy", "Xavier"};
		String cognoms[] = {"Ribellas", "Vallvé", "Altozano", "Martinez"};
		String dnis[] = {"12345678L", "93647586T", "63193574O", "23145674Z"};
		Double salaris[] = {2000.0, 1500.0, 1200.0, 1900.0};
		
		StringBuffer buffer = null;
		int n = cognoms.length;
		
		for(int i=0; i<n; i++) {
			accesArxiu.writeInt(i+1);
			buffer = new StringBuffer(noms[i]);
			buffer.setLength(10);
			buffer = new StringBuffer(cognoms[i]);
			buffer.setLength(10);
			buffer = new StringBuffer(dnis[i]);
			buffer.setLength(10);
			accesArxiu.writeInt(edats[i]);
			accesArxiu.writeChars(buffer.toString());
			accesArxiu.writeDouble(salaris[i]);
		}
		
	}

}
