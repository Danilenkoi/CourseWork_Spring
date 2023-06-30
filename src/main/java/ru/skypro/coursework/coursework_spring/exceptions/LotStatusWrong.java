package ru.skypro.coursework.coursework_spring.exceptions;

public class LotStatusWrong extends RuntimeException{
    public LotStatusWrong() {
        super("Wrong lot");
    }
}
