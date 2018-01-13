package com.accordium.ms.autocomplete.dao;

import java.util.List;

import com.accordium.ms.autocomplete.entity.BookStore;

public interface ElasticUtilDao {

	List<BookStore> matchAllRecords(String name);
	
}
