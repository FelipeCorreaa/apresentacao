package com.noovi2.userdept.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noovi2.userdept.entities.User;
import com.noovi2.userdept.repositories.UserRepository;

@Service
public class Userservice {
	
	//Listar os usuários do repositório do banco de dados.
	@Autowired
	private UserRepository repository;
	
	public List <User> FindAll (){
		return repository.findAll();
			
	}
	
	
	// buscar usuário pelo ID
	public User findById (Long id) {
		Optional <User> obj = repository.findById(id);
		return obj.get();
	}
	
	
}