package com.example.bookstore.DTO;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.example.bookstore.model.Author;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
	
	private String isbn;
	
    private String title;
    
    private List<Author> authors;
    
    private int year;
    
    private double price;
    
    private String genre;
	

}
