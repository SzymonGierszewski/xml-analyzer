package com.szymongierszewski.xmlanalyzer.service;

import com.szymongierszewski.xmlanalyzer.model.XmlDetails;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public interface XmlDetailsService {

    XmlDetails createXmlDetails(XMLStreamReader xmlStreamReader) throws XMLStreamException;
}
