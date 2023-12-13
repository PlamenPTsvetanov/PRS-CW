package com.example.restxmlproject.utils;

import com.example.restxmlproject.entities.AccountEntity;
import com.example.restxmlproject.entities.UserEntity;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlBuilder {
    public static Document buildPushMessage(UserEntity sender, AccountEntity account) {
        Document doc = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("PushMessage");
            doc.appendChild(rootElement);

            JAXBContext senderJaxB = JAXBContext.newInstance(sender.getClass());
            Marshaller senderMarshaller = senderJaxB.createMarshaller();
            senderMarshaller.marshal(sender, doc.createElement("sender"));

            JAXBContext accountJaxb = JAXBContext.newInstance(sender.getClass());
            Marshaller accountMarshaller = accountJaxb.createMarshaller();
            accountMarshaller.marshal(account, doc.createElement("account"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

}
