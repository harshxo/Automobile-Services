package com.harsh.fleetapp.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.harsh.fleetapp.security.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	 @Query(
	            value = "SELECT * FROM Role where id NOT IN (SELECT role_id from user_role WHERE user_id = ?1)",
	            nativeQuery = true
	    )
	    List<Role> getUserNotRoles(Integer userId);
}
