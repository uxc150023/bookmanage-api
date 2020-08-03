package com.book.controller;

import com.book.entity.Book;
import com.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookHandler {
    @Autowired
    private BookRepository bookRepository;

//    @GetMapping("/findAll/{page}/{size}")
//    public Page<Book> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
//        PageRequest request = PageRequest.of(page,size);
//        return bookRepository.findAll(request);
//    }
    @GetMapping("/findAll")
    public Object findAll(Integer pages, Integer pageSize) {
        PageRequest request = PageRequest.of(pages,pageSize);
        return bookRepository.findAll(request);
    }
}
