package com.global.book.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


//import com.global.book.exception.GCPFileUploadException;
//import com.google.auth.Credentials;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.storage.*;
//import io.grpc.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.global.book.entity.Author;
import com.global.book.errors.FileStorageException;
@Service
public class FileUploadService {

	
	//amzon upload file 
	 @Value("${aws.s3.bucketName}")
	 private String bucketName;
	@Autowired
	private AmazonS3 amazonS3;

	//google
//   @Value("${gcp.config.file}")
//   private String gcpBucketName;
//
//   @Value("${gcp.project.id}")
//   private String gcpProjectId;
//
//   @Value("${gcp.dir.name}")
//   private String gcpCredentialPath;



	@Autowired
	private AuthorService authorService;
	
	
	Logger log = LoggerFactory.getLogger(FileUploadService.class);
	
	private Path fileStorageLocation ;
	
	
	private final String basePath="D:\\global\\book\\";

	
	
	 public String clouldUploadFile(MultipartFile file ,Long id , String pathType) {
		 String fileName=null;
		 
		 if(file.getContentType().contains("image")) {
			  fileName=id + "_"+UUID.randomUUID() +".jpg";
		 }
		 else {
			 fileName = id + file.getOriginalFilename();
			 }
		 String uniqueFileName= pathType+"/"+fileName;
		 
		 try {
			 awsUploadObject(uniqueFileName ,file);
		 }
		catch (Exception e) {
			throw new FileStorageException("exception.file.uplaod. failed ");
		}
		 
		// String downloadUri =this.baseLink +pathType+fileName;
		 updateImagePath(id, pathType, pathType+"/"+ fileName);
		 
		  return fileName;
		 
	 }
	
	//aws s3
	@SuppressWarnings("resource")
	public void awsUploadObject(String uniqueFileName, MultipartFile file) {
		log.info("upload file with name " + uniqueFileName);
		
		ObjectMetadata meta =new ObjectMetadata();
		
		try {
	meta.setContentLength(IOUtils.toByteArray(file.getInputStream()).length);
	
		PutObjectRequest putObjectRequest =new 	PutObjectRequest(bucketName, uniqueFileName, file.getInputStream(),meta)
				.withCannedAcl(CannedAccessControlList.PublicRead);
		PutObjectResult result=	amazonS3.putObject(putObjectRequest);
		log.info("file upload sucessfuly " + result.toString());
		}
	
	catch (Exception e) {
		e.printStackTrace();
	}
		
	}
	
	//filurl( store in db)  = uniquename = pathtype+ /+filename 
	public void awsDeleteObject(String fileUrl) {
		DeleteObjectRequest req= new DeleteObjectRequest(bucketName, fileUrl);
		amazonS3.deleteObject(req);
		log.info("file deleted from bucket " + bucketName +"as" + fileUrl);
	}

	
	//download file from aws s3
	public byte[] downloadFile(String keyName) {
		byte[] content =null;
		log.info("Downloading an  object with key = " + keyName);
	S3Object s3Object=	amazonS3.getObject(bucketName, keyName);
	   S3ObjectInputStream stream=  s3Object.getObjectContent();
	   try {
	   content=   IOUtils.toByteArray(stream);
	   log.info("file download sucessfully");
	   s3Object.close();
	   }
	   catch (IOException e) {
		log.info("Io error message" + e.getMessage());
	}
	   return content;
	}
	
	
	

	public File convertMultipartFileToFile(MultipartFile multipartFile)  {
		File file = new File(multipartFile.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(multipartFile.getBytes());
		} catch (IOException e) {
			log.error("error to convert multi-part-file to file", e.getMessage());
		}

		return file;
	}

	public String storeFile(File file, Long id, String pathType)    {
		
		//create upload path
		this.fileStorageLocation=Paths.get(basePath+pathType).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(this.fileStorageLocation);
		}catch (Exception ex) {
			throw new FileStorageException("coluld not create the firectory where the uploaded file will be stored",ex);
		}
		
		//normalize file name 
		 String fileName = StringUtils.cleanPath(id + "-" + file.getName());
           try {
		   if(fileName.contains("..")) {
			   throw new FileStorageException("sorry ! filename contains invalid path sequence "+fileName);
		   }
		
		//copy file to the target location (replacing existing file with the same name)
		 Path targetLocation= this.fileStorageLocation.resolve(fileName);
		   InputStream targetStream  =   new  FileInputStream(file);
		   Files.copy(targetStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
		   
		   updateImagePath(id, pathType, pathType+"/"+ fileName );
		   return fileName;
		   
	}  catch (Exception ex) {
				throw new FileStorageException("coluld  store file"+ fileName + ". please try again!",ex);
			}
	}

	
	private void updateImagePath(Long id, String pathType , String imagePath ) {
		
		if(pathType.contains("authors")) {
			//update image author path
		Author author=	authorService.findById(id);
		author.setImagePath(imagePath);
		authorService.update(author);
		}
		
	}
	
	
	//google cloud storage
	
//
//	public void googleUploadObject(  String fileName , MultipartFile multipartFile){
//
//     //load json file from google
//		InputStream inputStream=  getClass().getResourceAsStream(this.gcpCredentialPath);
//		try{
//			Credentials credentials=GoogleCredentials.fromStream(inputStream);
//			Storage storage=  StorageOptions.newBuilder().
//					setCredentials(credentials).setProjectId(gcpProjectId).build().getService();
//			BlobId blobId=   BlobId.of(gcpBucketName,fileName);
//			BlobInfo blobInfo=BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();
////		// Create the Blob object and upload the content
////		Blob blob = storage.create(blobId, inputStream, contentLength);
//			storage.create(blobInfo,multipartFile.getInputStream());
//		} catch (IOException e) {
//			throw  new GCPFileUploadException("bad upload eception occured");
//		}
//
//		log.info("file uploaded successfully"+gcpBucketName+fileName);
//	}
//
//
//	public void googleDeleteObject(String fileName) {
//
//		InputStream inputStream=  getClass().getResourceAsStream(this.gcpCredentialPath);
//		try{
//			Credentials credentials=GoogleCredentials.fromStream(inputStream);
//			Storage storage=  StorageOptions.newBuilder().
//					setCredentials(credentials).setProjectId(gcpProjectId).build().getService();
//			BlobId blobId=   BlobId.of(gcpBucketName,fileName);
//			storage.delete(blobId);
//		} catch (IOException e) {
//			throw  new GCPFileUploadException("bad upload eception occured");
//		}
//
//		log.info("file uploaded successfully"+gcpBucketName+fileName);
//	}
	
	
	
	
	
	
}
