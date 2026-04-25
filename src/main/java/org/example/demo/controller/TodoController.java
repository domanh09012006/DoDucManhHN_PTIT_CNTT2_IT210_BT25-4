package org.example.demo.controller;

import jakarta.validation.Valid;
import org.example.demo.entity.Todo;
import org.example.demo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("todos", service.getAll());
        model.addAttribute("todo", new Todo());
        return "index";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("todo") Todo todo,
                      BindingResult result,
                      Model model) {

        if (result.hasErrors()) {
            model.addAttribute("todos", service.getAll());
            return "index";
        }

        service.save(todo);
        return "redirect:/";
    }
}