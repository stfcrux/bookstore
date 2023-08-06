package com.example.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookstore.model.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {

}
