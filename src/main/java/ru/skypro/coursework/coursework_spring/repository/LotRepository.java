package ru.skypro.coursework.coursework_spring.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.coursework.coursework_spring.lot.FullLot;
import ru.skypro.coursework.coursework_spring.status.LotStatus;
import ru.skypro.coursework.coursework_spring.model.LotModel;

import java.util.Optional;

public interface LotRepository extends CrudRepository<LotModel, Integer> {


    @Query("select new ru.skypro.coursework.coursework_spring.lot.FullLot(l.id, l.status, l.title, l.description," +
            " l.startPrice, l.bidPrice, l.currentPrice, l.lastBidModel)" +
            " from LotModel l where l.id = :id")
    Optional<FullLot> findFullLotValues(int id);

    @Modifying
    @Query("update LotModel l set l.status = :status where l.id = :id")
    void updateStatusById(int id, LotStatus status);

}
