package com.mkyong.controller;

import java.util.List;
import java.util.Map;

import com.mkyong.model.Artikel;
import com.mkyong.model.ElasticSearchEntity;
import com.mkyong.repository.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArtikelController {

	@Autowired
	ArtikelRepository artikelRepository;

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}

	@RequestMapping("/artikel")
	public String listContact(Map<String, List<ElasticSearchEntity<Artikel>>> model) {

		List<ElasticSearchEntity<Artikel>> artikelList = artikelRepository.getArtikels();

		model.put("articles", artikelList);

		return "index2";
	}

}