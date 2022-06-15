package com.example.demo.web.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = {"/", "/main"})
    public String goMain() {
        return "/main/main";
    }

}
