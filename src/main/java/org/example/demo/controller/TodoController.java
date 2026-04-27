package org.example.demo.controller;

import org.example.demo.entity.Todo;
import org.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.Optional;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        String owner = (String) session.getAttribute("ownerName");
        if (owner == null) {
            return "redirect:/welcome";
        }
        model.addAttribute("ownerName", owner);
        model.addAttribute("todos", todoRepository.findAll());
        model.addAttribute("todo", new Todo());

        return "index";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        Optional<Todo> optional = todoRepository.findById(id);
        if (optional.isPresent()) {
            model.addAttribute("todo", optional.get());
            model.addAttribute("todos", todoRepository.findAll());
            model.addAttribute("ownerName", session.getAttribute("ownerName"));
            return "index";
        }

        return "redirect:/";
    }
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("todo") Todo todo,
                         BindingResult result,
                         Model model,
                         HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("todos", todoRepository.findAll());
            model.addAttribute("ownerName", session.getAttribute("ownerName"));
            return "index";
        }
        todoRepository.save(todo);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
    @PostMapping("/save-name")
    public String saveName(@RequestParam String name, HttpSession session) {

        if (name == null || name.trim().isEmpty()) {
            return "redirect:/welcome";
        }
        session.setAttribute("ownerName", name);
        return "redirect:/";
    }
}