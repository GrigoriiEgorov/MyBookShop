package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.struct.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
//    List<Book> findBooksByAuthor_FirstName(String name);

//    @Query("from Book")
//    List<Book> customFindAllBooks();

    //NEW BOOK REP COMMANDS

    List<Book> findBooksByTitleContaining(String bookTitle);

    List<Book> findBooksByPriceBetween(Integer min, Integer max);

    List<Book> findBooksByPriceIs(Integer price);

    @Query("from Book where isBestseller = 1")
    List<Book> getBestsellers();

    @Query(value = "SELECT * FROM books WHERE discount = (SELECT MAX(discount) FROM books)", nativeQuery = true)
    List<Book> getBookWithMaxDiscount();


    Page<Book> findBooksByTitleContaining(String bootTitle, Pageable nextPage);

    @Query(value = "select * from books order by pub_date desc", nativeQuery = true)
    Page<Book> findAllBooksSortedByPubDate (Pageable nextPage);

    Book findBookBySlug(String slug);

    List<Book> findBooksBySlugIn(String[] slugs);
}
