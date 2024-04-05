package com.example.library.bookmanagement.validator;

import com.example.library.bookmanagement.pojo.BookDetails;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;

import static org.aspectj.util.LangUtil.isEmpty;

@Component
public class BookValidator {
    public void validate(BookDetails bookDetails) throws Exception {

        if(bookDetails.getName()==null){
            throw new Exception("Book name can not be empty");
        }
        if(bookDetails.getName()==""){
            throw new Exception("Book name can not be empty");
        }
        if(bookDetails.getAuthor()==null){
            throw new Exception("Author can not be empty");
        }
        if(bookDetails.getAuthor()==""){
            throw new Exception("Author can not be empty");
        }
        if(bookDetails.getPrice()==null){
            throw new Exception("Please enter price");
        }
        if(bookDetails.getPrice()<0.0d){
            throw new Exception("Price can not be less than 0");
        }
        if (bookDetails.getShelveNo()==null){
            throw new Exception("Shelf Number can not be empty");
        }
        if (bookDetails.getShelveNo()<0){
            throw new Exception("Shelf Number can not be negative");
        }

    }
}
