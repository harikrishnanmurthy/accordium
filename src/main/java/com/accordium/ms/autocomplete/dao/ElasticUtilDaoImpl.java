package com.accordium.ms.autocomplete.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.accordium.ms.autocomplete.config.ElasticUtilConfig;
import com.accordium.ms.autocomplete.entity.BookStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;

@Repository("ElasticUtilDao")
public class ElasticUtilDaoImpl implements ElasticUtilDao {
	
	@Autowired
	private ElasticUtilConfig elasticSearchConfig;
	
	@Autowired
	private JestClient elasticSearchClient;
	
	@Bean
	public JestClient createElasticSearchClient() throws IOException {
		HttpClientConfig.Builder httpClientConfigBuilder = new HttpClientConfig.Builder(
				"http://" + elasticSearchConfig.getElasticAddress() + ":" + elasticSearchConfig.getElasticPort())
				.connTimeout(new Integer(90000)).readTimeout(new Integer(90000))
				.maxConnectionIdleTime(1, TimeUnit.MINUTES).requestCompressionEnabled(true)
				.maxTotalConnection(8).multiThreaded(true);
		
		JestClientFactory factory =  new JestClientFactory();
		Gson gson = new GsonBuilder().create();
		factory.setHttpClientConfig(httpClientConfigBuilder.gson(gson).multiThreaded(true).build());
		return factory.getObject();
	}

	@Override
	public List<BookStore> matchAllRecords(String name) {
		String query = "{\n" +" \"query\" :{\n" +" \"wildcard\":{\"author\":\""+name.toLowerCase().concat("*")+"\"}}}";
		Search.Builder searchBuilder = new Search.Builder(query).addIndex(elasticSearchConfig.getIndexName())
				.addType(elasticSearchConfig.getDocType());
		try {
			SearchResult result = elasticSearchClient.execute(searchBuilder.build());
			List<BookStore> list = new ArrayList<BookStore>();
			List<Hit<BookStore, Void>> hitList = result.getHits(BookStore.class);
			hitList.forEach(hit -> {
				list.add(hit.source);
			});
			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
