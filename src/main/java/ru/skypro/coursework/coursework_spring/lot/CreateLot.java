package ru.skypro.coursework.coursework_spring.lot;

public record CreateLot(String title, String description, int startPrice, int bidPrice) {
    public CreateLot {
    }

    @Override
    public String toString() {
        return "CreateLot{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startPrice=" + startPrice +
                ", bidPrice=" + bidPrice +
                '}';
    }
}
