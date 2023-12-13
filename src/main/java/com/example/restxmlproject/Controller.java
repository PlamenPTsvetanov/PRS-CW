package com.example.restxmlproject;

import com.example.restxmlproject.utils.SignUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/xml")
public class Controller {


    @GetMapping("/sign")
    public HttpEntity<byte[]> signXml(@RequestBody MultipartFile xmlFile, HttpServletResponse response) throws Exception {
        if (xmlFile == null) {
            response.setStatus(403);
            response.getWriter().println("File not present!");
            return null;
        }

        if (!Objects.equals(xmlFile.getContentType(), "application/xml")) {
            response.setStatus(403);
            response.getWriter().println("Passed file is not XML!");
            return null;
        }

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        File f = convertMultipartFileToFile(xmlFile);
        Document xmlDocument = builder.parse(f);

        f.delete();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        response.setHeader("Content-Disposition", "attachment; filename=signed.xml");
        return new HttpEntity<>(SignUtil.signFile(xmlDocument).toByteArray(), headers);

    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile("temp", null);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }

        return file;
    }
}
