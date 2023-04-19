package it.kaiser.melker.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import it.kaiser.melker.model.User;
import it.kaiser.melker.repository.UserRepository;
import it.kaiser.melker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public List<User> findAll() {

		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(String id) {

		return userRepository.findById(id);
	}

	@Override
	public User insert(User utente) {

		userRepository.insert(utente);

		return utente;
	}

	@Override
	public User update(User utente) {

		userRepository.save(utente);
		return utente;
	}

	@Override
	public void deleteById(String id) {
		userRepository.deleteById(id);
	}

	@Override
	public void delete(User utente) {
		userRepository.delete(utente);

	}

}
