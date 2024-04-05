package com.example.library.bookmanagement.repository;

import com.example.library.bookmanagement.pojo.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {
    @Query("select count(id) from Book b where id=?1")
    Integer selectBookByid(int id);


    List<Book> findByNameContainingIgnoreCase(String name);

    Book findBookById(Integer id);




}
