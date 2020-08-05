package com.book.controller;

import com.book.entity.Book;
import com.book.repository.BookRepository;
import com.book.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookHandler {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("/findAll")
    public JsonResult<Object> findAll(Integer pageSize, Integer pages,Integer totalSize) {
        PageRequest request = PageRequest.of(pages,pageSize);
        Object obj = bookRepository.findAll(request);
        return new JsonResult<>(obj);
    }

    @GetMapping("/bookInfo")
    public JsonResult<Object> findById(Integer id){
        Object obj = bookRepository.findById(id).get();
        return new JsonResult<>(obj);
    }

    @PutMapping("/update")
    public JsonResult<String> update(@RequestBody Book book){
        Book result = bookRepository.save(book);
        String bool;
        if(result != null){
            bool = "success";
        }else{
            bool = "false";
        }
        return new JsonResult<>(bool);
    }
}
