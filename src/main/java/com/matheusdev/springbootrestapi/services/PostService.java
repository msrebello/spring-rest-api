package com.matheusdev.springbootrestapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusdev.springbootrestapi.entitties.Post;
import com.matheusdev.springbootrestapi.repository.PostRepository;
import com.matheusdev.springbootrestapi.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	
	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
} 
