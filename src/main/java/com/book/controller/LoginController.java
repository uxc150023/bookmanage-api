package com.book.controller;

import com.book.result.JsonResult;
import com.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import com.book.entity.User;

import java.util.Objects;

@Controller
@RequestMapping("/book")
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    public JsonResult<Object> login(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        String msg;
        Integer status;
        String code;

        User user = userService.get(username, requestUser.getPassword());

        if (null == user) {
            String message = "账号密码错误";
            System.out.println("test");
            msg = "false";
            code="0";
            status=0;
        } else {
            msg = "success";
            code="1";
            status=1;
        }
        return new JsonResult<>(code, msg, status);
    }
}

