package com.global.book.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import com.global.book.base.BaseEntity;
import com.global.book.validator.IPAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "auther entity")
@NamedEntityGraph(name = "loadAuthor" ,attributeNodes = @NamedAttributeNode(value = "books"))
@Entity
@Table(name = "authors")
@AttributeOverride(name = "id", column = @Column(name = "author_id"))
public class Author extends BaseEntity<Long> implements Serializable {
   
  
	@NotBlank(message = "{validation-name}")
     @Column(name = "author_name")
	private String name;

	 @Email(message = "must be a valid email address")
	 @Column(unique = true)
    private String email;
	
//	@Pattern(regexp = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$", 
//			message = "must be a valid IPv4 address")
	@IPAddress(message = "must be a valid IPv4 address")
	
	private String ipAdress;

   private String imagePath;
	
	
//     @Formula("(select count(*) from  books book where book.author_id = author_id) ")
// 	private double bookCount;
     
     
     
    // @JsonBackReference
     @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY )
     private List<Book> books =new ArrayList<>();

     //helper method 

     public void addBook(Book book) {
    	 books.add(book);
     }



     public void removeBook(Book book) {
    	 books.remove(book);
     }


	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



//	public double getBookCount() {
//		return bookCount;
//	}
//
//
//
//	public void setBookCount(double bookCount) {
//		this.bookCount = bookCount;
//	}



	public String getIpAdress() {
		return ipAdress;
	}



	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
}
