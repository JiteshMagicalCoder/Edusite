package org.smartstudy.services;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.smartstudy.exception.FileStorageException;
import org.smartstudy.exception.MyFileNotFoundException;
import org.smartstudy.model.DBFile;
import org.smartstudy.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class FilesService {

    @Autowired
    DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(Long fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
    
    
    
	public List<DBFile> showAllUsers(){
		
		 List<DBFile> dbfiles= new ArrayList<DBFile>(); 
		 for(DBFile dbfile: dbFileRepository.findAll())
		 {
			 dbfiles.add(dbfile);
			 
		 } 
	    	 return dbfiles;
		 }
	
	
	


}