package com.example.library.bookmanagement.model;

import com.example.library.bookmanagement.pojo.Book;
import com.example.library.bookmanagement.pojo.BookDetails;
import org.springframework.stereotype.Service;

@Service
public class BookAdapter {
    public Book toBookEntity(BookDetails bookDetails){
        Book b = new Book();
        b.setId(bookDetails.getId());
        b.setName(bookDetails.getName());
        b.setAuthor(bookDetails.getAuthor());
        b.setPrice(bookDetails.getPrice());
        b.setShelveNo(bookDetails.getShelveNo());
        return b;
    }
}
