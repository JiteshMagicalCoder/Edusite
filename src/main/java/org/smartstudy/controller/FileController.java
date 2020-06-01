package org.smartstudy.controller;

import java.io.Serializable;

import org.smartstudy.model.DBFile;
import org.smartstudy.services.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class FileController implements Serializable{


    @Autowired
    private FilesService FilesService;

    @PostMapping(value="/uploadFile", consumes = "multipart/form-data")
    public String uploadFile(@RequestParam(value="file") MultipartFile file, RedirectAttributes redirectAttributes) {
        DBFile dbFile = FilesService.storeFile(file);

		/*
		 * String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		 * .path("/downloadFile/") .path(dbFile.getFileName()) .toUriString();
		 */

		/*
		 * return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
		 * file.getContentType(), file.getSize());
		 */ 
        return "redirect:/userdashboard";
        
    }

	/*
	 * @PostMapping("/uploadMultipleFiles") public List<UploadFileResponse>
	 * uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) { return
	 * Arrays.asList(files) .stream() .map(file -> uploadFile(file))
	 * .collect(Collectors.toList()); }
	 */

    @GetMapping("/downloadFile{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        // Load file from database
        DBFile dbFile = FilesService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFiletype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFilename() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

    @GetMapping("/showFile{fileId}")
    public ResponseEntity<Resource> showFile(@PathVariable Long fileId) {
        // Load file from database
        DBFile dbFile = FilesService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFiletype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFilename() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
}