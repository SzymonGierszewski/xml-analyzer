package com.szymongierszewski.xmlanalyzer.controller;

import com.szymongierszewski.xmlanalyzer.model.AnalysisRequest;
import com.szymongierszewski.xmlanalyzer.model.XmlAnalysis;
import com.szymongierszewski.xmlanalyzer.service.PostsDetailsService;
import com.szymongierszewski.xmlanalyzer.service.XmlAnalysisService;
import com.szymongierszewski.xmlanalyzer.service.XmlAnalyzerService;
import com.szymongierszewski.xmlanalyzer.service.XmlDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/analyzes")
public class XmlAnalyzerController {

    private final XmlAnalysisService xmlAnalysisService;
    private final XmlAnalyzerService xmlAnalyzerService;

    @Autowired
    public XmlAnalyzerController(XmlAnalysisService xmlAnalysisService, XmlAnalyzerService xmlAnalyzerService) {
        this.xmlAnalysisService = xmlAnalysisService;
        this.xmlAnalyzerService = xmlAnalyzerService;
    }

    @PostMapping("/posts")
    public ResponseEntity<XmlAnalysis> createXmlAnalysis(@Valid @RequestBody AnalysisRequest analysisRequest) {
        XmlDetailsService xmlDetailsService = new PostsDetailsService();
        XmlAnalysis xmlAnalysis = xmlAnalyzerService.createXmlAnalysis(analysisRequest.getUrl(), xmlDetailsService);
        return ResponseEntity.status(HttpStatus.CREATED).body(xmlAnalysisService.saveXmlAnalysis(xmlAnalysis));
    }
}
