package ru.skypro.coursework.coursework_spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.coursework.coursework_spring.bid.Bid;
import ru.skypro.coursework.coursework_spring.dto.BidDTO;
import ru.skypro.coursework.coursework_spring.model.BidModel;

import java.util.Optional;

public interface BidRepository extends CrudRepository<BidModel, Integer> {

    Optional<BidDTO> findFirstByLotIdOrderByBidDateAsc(int lotId);
    @Query("select new ru.skypro.coursework.coursework_spring.dto.BidDTO(b.bidderName) from BidModel b " +
            "where b.lotId = :lotId " +
            "group by b.id " +
            "order by count(b.bidderName) desc limit 1")
    Optional<BidDTO> findTheMostFrequentBidder(int lotId);

    int countAllByLotId(int id);
}

