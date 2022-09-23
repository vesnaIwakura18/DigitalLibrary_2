package kz.bisen.springwebapp1.project2Boot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main {

    @GetMapping()
    public String getMainPage() {
        return "main";
    }
}
