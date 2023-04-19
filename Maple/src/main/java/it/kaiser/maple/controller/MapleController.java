package it.kaiser.maple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.kaiser.maple.model.FotoB;
import it.kaiser.maple.service.MapleService;

@RestController
@RequestMapping("/maple")
public class MapleController {
	
	@Autowired
	private MapleService mapleService;

	@GetMapping
	public ResponseEntity<String> prendi() {

		return new ResponseEntity<>("Maple ti Saluta", HttpStatus.OK);
	}
	
	@GetMapping("/{fotoId}")
	public ResponseEntity<String> cercaFoto(@PathVariable Integer fotoId) {

		FotoB foto = mapleService.dammiFoto(fotoId);
		
		return new ResponseEntity<>(foto.getUrl(), HttpStatus.OK);
	}


}
