package it.kaiser.kylo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import it.kaiser.kylo.model.Commenti;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class KyloServiceImpl implements KyloService {

	private WebClient client;
	
	@Autowired
	public KyloServiceImpl(@Value("${kylo.client.url}") String clientUrl) {
		this.client =  WebClient.create(clientUrl);
		
	}
	
	
	
	

	@Override
	public Mono<Commenti> getFromId(Integer id) {

		return client.get()
				.uri("/comments/{id}", id)
				.retrieve()
				.bodyToMono(Commenti.class);

	}

	@Override
	public Flux<Commenti> getAll() {
		return client.get().uri("/comments")
				.retrieve()
				.bodyToFlux(Commenti.class);

	}

	@Override
	public List<Commenti> getAllAsAList() {
		
		return client.get().uri("/comments")
				.retrieve().bodyToFlux(Commenti.class)
				.collectList()
				.block();
	}

}
