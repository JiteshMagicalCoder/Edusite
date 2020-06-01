package org.smartstudy.services;

import java.util.List;

import org.smartstudy.model.User;
import org.smartstudy.model.UserFiles;

public interface UserService {

	List<User> getAllUsers();

	User save(User user);

	User findById(Long userId);

	List<UserFiles> findFilesByUserId(Long userId);

	User update(User user);

	User findByUsernameAndPassword(String username, String password);

	void deleteFilesByUserId(Long userId);

	void deleteUser(Long userId);

	User findByEmail(String email);
	

}
