package com.mkyong.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mkyong.model.Artikel;
import com.mkyong.model.ElasticSearchEntity;
import com.mkyong.repository.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ArtikelController {

	@Autowired
	ArtikelRepository artikelRepository;

	private ArrayList warenkorbItems = new ArrayList();

	@RequestMapping("/")
	public String listArtikel(Map<String, List<ElasticSearchEntity<Artikel>>> model) {

		List<ElasticSearchEntity<Artikel>> artikelList = artikelRepository.getArtikels();

		model.put("articles", artikelList);

		return "index2";
	}

	@RequestMapping("/produkte")
	public String ArtikelList(Map<String, List<ElasticSearchEntity<Artikel>>> model) {

		List<ElasticSearchEntity<Artikel>> artikelList = artikelRepository.getArtikels();

		model.put("articles", artikelList);

		return "produkte";
	}

	@RequestMapping("/produkt")
	public String ArtieklById(Map<String, List<ElasticSearchEntity<Artikel>>> model) {

		List<ElasticSearchEntity<Artikel>> artikelList = artikelRepository.getArtikelById(1);

		model.put("article", artikelList);

		return "produkt";
	}

	@RequestMapping("/warenkorb")
	public String Warenkorb(Map<String, List<ElasticSearchEntity<Artikel>>> model) {

		List<ElasticSearchEntity<Artikel>> artikelList = artikelRepository.getWarenkorb(warenkorbItems);

		model.put("articles", artikelList);

		return "warenkorb";
	}

	@RequestMapping(value={"produkt/add/{id}"})
	public String acceptOffer(@PathVariable String id, Map<String, List<ElasticSearchEntity<Artikel>>> model){
		warenkorbItems.add(id);
		List<ElasticSearchEntity<Artikel>> artikelList = artikelRepository.getArtikelById(1);

		model.put("article", artikelList);
		model.put("warenkorbItems", warenkorbItems);
		return "produkt";
	}

}