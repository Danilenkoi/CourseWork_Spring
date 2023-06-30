package ru.skypro.coursework.coursework_spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.coursework.coursework_spring.dto.BidDTO;
import ru.skypro.coursework.coursework_spring.status.LotStatus;
import ru.skypro.coursework.coursework_spring.exceptions.NoFoundById;
import ru.skypro.coursework.coursework_spring.exceptions.LotStatusWrong;
import ru.skypro.coursework.coursework_spring.model.BidModel;
import ru.skypro.coursework.coursework_spring.model.LotModel;
import ru.skypro.coursework.coursework_spring.repository.BidRepository;
import ru.skypro.coursework.coursework_spring.repository.LotRepository;


@Service
public class BidService {
    final private BidRepository bidRepository;
    final private LotRepository lotRepository;

    public BidService(BidRepository bidRepository, LotRepository lotRepository) {
        this.bidRepository = bidRepository;
        this.lotRepository = lotRepository;
    }

    public BidDTO getFirstBidder(int lotId) {
        return bidRepository.findFirstByLotIdOrderByBidDateAsc(lotId)
                .orElseThrow(() -> new NoFoundById(lotId));
    }


    public BidDTO getMostFrequentBidder(int lotId) {
        return bidRepository.findTheMostFrequentBidder(lotId).orElseThrow(() -> new NoFoundById(lotId));
    }

    @Transactional
    public void addBid(int lotId, BidDTO dto) {
        BidModel bidModel = new BidModel(lotId, dto.bidderName());
        LotModel lot = lotRepository.findById(lotId).orElseThrow(() -> new NoFoundById(lotId));
        if (!lot.getStatus().equals(LotStatus.STARTED)) {
            throw new LotStatusWrong();
        }
        lot.setLastBid(bidModel);
        lotRepository.save(lot);
    }

}
