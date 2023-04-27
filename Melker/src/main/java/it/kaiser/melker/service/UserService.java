package it.kaiser.melker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import it.kaiser.melker.model.UserEntity;
import it.kaiser.melker.model.UserDTO;

public interface UserService extends UserDetailsService{

	public List<UserEntity> findAll();
	public Optional<UserEntity> findById(String id);
	public UserDTO findByEmail(String email);
	public UserEntity insert(UserDTO utente);
	public UserEntity update(UserEntity utente);
	public void deleteById(String id);
	public void delete(UserEntity utente);
	
	
}
