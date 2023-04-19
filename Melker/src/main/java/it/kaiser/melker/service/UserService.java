package it.kaiser.melker.service;

import java.util.List;
import java.util.Optional;

import it.kaiser.melker.model.User;

public interface UserService {

	public List<User> findAll();
	public Optional<User> findById(String id);
	public User insert(User utente);
	public User update(User utente);
	public void deleteById(String id);
	public void delete(User utente);
	
	
}
