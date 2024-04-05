package com.example.library.bookmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BookDetails {
    private Integer id;
    private String name;
    private String author;
    private Integer shelveNo;
    private Double price;
}
