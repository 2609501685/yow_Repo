package xml;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import _2024_07_10.Item;

public class XMLParse {
	
	String filePath;
	LinkedList<Item> items = new LinkedList<Item>();
	
	public XMLParse(String filePath) throws ParserConfigurationException, SAXException, IOException {
		this.filePath = filePath;
		parse();
	}
	
	public LinkedList<Item> getItems() {
		return items;
	}
	
	private void parse() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(new File(filePath));
		Element root = document.getDocumentElement();
		NodeList nodeList = root.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i) instanceof Element) {
				Element element = (Element)nodeList.item(i);
				String name = null;
				String currentNum = null;
				String totalnum = null;
				NodeList eleNodeList = element.getChildNodes();
				for (int j = 0; j < eleNodeList.getLength(); j++) {
					if (eleNodeList.item(j) instanceof Element) {
						Element ele = (Element)eleNodeList.item(j);
						switch (ele.getNodeName()) {
							case "name": name = ele.getTextContent(); break;
							case "currentNum": currentNum = ele.getTextContent(); break;
							case "totalnum": totalnum = ele.getTextContent(); break;
						}
					}
				}
				Item item = new Item(name, Integer.valueOf(currentNum), Integer.valueOf(totalnum));
				items.add(item);
			}
		}
	}
	
}
