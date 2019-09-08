package com.szymongierszewski.xmlanalyzer.model;

import java.time.LocalDateTime;

public class XmlAnalysis {

    private LocalDateTime analysisTimestamp;
    private XmlDetails xmlDetails;

    public XmlAnalysis(XmlDetails xmlDetails) {
        this.xmlDetails = xmlDetails;
    }
}
