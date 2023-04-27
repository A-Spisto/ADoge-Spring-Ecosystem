package it.kaiser.melker.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.kaiser.melker.model.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity,String>{

	public Optional<UserEntity> findUserByEmail(String email);
	
}
