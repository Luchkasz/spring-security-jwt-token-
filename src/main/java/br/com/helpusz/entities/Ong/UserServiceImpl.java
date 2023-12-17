package br.com.helpusz.entities.Ong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.helpusz.config.JwtTokenProvider;


@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository ongRepository;

  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  private JwtTokenProvider jwtTokenProvider;
  
  @Override
  public void register(User ong) {
    if(this.ongRepository.existsByEmail(ong.getEmail())) {
      throw new RuntimeException("Conta já existente");
    } 

    ong.setPassword(passwordEncoder.encode(ong.getPassword()));
    
    this.ongRepository.save(ong);
  }

  @Override
  public String login(User ong) {
    if(!this.ongRepository.existsByEmail(ong.getEmail())) {
      throw new RuntimeException("Conta não existente");
    }

    User existingOng = this.ongRepository.findByEmail(ong.getEmail());

    if(!passwordEncoder.matches(ong.getPassword(), existingOng.getPassword())) {
      throw new RuntimeException("Senha inválida");
    }

    String token = jwtTokenProvider.createToken(existingOng.getEmail());

    System.out.println("Login efetuado");

    System.out.println("Token: " + token);

    return token;
  }
  
}
