package com.mkyong.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkyong.model.Artikel;
import com.mkyong.model.ElasticSearchEntity;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.util.ArrayList;
import java.util.List;

public class ArtikelConverter {
	
	public ArtikelConverter(){
		
	}

	
	public List<ElasticSearchEntity<Artikel>> getElasticSearchEntities(SearchResponse searchResponse) throws java.io.IOException {
		SearchHits searchHits = searchResponse.getHits();
		SearchHit[] hits = searchHits.getHits();
		ObjectMapper mapper = new ObjectMapper();
		List<ElasticSearchEntity<Artikel>> artikels = new ArrayList<>(Math.toIntExact(searchResponse.getHits().totalHits));
		for (SearchHit hit : hits){
			ElasticSearchEntity<Artikel> artikel = mapper.readValue(hit.toString(),  new TypeReference<ElasticSearchEntity<Artikel>>(){
			});
			artikels.add(artikel);
		}
		return artikels;
	}
}
