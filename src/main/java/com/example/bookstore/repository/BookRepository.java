package com.example.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bookstore.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
	
	
	@Query("SELECT b FROM Book b JOIN b.authors a WHERE a.name IN ?2 " +
          "AND b.title = ?1")
	List<Book> findByTitleAndAuthorsName(String title, String authorName);
	
	List<Book> findByAuthorsName(String authorName);
	
	List<Book> findByTitle(String title);
    
}
