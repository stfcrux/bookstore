package com.example.bookstore.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.DTO.BookDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;


@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;

    public Book findBookByIsbn(String isbn) {
        return bookRepository.findById(isbn).orElse(null);
    }

    public List<Book> findBooksByTitleAndAuthor(String title, String authorName) {
    	
    	List<Book> books = null;
    	
    	if(!title.isEmpty() && !authorName.isEmpty()) {
			books=bookRepository.findByTitleAndAuthorsName(title, authorName);
		}else if(title.isEmpty()) {
			books=bookRepository.findByAuthorsName(authorName);
		}else if(authorName.isEmpty()) {
			books=bookRepository.findByTitle(title);
		}
    	
        return books;
    }

    public void saveBook(BookDTO book) {
    	
    	Book bookToSave = new Book();   	
    	bookToSave.setIsbn(book.getIsbn());
    	bookToSave.setTitle(book.getTitle());
    	bookToSave.setAuthors(book.getAuthors());
    	bookToSave.setGenre(book.getGenre());
    	bookToSave.setPrice(book.getPrice());
    	bookToSave.setYear(book.getYear());
    	
        bookRepository.save(bookToSave);
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
