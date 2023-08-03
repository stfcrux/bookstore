package com.example.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return bookRepository.findByTitleAndAuthorsName(title, authorName);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
