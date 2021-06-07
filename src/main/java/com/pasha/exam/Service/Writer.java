package com.pasha.exam.Service;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Writer {

    String filepath = "result.xml";
    int connectedReadersCount;
    Document doc;
    Element rootElement;

    public Writer(int connectedReadersCount) {
        this.connectedReadersCount = connectedReadersCount;
        initXml();
    }

    public synchronized void addUrl(String url, String data) {
        Element item = doc.createElement("Item");
        Element urlElement = doc.createElement("url");
        urlElement.appendChild(doc.createTextNode(url));
        Element dataElement = doc.createElement("item_data");
        dataElement.appendChild(doc.createTextNode(data));
        item.appendChild(urlElement);
        item.appendChild(dataElement);
        rootElement.appendChild(item);
    }

    public synchronized void disconnectReader() {
        connectedReadersCount--;
        if (connectedReadersCount == 0) {
            saveXml();
        }
    }

    private void initXml() {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
            rootElement = doc.createElement("Data");
            doc.appendChild(rootElement);
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        }
    }

    private void saveXml() {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e ) {
            e.printStackTrace();
        } catch (TransformerException e ) {
            e.printStackTrace();
        }
    }
}
