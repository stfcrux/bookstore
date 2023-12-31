package com.example.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.DTO.BookDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
    @Autowired
    private BookService bookService;


    @GetMapping("/get/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
    	
    	if (bookService.findBookByIsbn(isbn) == null) {
    		
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		
    	}else {
    	
    		return new ResponseEntity<>(bookService.findBookByIsbn(isbn),HttpStatus.OK);
    	
    	}
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Book>> getBooksByTitleAndAuthor(
            @RequestParam String title,
            @RequestParam String authorName) {
        return new ResponseEntity<>(bookService.findBooksByTitleAndAuthor(title, authorName),HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Void> addBook(@RequestBody BookDTO book) {
    	
    	if (bookService.findBookByIsbn(book.getIsbn()) != null) {
    		
    		return new ResponseEntity<>(HttpStatus.CONFLICT);
    		
    	}else {
    	
    		bookService.saveBook(book);
    		return new ResponseEntity<>(HttpStatus.CREATED);
    	}
    }

    @PutMapping("/update/{isbn}")
    public ResponseEntity<Void> updateBook(@PathVariable String isbn, @RequestBody BookDTO book) {   	
        
    	
    	if (bookService.findBookByIsbn(isbn) == null) {
    		
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		
    	}else {
    		
    		bookService.saveBook(book);
            return new ResponseEntity<>(HttpStatus.OK);
    		
    	}
    	
    	
    }
    
    
    // handled security in security class, only admin allowed to access
    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
    	
    	if (bookService.findBookByIsbn(isbn) == null) {
    		
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		
    	}else {
    		
    		bookService.deleteBook(isbn);
            return new ResponseEntity<>(HttpStatus.OK);
    		
    	}
    }
	

}
