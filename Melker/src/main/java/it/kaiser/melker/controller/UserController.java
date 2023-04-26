package it.kaiser.melker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import it.kaiser.melker.model.UserEntity;
import it.kaiser.melker.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	

	@GetMapping("/utenti")
	public ResponseEntity<?> vediUtenti() {

		List<UserEntity> utenti = userService.findAll();

		if (utenti.isEmpty()) {

			return new ResponseEntity<>("nessun utente trovato", HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(utenti, HttpStatus.OK);

	}
	
	
	
	@PostMapping("/passwordTestEncode")
	public ResponseEntity<?> testCodePassword(@RequestBody String password) {
	
		return new ResponseEntity<>("La password codificata è: "
		+bCryptPasswordEncoder.encode(password), HttpStatus.OK);

	}
	
	

}
