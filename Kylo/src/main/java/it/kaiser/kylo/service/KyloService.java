package it.kaiser.kylo.service;

import java.util.List;

import it.kaiser.kylo.model.Commenti;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface KyloService {
	
	public Mono<Commenti> getFromId(Integer id);
	public Flux<Commenti> getAll();
	public List<Commenti> getAllAsAList();
	
}
