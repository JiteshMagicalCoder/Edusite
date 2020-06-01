package org.smartstudy.services;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.smartstudy.config.Encryptor;
import org.smartstudy.model.User;
import org.smartstudy.model.UserFiles;
import org.smartstudy.repository.UserImageRepository;
import org.smartstudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UploadPathService uploadPathService;
	@Autowired
	private UserImageRepository userImageRepository;
	@Autowired
	private ServletContext context;
	
	
	
	@Autowired
	private Encryptor encryptor;


	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User save(User user) {
		 user.setPassword(encryptor.encrypt(user.getPassword()));
		
		
		User dbuser = userRepository.save(user);
		if (dbuser != null && user.getFiles() != null && user.getFiles().size() > 0) {
			for (MultipartFile file : user.getFiles()) {
				if (file != null && StringUtils.hasText(file.getOriginalFilename())) {
					String fileName = file.getOriginalFilename();
					String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + System.currentTimeMillis()
							+ "." + FilenameUtils.getExtension(fileName);
					File storeFile = uploadPathService.getFilePath(modifiedFileName, "images");
					if (storeFile != null) {
						try {
							FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
						} catch (Exception e) {
							e.printStackTrace();
						}

						UserFiles files = new UserFiles();
						files.setFileExtension(FilenameUtils.getExtension(fileName));
						files.setFileName(fileName);
						files.setModifiedFileName(modifiedFileName);
						files.setUser(dbuser);
						userImageRepository.save(files);
					}

				}
			}

		}
		return dbuser;
	}

	@Override
	public User findById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Override
	public List<UserFiles> findFilesByUserId(Long userId) {

		return userImageRepository.findFilesByUserId(userId);
	}

	@Override
	public User update(User user) {

		
		User dbuser = userRepository.save(user);
		if (user != null && user.getRemoveImages() != null && user.getRemoveImages().size() > 0) {
			userImageRepository.deleteFilesByUserIdAndImageNames(user.getId(), user.getRemoveImages());
			for (String file : user.getRemoveImages()) {
				File dbFile = new File(context.getRealPath("/images/" + File.separator + file));
				if (dbFile.exists()) {
					dbFile.delete();
				}
			}

		}
		
	
		
		if (dbuser != null && user.getFiles() != null && user.getFiles().size() > 0) {
			for (MultipartFile file : user.getFiles()) {
				if (file != null && StringUtils.hasText(file.getOriginalFilename())) {
					String fileName = file.getOriginalFilename();
					String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + System.currentTimeMillis()
							+ "." + FilenameUtils.getExtension(fileName);
					File storeFile = uploadPathService.getFilePath(modifiedFileName, "images");
					if (storeFile != null) {
						try {
							FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
						} catch (Exception e) {
							e.printStackTrace();
						}

						UserFiles files = new UserFiles();
						files.setFileExtension(FilenameUtils.getExtension(fileName));
						files.setFileName(fileName);
						files.setModifiedFileName(modifiedFileName);
						files.setUser(dbuser);
						userImageRepository.save(files);
					}

				}
			}

		}
		return dbuser;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		
		String pwd =encryptor.encrypt(password);
		
		
		
	
		return userRepository.findByUsernameAndPassword(username, pwd);

		
	}

	@Override
	public void deleteFilesByUserId(Long userId) {
		List<UserFiles> userfiles = userImageRepository.findFilesByUserId(userId);
		if (userfiles != null && userfiles.size() > 0) {
			for (UserFiles dbFile : userfiles) {
				File dbStoreFile = new File(
						context.getRealPath("/images/" + File.separator + dbFile.getModifiedFileName()));
			}
		}
		userImageRepository.deleteFilesByUserId(userId);

	}

	@Override
	public void deleteUser(Long userId) {

		userRepository.deleteById(userId);
	}

	

	

	@Override
	public User findByEmail(String email) {

		return userRepository.findByEmail(email);
	}

}
