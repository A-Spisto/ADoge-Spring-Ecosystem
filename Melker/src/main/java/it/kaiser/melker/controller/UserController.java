package it.kaiser.melker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.kaiser.melker.model.User;
import it.kaiser.melker.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/utenti")
	public ResponseEntity<?> vediUtenti() {

		List<User> utenti = userService.findAll();

		if (utenti.isEmpty()) {

			return new ResponseEntity<>("nessun utente trovato", HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(utenti, HttpStatus.OK);

	}

}
