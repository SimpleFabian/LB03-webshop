package com.mkyong.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

public class ElasticSearchEntity<T> {

	@Getter
	private String _index;
	@Getter
	private String _type;
	@Getter
	private String _id;
	@Getter
	private String _score;
	@Getter
	private T _source;
	
	@JsonProperty
	private BigDecimal[] sort;
	
	public ElasticSearchEntity(){
		
	}
	
	public T get_source() { return _source;}
}
