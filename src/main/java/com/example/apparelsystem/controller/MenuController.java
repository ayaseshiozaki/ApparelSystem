
package com.example.apparelsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/login")
    public String login() {
        return "01-login";
    }

    @GetMapping("/index")
    public String index() {
        return "02-index";
    }
}
