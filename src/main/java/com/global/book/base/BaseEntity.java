package com.global.book.base;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
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

}
