package ru.skypro.coursework.coursework_spring.service;

import com.opencsv.CSVWriter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.coursework.coursework_spring.lot.CreateLot;
import ru.skypro.coursework.coursework_spring.lot.FullLot;
import ru.skypro.coursework.coursework_spring.lot.Lot;
import ru.skypro.coursework.coursework_spring.status.LotStatus;
import ru.skypro.coursework.coursework_spring.exceptions.NoFoundById;
import ru.skypro.coursework.coursework_spring.handler.LotConverter;
import ru.skypro.coursework.coursework_spring.repository.BidRepository;
import ru.skypro.coursework.coursework_spring.repository.LotPagingRepository;
import ru.skypro.coursework.coursework_spring.repository.LotRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LotService {
    final private LotRepository lotRepository;
    final private LotConverter lotConverter;
    final private LotPagingRepository lotPagingRepository;
    final private BidRepository bidRepository;

    public LotService(LotRepository lotRepository, LotConverter lotConverter, LotPagingRepository lotPagingRepository, BidRepository bidRepository) {
        this.lotRepository = lotRepository;
        this.lotConverter = lotConverter;
        this.lotPagingRepository = lotPagingRepository;
        this.bidRepository = bidRepository;
    }

    public List<Lot> findLots(String status, int pageNumber, int unitsPerPage) {
        List<Lot> list = new ArrayList<>();
        Pageable page = PageRequest.of(pageNumber, unitsPerPage);
        if (status == null) {
            lotPagingRepository.findAll(page)
                    .forEach(l -> list.add(lotConverter.toLot(l)));
        } else {
            LotStatus lotStatus = LotStatus.valueOf(status.toUpperCase());
            lotPagingRepository.findAllByStatusIs(lotStatus, page)
                    .forEach(l -> list.add(lotConverter.toLot(l)));
        }
        log.info("page {} has been requested", pageNumber);
        return list;
    }

    @SneakyThrows
    public File getLotsInCSV() {
        File file = File.createTempFile("lots", ".csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));
             CSVWriter writer1 = new CSVWriter(writer, ';', '"', '\'', "\n")) {
            writer1.writeNext(new String[]{"id", "status", "title", "description", "startPrice", "bidPrice"});
            for (int page = 0; ; page++) {
                List<Lot> lots = findLots(null, page, 50);
                if (lots.isEmpty()) {
                    break;
                }
                for (Lot lot : lots) {
                    writer1.writeNext(new String[]{String.valueOf(lot.id()), lot.status().name(), lot.title(), lot.description(),
                            String.valueOf(lot.startPrice()), String.valueOf(lot.bidPrice())});
                }
            }
        }
        log.info("csv file has been requested");
        return file;
    }

    @Transactional
    public FullLot getFullLot(int id) {
        FullLot lot = lotRepository.findFullLotValues(id).orElseThrow(() -> new NoFoundById(id));
        lot.setCurrentPrice(bidRepository.countAllByLotId(id) * lot.getBidPrice() + lot.getStartPrice());
        log.info("lot has been requested: {}", lot);
        return lot;
    }

    @Transactional
    public void startLot(int id) {
        checkPresence(id);
        lotRepository.updateStatusById(id, LotStatus.STARTED);
        log.info("lot with id {} has been started", id);
    }

    @Transactional
    public void stopLot(int id) {
        checkPresence(id);
        lotRepository.updateStatusById(id, LotStatus.STOPPED);
        log.info("lot with id {} has been stopped", id);
    }

    public void addLod(CreateLot lot) {
        lotRepository.save(lotConverter.toLotModel(lot));
        log.info("new lot has been added: {}", lot);
    }

    public void checkPresence(int id) {
        if (!lotRepository.existsById(id)) {
            throw new NoFoundById(id);
        }
    }
}
