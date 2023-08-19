package com.matheusdev.springbootrestapi.config;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.matheusdev.springbootrestapi.dto.AuthorDTO;
import com.matheusdev.springbootrestapi.entitties.Post;
import com.matheusdev.springbootrestapi.entitties.User;
import com.matheusdev.springbootrestapi.repository.PostRepository;
import com.matheusdev.springbootrestapi.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.of("GMT"));

		postRepository.deleteAll();
		userRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, LocalDate.parse("21/03/2018", formatter), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",new AuthorDTO(maria));
		Post post2 = new Post(null, LocalDate.parse("23/03/2018", formatter), "Bom dia", "Acordei feliz hoje!",new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);

	}

}
