package it.kaiser.melker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.kaiser.melker.model.User;

@Repository
public interface UserRepository extends MongoRepository<User,String>{

}
