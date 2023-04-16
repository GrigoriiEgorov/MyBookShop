package com.example.MyBookShopApp.config;

import com.example.MyBookShopApp.data.BookRepository;
import com.example.MyBookShopApp.data.TestEntity;
import com.example.MyBookShopApp.data.TestEntityCrudRepository;
import com.example.MyBookShopApp.data.struct.author.Author;
import com.example.MyBookShopApp.data.struct.book.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.logging.Logger;

//@Configuration
public class CommandLineRunnerImpl implements CommandLineRunner {

    TestEntityCrudRepository testEntityCrudRepository;
    BookRepository bookRepository;

    @Autowired
    public CommandLineRunnerImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {



//        for (int i = 0; i < 5; i++) {
//            createEntityTest(new TestEntity());
//        }
//
//        TestEntity readTestEntity = readTestEntityById(3L);
//
//        Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info(readTestEntity.toString());
//
//        TestEntity updateTestEntity = updateTestEntity(5L);
//
//        deleteTestEntity(4L);

    }

    private void deleteTestEntity(Long id) {
        TestEntity findEntity = testEntityCrudRepository.findById(id).get();
        testEntityCrudRepository.delete(findEntity);
    }

//    private List<Author> getAuthorList(Book book){
//        return
//    }

    private TestEntity updateTestEntity(Long id) {
        TestEntity findEntity = testEntityCrudRepository.findById(id).get();
        findEntity.setDescribe("NEW UPDATE");
        return testEntityCrudRepository.save(findEntity);
    }

    private TestEntity readTestEntityById(Long id) {
        return testEntityCrudRepository.findById(id).get();
    }

    private void createEntityTest(TestEntity entity) {
        testEntityCrudRepository.save(entity);
    }
}
