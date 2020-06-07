package com.family.fmlgenealogy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fml/test")
public class TestController {
    @RequestMapping("index")
    @Override
    public String toString() {
        return "成功！";
    }
}
