package ru.skypro.coursework.coursework_spring.handler;

import org.springframework.stereotype.Component;
import ru.skypro.coursework.coursework_spring.bid.Bid;
import ru.skypro.coursework.coursework_spring.dto.BidDTO;

@Component
public class BidConverter {
    public BidDTO toBidDTO(Bid bid) {
        return new BidDTO(bid.bidderName());
    }
}
