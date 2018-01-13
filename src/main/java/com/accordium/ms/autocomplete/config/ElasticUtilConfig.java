package com.accordium.ms.autocomplete.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticUtilConfig {

	@Value("${elasticsearch.host}")
	private String elasticAddress;
	
	@Value("${elasticsearch.port}")
	private String elasticPort;
	
	@Value("${elasticsearch.index}")
	private String indexName;
	
	@Value("${elasticsearch.doctype}")
	private String docType;

	public String getElasticAddress() {
		return elasticAddress;
	}

	public void setElasticAddress(String elasticAddress) {
		this.elasticAddress = elasticAddress;
	}

	public String getElasticPort() {
		return elasticPort;
	}

	public void setElasticPort(String elasticPort) {
		this.elasticPort = elasticPort;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}
}
