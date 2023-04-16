package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.struct.book.Book;
import com.example.MyBookShopApp.errs.BookstoreApiWrongParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {


    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }

    public List<Book> getBookByTitle(String title) throws BookstoreApiWrongParameterException {
        if(title.isBlank() || title.length() <= 1){
            throw new BookstoreApiWrongParameterException("Wrong values passed to one ore more parameters");
        } else {
            List<Book> data = bookRepository.findBooksByTitleContaining(title);
            if(data.size()>0){
                return data;
            }else {
                throw new BookstoreApiWrongParameterException("No data found with specified parameters");
            }
        }
    }

    public List<Book> getBooksWithPriceBetween(Integer min, Integer max){
        return bookRepository.findBooksByPriceBetween(min, max);
    }

    public List<Book> getBooksWithPrice(Integer price){
        return bookRepository.findBooksByPriceIs(price);
    }

    public List<Book> getBooksWithMaxDiscount(){
        return bookRepository.getBookWithMaxDiscount();
    }

    public List<Book> getBestsellers(){
        return bookRepository.getBestsellers();
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBooksByTitleContaining(searchWord, nextPage);
    }

    public Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit){
        Pageable pageable = PageRequest.of(offset, limit);
        return bookRepository.findAll(pageable);
    }

    public Page<Book> getPageOfNewBooks(Integer offset, Integer limit){
        Pageable pageable = PageRequest.of(offset,limit);
        return bookRepository.findAllBooksSortedByPubDate(pageable);
    }

    public Book getBookBySlug(String slug){
        return bookRepository.findBookBySlug(slug);
    }

}
