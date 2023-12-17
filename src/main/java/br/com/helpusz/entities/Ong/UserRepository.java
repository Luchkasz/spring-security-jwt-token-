package br.com.helpusz.entities.Ong;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

  boolean existsByEmail(String email);

  User findByEmail(String email);
  
}
