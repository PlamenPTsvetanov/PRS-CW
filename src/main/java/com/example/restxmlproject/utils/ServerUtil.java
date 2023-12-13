package com.example.restxmlproject.utils;

import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ServerUtil {
    public static int sendToServer(Document document) throws Exception {

        URL url = new URL("http://localhost:8081/server/validate");
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Accept", "application/xml");
        httpConnection.setRequestProperty("Content-Type", "application/xml");

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));

        httpConnection.setDoOutput(true);
        OutputStream outStream = httpConnection.getOutputStream();
        OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, StandardCharsets.UTF_8);
        outStreamWriter.write(writer.toString());
        outStreamWriter.flush();
        outStreamWriter.close();
        outStream.close();

        return httpConnection.getResponseCode();
    }
}
