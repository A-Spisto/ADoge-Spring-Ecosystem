package it.kaiser.melker.serviceimpl;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.kaiser.melker.model.UserEntity;
import it.kaiser.melker.model.UserDTO;
import it.kaiser.melker.repository.UserRepository;
import it.kaiser.melker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<UserEntity> findAll() {

		return userRepository.findAll();
	}

	@Override
	public Optional<UserEntity> findById(String id) {

		return userRepository.findById(id);
	}

	@Override
	public UserEntity insert(UserDTO utenteDto) {

		UserEntity utente = new UserEntity();
		utente.setEmail(utenteDto.getEmail());
		utente.setName(utenteDto.getFirstName());
		utente.setRole("user");
		utente.setId(UUID.randomUUID().toString());
		utente.setEncryptedPassword(bCryptPasswordEncoder.encode(utenteDto.getPassword()));

		userRepository.insert(utente);

		return utente;
	}

	@Override
	public UserEntity update(UserEntity utente) {

		userRepository.save(utente);
		return utente;
	}

	@Override
	public void deleteById(String id) {
		userRepository.deleteById(id);
	}

	@Override
	public void delete(UserEntity utente) {
		userRepository.delete(utente);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserEntity> utente = userRepository.findUserByEmail(username);

		if (utente.isEmpty())
			throw new UsernameNotFoundException(username);

		return new User(utente.get().getEmail(), utente.get().getEncryptedPassword(), 
				true, true, true, true, new ArrayList<>());

	}

	@Override
	public UserDTO findByEmail(String email) {
		Optional<UserEntity> utente = userRepository.findUserByEmail(email);

		if (utente.isEmpty())
			throw new UsernameNotFoundException(email);
	
		return new ModelMapper().map(utente, UserDTO.class);
	}

}
