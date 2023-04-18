package it.kaiser.maple.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.kaiser.maple.model.FotoB;

@Service
public class MapleServiceImpl implements MapleService {

	@Override
	public FotoB dammiFoto(Integer id) {

		FotoB foto = null;

		// "https://jsonplaceholder.typicode.com"
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://jsonplaceholder.typicode.com/photos/" + id;

		ResponseEntity<FotoB> fotoResponse = restTemplate.getForEntity(url, FotoB.class);

		foto = fotoResponse.getBody();

		return foto;
	}

}
