package com.example.restxmlproject.utils;

import com.example.restxmlproject.DTO.AccountDTO;
import com.example.restxmlproject.DTO.UserDTO;
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

            UserDTO dtoSender = new UserDTO(sender);
            JAXBContext senderJaxB = JAXBContext.newInstance(dtoSender.getClass());
            Marshaller senderMarshaller = senderJaxB.createMarshaller();
            Element senderElement = doc.createElement("sender");
            senderMarshaller.marshal(dtoSender, senderElement);
            rootElement.appendChild(senderElement);

            AccountDTO dtoAccount = new AccountDTO(account);
            JAXBContext accountJaxb = JAXBContext.newInstance(dtoAccount.getClass());
            Marshaller accountMarshaller = accountJaxb.createMarshaller();
            Element accountElement = doc.createElement("account");
            accountMarshaller.marshal(dtoAccount, accountElement);
            rootElement.appendChild(accountElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

}
