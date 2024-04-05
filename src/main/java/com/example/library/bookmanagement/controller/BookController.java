package com.example.library.bookmanagement.controller;

import com.example.library.bookmanagement.exception.BookNotFoundException;
import com.example.library.bookmanagement.model.BookAdapter;
import com.example.library.bookmanagement.pojo.Book;
import com.example.library.bookmanagement.pojo.BookDetails;
import com.example.library.bookmanagement.service.BookService;
import com.example.library.bookmanagement.validator.BookValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    BookValidator bookValidator;

    @Autowired
    BookAdapter bookAdapter;

    @GetMapping(path = "/book/findallbook")
    public List<Book> findAllBooks(){
        List<Book> b = bookService.findAllBook();
        return b;
    }

    @GetMapping(path="/book/findonebook/{id}")
    public Optional<Book> findonebook(@PathVariable Integer id){
            Optional<Book> b = bookService.findone(id);
            return b;
    }

    @GetMapping(path="/book/findbookbyname/{name}")
    public List<Book> findbookbyName(@PathVariable String name){
            List<Book> b = bookService.findBookByName(name);
            return b;
    }

    @PostMapping(path ="/book/addbook")
    public ResponseEntity<Book> addOne(@Valid @RequestBody BookDetails bookDetails) throws Exception {
        bookValidator.validate(bookDetails);
        Book b = bookService.addOne(bookDetails);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(b.getId())
                .toUri();

        //to send "created" as response status ResponseEntity is used
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path="/updatebook/id/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody BookDetails bookDetails, @PathVariable Integer id) throws Exception {
           bookService.updateBook(bookDetails, id);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/book/deletebook/{id}")
    public void deleteBook(@PathVariable Integer id) {
            bookService.deleteBook(id);
    }

    @PostMapping(path ="/book/addallbook")
    public ResponseEntity<Book> addBooks(@Valid @RequestBody List<Book> bookDetails) throws Exception {
        /*List<BookDetails> b = bookDetails;
        Iterator it = b.iterator();
        while (it.hasNext()) {
            bookValidator.validate((BookDetails) it.next());
            bookService.addOne((BookDetails) it.next());
            it.next();
        }*/
        List<Book> b = bookService.addAll(bookDetails);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
