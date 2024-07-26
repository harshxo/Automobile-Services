package com.harsh.fleetapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harsh.fleetapp.models.User;
import com.harsh.fleetapp.repositories.UserRepository;

@Service
public class UserService {
	@Autowired private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	//Get All Users
	public List<User> findAll(){
		return userRepository.findAll();
	}

	//Get User By Id
	public User findById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	//Delete User
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	//Update User
	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
}
