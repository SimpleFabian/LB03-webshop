package com.mkyong.repository;

import com.mkyong.model.Artikel;
import com.mkyong.model.ElasticSearchEntity;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtikelRepository {
    
    public ArtikelRepository(){
    	
    }

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    
    public List<ElasticSearchEntity<Artikel>> getArtikels(){
    	try {
    		SearchRequest searchRequest = new SearchRequest("webshop");
    		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
    		searchRequest.source(searchSourceBuilder);
    	
    		SearchResponse searchResponse;
			searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
	    	return new ArtikelConverter().getElasticSearchEntities(searchResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new ArrayList();
    }

    public List<ElasticSearchEntity<Artikel>> getArtikelById(int id){
    	try {
    		SearchRequest searchRequest = new SearchRequest("webshop");
    		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    		searchSourceBuilder.query(QueryBuilders.matchQuery("_id", id));
    		searchRequest.source(searchSourceBuilder);

    		SearchResponse searchResponse;
			searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
	    	return new ArtikelConverter().getElasticSearchEntities(searchResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new ArrayList();
    }

    public List<ElasticSearchEntity<Artikel>> getWarenkorb(ArrayList warenkorbItems){
    	try {
    		SearchRequest searchRequest = new SearchRequest("webshop");
    		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

    		for(int i = 0; warenkorbItems.size() <= i; i++){
				searchSourceBuilder.query(QueryBuilders.matchQuery("_id", warenkorbItems.get(i)));
			}
    		searchRequest.source(searchSourceBuilder);

    		SearchResponse searchResponse;
			searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
	    	return new ArtikelConverter().getElasticSearchEntities(searchResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new ArrayList();
    }

}
