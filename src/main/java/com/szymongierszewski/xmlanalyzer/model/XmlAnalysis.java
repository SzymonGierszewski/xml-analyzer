package com.szymongierszewski.xmlanalyzer.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "xml_analyzes")
public class XmlAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "timestamp")
    private LocalDateTime analysisTimestamp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "xml_details_id", unique = true)
    private XmlDetails xmlDetails;

    public XmlAnalysis(XmlDetails xmlDetails) {
        this.xmlDetails = xmlDetails;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getAnalysisTimestamp() {
        return analysisTimestamp;
    }

    public XmlDetails getXmlDetails() {
        return xmlDetails;
    }
}
