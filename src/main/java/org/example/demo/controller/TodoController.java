package org.example.demo.controller;

import org.example.demo.entity.Todo;
import org.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import java.util.Optional;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoRepository.findAll());
        model.addAttribute("todo", new Todo());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Todo> optional = todoRepository.findById(id);

        if (optional.isPresent()) {
            model.addAttribute("todo", optional.get());
            model.addAttribute("todos", todoRepository.findAll());
            return "index";
        }

        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("todo") Todo todo,
                         BindingResult result,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("todos", todoRepository.findAll());
            model.addAttribute("todo", todo);
            return "index";
        }

        todoRepository.save(todo);
        redirectAttributes.addFlashAttribute("message", "Cập nhật thành công!");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes) {

        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Xóa thành công!");
        }
        return "redirect:/";
    }
}