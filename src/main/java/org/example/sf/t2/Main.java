package org.example.sf.t2;

import org.example.sf.Util;
import org.example.sf.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        List<Employee> employers = parseXML("data.xml");
        String parsedContent = Util.listToJson(employers);
        Util.writeString(parsedContent, "xmlToJson.json");
    }

    @SuppressWarnings("ConstantConditions")
    private static List<Employee> parseXML(
            String filename
    ) throws ParserConfigurationException, IOException, SAXException {
        File file = Util.getResourceFile(filename);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        List<String> elements = new ArrayList<>();
        List<Employee> result = new ArrayList<>();

        Node root = document.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeName().equals("employee")) {
                NodeList employeeNodes = node.getChildNodes();
                for (int j = 0; j < employeeNodes.getLength(); j++) {
                    Node employeeNode = employeeNodes.item(j);
                    if (Node.ELEMENT_NODE == employeeNode.getNodeType()) {
                        elements.add(employeeNode.getTextContent());
                    }
                }
                result.add(new Employee(
                        Long.parseLong(elements.get(0)),
                        elements.get(1),
                        elements.get(2),
                        elements.get(3),
                        Integer.parseInt(elements.get(4))
                ));
                elements.clear();
            }
        }
        return result;
    }
}
