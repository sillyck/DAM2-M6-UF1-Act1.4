import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class CrearXML {

	public static void main(String[] args) throws IOException {
		
		File fichero = new File("Treballadors.dat");
		RandomAccessFile file = new RandomAccessFile (fichero, "r");
		
		int id, edad, posicio=0;
		char[] DNI = new char[9];
		char[] nom = new char[10]; 
		char[] cognom = new char[10];
		char auxDNI, auxNom, auxCognom;
		Double salari;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "Persones", null);
			document.setXmlVersion("1.0");
			
			for(;;) {
				file.seek(posicio);
				id=file.readInt();
				for(int i=0; i<DNI.length; i++) {
					auxDNI = file.readChar();
					cognom[i] = auxDNI;
				}
				
				for(int i=0; i<nom.length; i++) {
					auxNom = file.readChar();
					cognom[i] = auxNom;
				}
				
				for(int i=0; i<cognom.length; i++) {
					auxCognom = file.readChar();
					cognom[i] = auxCognom;
				}
				
				String DNIs = new String(DNI);
				String noms = new String(nom);
				String cognoms = new String(cognom);
				edad=file.readInt();
				salari=file.readDouble();
				
				if(id<0) {
					Element raiz = document.createElement("persona");
					
					document.getDocumentElement().appendChild(raiz);
					crearElemento("id", Integer.toString(id), raiz, document);
					crearElemento("DNI", DNIs.trim() ,raiz, document);
					crearElemento("nom", noms.trim() ,raiz, document);
					crearElemento("cognom", cognoms.trim() ,raiz, document);
					crearElemento("edat", Integer.toString(edad) ,raiz, document);
					crearElemento("salari", Double.toString(salari) ,raiz, document);
				}
				posicio = posicio + 74;
				if(file.getFilePointer() == file.length()) break;
			}
			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("Persones.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
		}catch (Exception e) {
			System.out.println("Error: " + e);
		}
		file.close();
	}
	
	static void crearElemento (String datoPerso, String valor, Element raiz, Document document) {
		Element elem = document.createElement(datoPerso);
		Text text = document.createTextNode(valor);
		raiz.appendChild(elem);
		raiz.appendChild(text);
	}

}
