package s.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping({"/", "/books"})
    public String books() {
        return "book";
    }

    @GetMapping("/add-book")
    public String addBook() {
        return "add_book";
    }

    @GetMapping("/edit-book")
    public String editBook() {
        return "edit-book";
    }
}