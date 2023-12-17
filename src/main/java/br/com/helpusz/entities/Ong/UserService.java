package br.com.helpusz.entities.Ong;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
  
  void register(User ong);

  String login(User ong);
  
}
