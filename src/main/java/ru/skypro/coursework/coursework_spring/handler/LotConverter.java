package ru.skypro.coursework.coursework_spring.handler;

import org.springframework.stereotype.Component;
import ru.skypro.coursework.coursework_spring.lot.CreateLot;
import ru.skypro.coursework.coursework_spring.lot.Lot;
import ru.skypro.coursework.coursework_spring.status.LotStatus;
import ru.skypro.coursework.coursework_spring.model.LotModel;


@Component
public class LotConverter {

    public LotModel toLotModel(CreateLot dto) {
        return new LotModel(LotStatus.CREATED, dto.title(), dto.description(), dto.startPrice(), dto.bidPrice());
    }

    public Lot toLot(LotModel lotModel) {
        return new Lot(lotModel.getId(),
                lotModel.getStatus(),
                lotModel.getTitle(),
                lotModel.getDescription(),
                lotModel.getStartPrice(),
                lotModel.getBidPrice());
    }

}
