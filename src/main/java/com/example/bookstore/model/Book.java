package com.example.bookstore.model;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_book")
public class Book {
	
	@Id
    private String isbn;
	
    private String title;
    
    //many to many mapping since one book can have more than 1 author
    //and one author can write multiple books
    @ManyToMany(cascade = CascadeType.ALL) 
    @JoinTable(
    		joinColumns = @JoinColumn(name = "author"),
    		inverseJoinColumns = @JoinColumn(name = "isbn")	
    )
    
    private List<Author> authors;
    
    //due to h2 having year as a keyword
    @Column(name = "pub_year")
    private int year;
    
    private double price;
    
    private String genre;
}
