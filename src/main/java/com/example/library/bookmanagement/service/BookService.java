package com.example.library.bookmanagement.service;

import com.example.library.bookmanagement.exception.BookNotFoundException;
import com.example.library.bookmanagement.exception.ResourceNotFoundException;
import com.example.library.bookmanagement.model.BookAdapter;
import com.example.library.bookmanagement.pojo.Book;
import com.example.library.bookmanagement.pojo.BookDetails;
import com.example.library.bookmanagement.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookService {
    @Autowired
    BookDetails bookDetails;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookAdapter bookAdapter;

    public Book addOne(BookDetails bookDetails){
        Book b = bookAdapter.toBookEntity(bookDetails);
        bookRepository.save(b);
        return b;
    }

    public List<Book> addAll(@Valid List<Book> bookDetails) throws Exception {
        if(bookDetails == null || bookDetails.isEmpty()) {
            throw new Exception("Book details required");
        }
        return (List<Book>) bookRepository.saveAll(bookDetails);
    }


    public Optional<Book> findone(Integer id){
        return Optional.ofNullable(bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id)));
    }

    public List<Book> findBookByName(String name){
        List<Book> b = bookRepository.findByNameContainingIgnoreCase(name);
        if(b==null || b.isEmpty()){
            throw new BookNotFoundException(name + " not found");
        } else {
            return b;
        }
    }

    public Book updateBook(BookDetails bookDetails,Integer id) {
        Book book = bookAdapter.toBookEntity(bookDetails);
        Book b = bookRepository.findBookById(id);
        if(b==null){
            throw new ResourceNotFoundException("Book", "id", id);
        } else {
            if (book.getName() != null)
                b.setName(book.getName());
            if (book.getAuthor() != null)
                b.setAuthor(book.getAuthor());
            if (book.getShelveNo() != null)
                b.setShelveNo(book.getShelveNo());
            if (book.getPrice() >= 0)
                b.setPrice(book.getPrice());
            return bookRepository.save(b);
        }
    }

    public void deleteBook(Integer id) {
        bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book", "id",id));
        bookRepository.deleteById(id);
    }

    public Integer selectByid(Integer id){
        return bookRepository.selectBookByid(id);
    }

    public List<Book> findAllBook(){
         List<Book> b = (List<Book>) bookRepository.findAll();
         if(b==null || b.isEmpty()){
             throw new BookNotFoundException("No Books found");
         } else {
             return b;
         }

    }
}
