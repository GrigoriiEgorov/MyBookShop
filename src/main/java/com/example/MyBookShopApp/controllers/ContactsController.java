package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ContactsController {

    @ModelAttribute("nameGetter")
    public String nameGetter(){
        return "";
    }

    @GetMapping("/contacts")
    public String contacts(){
        return "contacts";
    }
}
