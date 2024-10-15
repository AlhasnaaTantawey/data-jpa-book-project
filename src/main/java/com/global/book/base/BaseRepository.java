package com.global.book.base;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.NoRepositoryBean;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;


@NoRepositoryBean
public interface BaseRepository <T extends BaseEntity<ID>, ID extends Number> extends JpaRepository<T, ID> {

//	@Modifying
//	@Transactional
//	@Query("update #{#entityName} t set t.statusCode = :statusCode where t.id = :id ")
//	void updateEntity(  ID id , String statusCode);
 


	
	 @Transactional
	    default void updateEntity(String entityName, ID id, String statusCode) {
	        String query = String.format("UPDATE %s t SET t.statusCode = ?1 WHERE t.id = ?2", entityName);
	        entityManager.createNativeQuery(query)
	                .setParameter(1, statusCode)
	                .setParameter(2, id)
	                .executeUpdate();
	    }

	    public static final EntityManager entityManager = null;

}
