package com.matheusdev.springbootrestapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusdev.springbootrestapi.dto.UserDTO;
import com.matheusdev.springbootrestapi.entitties.User;
import com.matheusdev.springbootrestapi.repository.UserRepository;
import com.matheusdev.springbootrestapi.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User user) {
		return repository.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User uptade(User user) {
		User newUser = findById(user.getId());
		updateUser(newUser, user);
		return repository.save(newUser);
	}

	private void updateUser(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());	
	}
	
	public User fromDTO (UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
} 
