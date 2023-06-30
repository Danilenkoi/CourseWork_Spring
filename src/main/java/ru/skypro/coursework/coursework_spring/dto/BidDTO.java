package ru.skypro.coursework.coursework_spring.dto;

public record BidDTO(String bidderName) {
    public BidDTO {
    }

    @Override
    public String toString() {
        return "BidDTO{" +
                "bidderName='" + bidderName + '\'' +
                '}';
    }
}
