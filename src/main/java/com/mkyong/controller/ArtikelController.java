package com.mkyong.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mkyong.model.Artikel;
import com.mkyong.model.ElasticSearchEntity;
import com.mkyong.repository.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ArtikelController {

	@Autowired
	ArtikelRepository artikelRepository;

	//List for the cart
	private ArrayList warenkorbItems = new ArrayList();

	//home page call
	@RequestMapping("/")
	public String listArtikel(Map<String, List<ElasticSearchEntity<Artikel>>> model) {

		List<ElasticSearchEntity<Artikel>> artikelList = artikelRepository.getArtikels();

		model.put("articles", artikelList);

		return "index";
	}

	//produkte page call
	@RequestMapping("/produkte")
	public String artikelList(Map<String, List<ElasticSearchEntity<Artikel>>> model) {

		List<ElasticSearchEntity<Artikel>> artikelList = artikelRepository.getArtikels();

		model.put("articles", artikelList);

		return "produkte";
	}

	//single produkt page call
	@RequestMapping("/produkt/{id}")
	public String artieklById(@PathVariable int id, Map<String, List<ElasticSearchEntity<Artikel>>> model) {

		List<ElasticSearchEntity<Artikel>> artikelList = artikelRepository.getArtikelById(id);

		model.put("article", artikelList);

		return "produkt";
	}

	//cart call
	@RequestMapping("/warenkorb")
	public String warenkorb(Map<String, List<ElasticSearchEntity<Artikel>>> model) {

		List<ElasticSearchEntity<Artikel>> artikelList = artikelRepository.getWarenkorb(warenkorbItems);

		model.put("articles", artikelList);

		if(artikelList.size() >= 1){
			return "warenkorb";
		}else{
			return "leererWarenkorb";
		}
	}

	//adding produkt to cart call
	@RequestMapping(value={"/add/{id}"})
	public String acceptOffer(@PathVariable int id, Map<String, List<ElasticSearchEntity<Artikel>>> model){
		warenkorbItems.add(id);
		List<ElasticSearchEntity<Artikel>> artikelList = artikelRepository.getArtikelById(id);

		model.put("article", artikelList);
		model.put("warenkorbItems", warenkorbItems);
		return "produkt";
	}

	//bestellen call
	@RequestMapping(value={"/bestellen"})
	public String bestellen(){
		warenkorbItems = new ArrayList();
		return "dankesSeite";
	}

	//login page call
	@RequestMapping(value={"/login"})
	public String login(){
		return "login";
	}

}