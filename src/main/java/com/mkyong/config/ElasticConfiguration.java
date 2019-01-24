package com.mkyong.config;

import com.mkyong.repository.ArtikelRepository;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfiguration {
    
    @Bean
	ArtikelRepository artikelRepository() { return new ArtikelRepository();}

	//Konfiguration des Ports für die DB
	@Bean
	RestHighLevelClient initRestHighLevelClient(){
		return new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
	}
}