package ru.skypro.coursework.coursework_spring.exceptions;

public class NoFoundById extends RuntimeException{
    public NoFoundById(int id) {
        super("No instances for id: " + id);
    }
}
