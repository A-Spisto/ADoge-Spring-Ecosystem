package it.kaiser.kylo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.kaiser.kylo.model.Commenti;
import it.kaiser.kylo.service.KyloService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/kylo")
public class KyloController {

	@Autowired
	private KyloService kyloService;

	@GetMapping
	public String gettone() {

		return "Kylo Borka in maniera entusiasta";

	}

	@GetMapping(value = "/stream/post", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Commenti> gettoneDaUnAltraAPI() {

		return null;

	}

	@GetMapping(value = "/stream/post/{id}", produces = { "application/json" })
	public Mono<Commenti> gettoneSingoloDaUnAltraAPI(@PathVariable String id) {

		WebClient client = WebClient.create("https://jsonplaceholder.typicode.com/");

		return client.get().uri("/comments/{id}", id).retrieve().bodyToMono(Commenti.class);

	}
	
	@GetMapping(value = "/stream/post", produces = { "application/json" })
	public Flux<Commenti> gettoneTutto() {

		WebClient client = WebClient.create("https://jsonplaceholder.typicode.com/");

		return client.get()
				.uri("/comments")
				.retrieve()
				.bodyToFlux(Commenti.class);

	}
	
	@GetMapping(value = "/stream/postone", produces = { "application/json" })
	public ResponseEntity<List<Commenti>> gettoneTuttoToResponse() {

		WebClient client = WebClient.create("https://jsonplaceholder.typicode.com/");

		List<Commenti> listone = client.get()
				.uri("/comments")
				.retrieve()
				.bodyToFlux(Commenti.class)
				.collectList()
				.block();
		return new ResponseEntity<>(listone, HttpStatus.OK);
		

	}
	
	

	@PostMapping
	public ResponseEntity<String> postone() {
		ResponseEntity<String> res = null;

		return res;

	}

}
