package com.szymongierszewski.xmlanalyzer.repository;

import com.szymongierszewski.xmlanalyzer.model.XmlAnalysis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XmlAnalysisRepository extends CrudRepository<XmlAnalysis, Long> {
}
