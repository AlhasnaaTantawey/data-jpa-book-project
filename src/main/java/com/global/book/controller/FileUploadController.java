package com.global.book.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.global.book.service.FileUploadService;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileUploadController {

	private  final FileUploadService fileUploadService;
	
	@PostMapping("/upload")
	public ResponseEntity<Object> uploadFile(@RequestParam Long id  ,@RequestParam("path") String pathType 
			,@RequestParam MultipartFile file){
	String fileName	=fileUploadService.storeFile(fileUploadService.convertMultipartFileToFile(file), id, pathType);
		return ResponseEntity.ok(fileName);
	}
	
	
	 @PostMapping("/multiplefile")
	    public ResponseEntity<Object> uploadmultiplefile(@RequestParam Long id, @RequestParam("path") String pathType, 
	                                                     @RequestParam MultipartFile[] files) {
	        // Use a list to collect file names
	        List<String> fileNames = new ArrayList<>();
	        // Process each file in the array
	        for (MultipartFile file : files) {
	            String fileName = fileUploadService.storeFile(fileUploadService.convertMultipartFileToFile(file), id, pathType);
	            fileNames.add(fileName);
	        }
	        // Return the list of uploaded file names
	        return ResponseEntity.ok(fileNames);
	    }
	
}
