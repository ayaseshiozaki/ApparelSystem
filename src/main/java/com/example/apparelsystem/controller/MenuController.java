
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
    @GetMapping("/uriage")
    public String uriage() {
        return "03-17-uriage/03-uriagekannri";
    }

    @GetMapping("/zaiko")
    public String zaiko() {
        return "18-29-zaiko/18-zaikokannri";
    }

    @GetMapping("/siireasaki")
    public String siireasaki() {
        return "30-43-siiresaki/30-siiresaki_kannri";
    }

}
