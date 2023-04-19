package it.kaiser.kylo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/")
public class KyloFirstController {

	
	
	@GetMapping(value="/stream/post")
	public String primo() {

		return "Hai trovato il primo Kylo";
	}

}
