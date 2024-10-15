package com.global.book.base;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners( { AuditingEntityListener.class })
public  abstract class BaseEntity <ID> {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id ;
	
	
	 private  String statusCode;
	 

	@CreatedBy
	private String createdBy;

	@CreatedDate
	private LocalDateTime createdData;

	@LastModifiedBy
	private String lastModifiedBy;

	@LastModifiedDate
	private LocalDateTime lastModifiedData;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedData() {
		return createdData;
	}

	public void setCreatedData(LocalDateTime createdData) {
		this.createdData = createdData;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public LocalDateTime getLastModifiedData() {
		return lastModifiedData;
	}

	public void setLastModifiedData(LocalDateTime lastModifiedData) {
		this.lastModifiedData = lastModifiedData;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	

}
