package org.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Khong duoc de trong")
    @Size(min = 3, max = 255, message = "noi dung qua ngan")
    private String content;

    @NotNull(message = "Khong duoc de trong")
    @FutureOrPresent(message = "Khong de ngay trong qua khu")
    private LocalDate dueDate;

    @NotBlank(message = "Khong duoc de trong")
    @Pattern(
            regexp = "PENDING|DONE",
            message = "PENDING or DONE"
    )
    private String status;

    @NotBlank(message = "Khong duoc de trong")
    @Pattern(
            regexp = "LOW|MEDIUM|HIGH",
            message = "Phai la HIGH/MEDIUM/LOW"
    )
    private String priority;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}