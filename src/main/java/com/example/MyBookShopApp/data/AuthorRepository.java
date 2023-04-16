package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.struct.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
