package ru.skypro.coursework.coursework_spring.lot;

import ru.skypro.coursework.coursework_spring.status.LotStatus;

public record Lot(int id, LotStatus status, String title, String description, int startPrice, int bidPrice) {
    public Lot {
    }
}
