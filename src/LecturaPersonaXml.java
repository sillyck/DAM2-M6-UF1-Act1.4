import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LecturaPersonaXml {

	public static void main(String[] args) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("Persones.xml"));
			document.getDocumentElement().normalize();
			
			System.out.println("Elemento raíz: " + document.getDocumentElement().getNodeName());
			
			NodeList persones = document.getElementsByTagName("Persona");
			for(int i=0; i<persones.getLength();i++) {
				Node perso = persones.item(i);
				if(perso.getNodeType() == Node.ELEMENT_NODE) {

					Element elemento = (Element) perso;
					System.out.print("ID: " + getNodo("id", elemento) + " || ");
					System.out.print("Nom: " + getNodo("nom", elemento) + " || ");
					System.out.print("Cognom: " + getNodo("cognom", elemento) + " || ");
					System.out.print("DNI: " + getNodo("DNI", elemento) + " || ");
					System.out.print("Edat: " + getNodo("edat", elemento) + " || ");
					System.out.print("Salari: " + getNodo("salari", elemento) + " || ");
					System.out.println("");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String getNodo(String etiqueta, Element elem) {
		NodeList node = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node valornodo = (Node) node.item(0);
		return valornodo.getNodeValue();
	}

}
