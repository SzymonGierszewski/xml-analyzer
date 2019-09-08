package com.szymongierszewski.xmlanalyzer.service;

import com.szymongierszewski.xmlanalyzer.exceptions.XmlProcessingException;
import com.szymongierszewski.xmlanalyzer.model.XmlAnalysis;
import com.szymongierszewski.xmlanalyzer.model.XmlDetails;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class XmlAnalyzerService {

    private final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

    public XmlAnalysis createXmlAnalysis(URL url, XmlDetailsService xmlDetailsService) {
        try (InputStream inputStream = url.openStream()) {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
            XmlDetails xmlDetails = xmlDetailsService.createXmlDetails(xmlStreamReader);
            return new XmlAnalysis(xmlDetails);
        } catch (IOException | XMLStreamException e) {
            // TODO logger
            throw new XmlProcessingException("Error processing request");
        }
    }

}
