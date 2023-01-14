package my.ovsyannikov.den.homework.controller;

import my.ovsyannikov.den.homework.record.InfoRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class InfoController {
    @GetMapping
    public String index() {
        return "Application is run";
    }

    @GetMapping("/info")
    public InfoRecord info() {
        return new InfoRecord("Denis Ovsyannikov",
                "Рецепты", LocalDate.of(2023, 01, 14), "Книга рецептов");
    }
}