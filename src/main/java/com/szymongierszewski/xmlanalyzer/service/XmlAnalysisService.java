package com.szymongierszewski.xmlanalyzer.service;

import com.szymongierszewski.xmlanalyzer.model.XmlAnalysis;
import com.szymongierszewski.xmlanalyzer.repository.XmlAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XmlAnalysisService {

    private final XmlAnalysisRepository xmlAnalysisRepository;

    @Autowired
    public XmlAnalysisService(XmlAnalysisRepository xmlAnalysisRepository) {
        this.xmlAnalysisRepository = xmlAnalysisRepository;
    }

    public XmlAnalysis saveXmlAnalysis(XmlAnalysis xmlAnalysis) {
        return xmlAnalysisRepository.save(xmlAnalysis);
    }
}
