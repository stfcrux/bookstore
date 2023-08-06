package com.example.bookstore.DTO;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {
	

	private Long id;
	
	private List<Book> Books;
	
	private String name;
	
    private String birthday;

}
