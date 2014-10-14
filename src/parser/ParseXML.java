package parser;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ParseXML {
	
	public static void main(String argv[]){
		
		try {
			File xmlFile = new File ("home_plan.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document floor_plan = dBuilder.parse(xmlFile);
			
			System.out.println("Root element: " + floor_plan.getDocumentElement().getNodeName() +"\n" );
			
			floor_plan.getDocumentElement().normalize();
			NodeList nodeList = floor_plan.getElementsByTagName("floor");
			NodeList nodeAttributesList = floor_plan.getElementsByTagName("cell");
			for (int i=0; i < nodeList.getLength(); i++){
				
				Node node = nodeList.item(i);
				System.out.println("Current Element: " + node.getNodeName() + "\n");
				
				if(node.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement  =  (Element) node;
					
					for(int j = 0; j < nodeAttributesList.getLength(); j++ ){
						
						Node nodeIn = nodeAttributesList.item(j);
						Element eElementIn  =  (Element) nodeIn;
						
						System.out.println("Current Element: " + nodeIn.getNodeName() + "\n");						
						System.out.println("Coordinate X: " + eElementIn.getAttribute("xs") + "\n");
						System.out.println("Coordinate Y: " + eElementIn.getAttribute("ys") + "\n");
						System.out.println("Surface Type: " + eElementIn.getAttribute("ss") + "\n");
						System.out.println("Paths: " + eElementIn.getAttribute("ps") + "\n");
						System.out.println("Dirt: " + eElementIn.getAttribute("ds") + "\n");
						System.out.println("Charging Station: " + eElementIn.getAttribute("cs") + "\n");
						System.out.println("\n");
					}
				}
			
				
				
				
			}
			
			
			
			
			
		} catch(Exception e){
			System.out.println("Failed");
			e.printStackTrace();
		}
		
		
	}

}
