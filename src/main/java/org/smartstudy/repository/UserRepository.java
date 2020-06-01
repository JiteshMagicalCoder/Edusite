package org.smartstudy.repository;

import java.util.List;

import org.smartstudy.model.User;
import org.smartstudy.model.UserFiles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public 	interface UserRepository extends CrudRepository<User,Long> {

	User findByUsernameAndPassword(String username, String password);

	User findByEmail(String email);
	
	public User findByUsername(String username);
	
}
