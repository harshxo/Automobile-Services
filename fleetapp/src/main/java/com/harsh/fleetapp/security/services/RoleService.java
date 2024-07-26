package com.harsh.fleetapp.security.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.fleetapp.models.User;
import com.harsh.fleetapp.repositories.UserRepository;
import com.harsh.fleetapp.security.models.Role;
import com.harsh.fleetapp.security.repositories.RoleRepository;

@Service
public class RoleService {

	  @Autowired
	    private RoleRepository roleRepository;
	    
	    @Autowired
	    private UserRepository userRepository;

	    public List<Role> getRoles(){
	        return roleRepository.findAll();
	    }

	    public void save(Role client) {
	        roleRepository.save(client);
	    }

	    public Role findById(int id) {
	        return roleRepository.findById(id).orElse(null);
	    }

	    public void delete(Integer id) {
	        roleRepository.deleteById(id);
	    }

	    public void assignRole(Integer userId, Integer roleId){
	        User user = userRepository.findById(userId).orElse(null);
	        Role role = roleRepository.findById(roleId).orElse(null);
	        Set<Role> userRoles = user.getRoles();
	        userRoles.add(role);
	        user.setRoles(userRoles);
	        userRepository.save(user);
	    }

	    public void unassignRole(Integer userId, Integer roleId){
	        User user = userRepository.findById(userId).orElse(null);
	        Set<Role> userRoles = user.getRoles();
	        userRoles.removeIf(x -> x.getId() == roleId);
	        user.setRoles(userRoles);
	        userRepository.save(user);
	    }

	    public Set<Role> getUserRoles(User user){
	        return user.getRoles();
	    }


	    public List<Role> getUserNotRoles(User user){
	        return roleRepository.getUserNotRoles(user.getId());
	    }
}
