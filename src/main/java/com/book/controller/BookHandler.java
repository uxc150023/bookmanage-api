package com.book.controller;

import com.book.entity.Book;
import com.book.repository.BookRepository;
import com.book.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookHandler {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/findAll")
//    public Page<Book> findAll(Integer pages, Integer pageSize) {
//        PageRequest request = PageRequest.of(pages,pageSize);
//        return bookRepository.findAll(request);
//    }
    public JsonResult<List> findAll(Integer pages, Integer pageSize) {
//        List<Book> userList = new ArrayList<>();
        PageRequest request = PageRequest.of(pages,pageSize);
        List<Book> userList = new ArrayList<>(request.);
        return new JsonResult<>(userList, "获取用户列表成功");
    }
}
